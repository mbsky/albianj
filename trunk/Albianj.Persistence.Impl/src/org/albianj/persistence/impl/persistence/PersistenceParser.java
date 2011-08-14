package org.albianj.persistence.impl.persistence;

import java.beans.PropertyDescriptor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.cached.AlbianObjectsCached;
import org.albianj.persistence.impl.storage.StorageParser;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.ICacheAttribute;
import org.albianj.persistence.object.IMemberAttribute;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.impl.AlbianObjectAttribute;
import org.albianj.persistence.object.impl.CacheAttribute;
import org.albianj.persistence.object.impl.MemberAttribute;
import org.albianj.persistence.object.impl.RoutingAttribute;
import org.albianj.persistence.toolkit.Convert;
import org.albianj.verify.Validate;
import org.albianj.xml.XmlParser;
import org.dom4j.Element;
import org.dom4j.Node;

public class PersistenceParser extends FreePersistenceParser
{

	private static final String cacheTagName = "Cache";
	private static final String memberTagName = "Members/Member";
	public static final String DEFAULT_ROUTING_NAME = "!@#$%Albianj_Default_Routing%$#@!";

	@Override
	protected void parserAlbianObjects(@SuppressWarnings("rawtypes") List nodes)
	{
		if(Validate.isNullOrEmpty(nodes))
		{
			throw new IllegalArgumentException("nodes");
		}
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		for (Object node : nodes)
		{
			IAlbianObjectAttribute albianObjectAttribute = parserAlbianObject((Element) node);
			if (null == albianObjectAttribute)
			{
				String msg = "parser the albian object is error.";
				if(null != logger)
					logger.warn(msg);
				throw new PersistenceAttributeException(msg);
			}

			AlbianObjectsCached.insert(albianObjectAttribute.getType(),albianObjectAttribute);
		}

	}

	@Override
	protected IAlbianObjectAttribute parserAlbianObject(Element node)
	{
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		String type = XmlParser.getAttributeValue(node, "Type");
		if (Validate.isNullOrEmptyOrAllSpace(type))
		{
			if (null != logger) logger
					.error("The albianObject's type is empty or null.");
			return null;
		}

		Map<String, IMemberAttribute> map = reflexAlbianObjectMembers(type);

		Node cachedNode = node.selectSingleNode(cacheTagName);
		ICacheAttribute cached;
		if (null == cachedNode)
		{
			cached = new CacheAttribute();
			cached.setEnable(true);
			cached.setLifeTime(300);
		}
		else
		{
			cached = parserAlbianObjectCache(node);
		}

		IRoutingAttribute defaultRouting = new RoutingAttribute();
		defaultRouting.setName(DEFAULT_ROUTING_NAME);
		defaultRouting.setOwner("dbo");
		defaultRouting.setStorageName(StorageParser.DEFAULT_STORAGE_NAME);
		defaultRouting.setTableName(ReflectionHelper.getClassSimpleName(type));

		@SuppressWarnings("rawtypes")
		List nodes = node.selectNodes(memberTagName);
		if(!Validate.isNullOrEmpty(nodes))
		{
			parserAlbianObjectMembers(nodes, map);
		}
		IAlbianObjectAttribute albianObjectAttribute = new AlbianObjectAttribute();
		albianObjectAttribute.setCache(cached);
		albianObjectAttribute.setMembers(map);
		albianObjectAttribute.setType(type);
		albianObjectAttribute.setDefaultRouting(defaultRouting);
		return albianObjectAttribute;
	}

	private static ICacheAttribute parserAlbianObjectCache(Node node)
	{
		String enable = XmlParser.getAttributeValue(node, "Enable");
		String lifeTime = XmlParser.getAttributeValue(node, "LifeTime");
		ICacheAttribute cache = new CacheAttribute();
		cache.setEnable(Validate.isNullOrEmpty(enable) ? true
				: new Boolean(enable));
		cache.setLifeTime(Validate.isNullOrEmpty(lifeTime) ? 300
				: new Integer(lifeTime));
		return cache;
	}

	private static void parserAlbianObjectMembers(@SuppressWarnings("rawtypes") List nodes,
			Map<String, IMemberAttribute> map)
	{
		for (Object node : nodes)
		{
			parserAlbianObjectMember((Element) node, map);
		}
	}

	private static void parserAlbianObjectMember(Element elt,
			Map<String, IMemberAttribute> map)
	{
		String name = XmlParser.getAttributeValue(elt, "Name");
		IAlbianLoggerService logger = AlbianServiceRouter.getService(
				IAlbianLoggerService.class, "logger");
		if (Validate.isNullOrEmpty(name))
		{
			String msg = "AlbianObject name is null or empty.";
			if (null != logger)
			{
				logger.error(msg);
			}
			throw new PersistenceAttributeException(msg);
		}
		IMemberAttribute member = (IMemberAttribute) map.get(name);

		String fieldName = XmlParser.getAttributeValue(elt, "FieldName");
		String allowNull = XmlParser.getAttributeValue(elt, "AllowNull");
		String length = XmlParser.getAttributeValue(elt, "Length");
		String primaryKey = XmlParser.getAttributeValue(elt, "PrimaryKey");
		String dbType = XmlParser.getAttributeValue(elt, "DbType");
		String isSave = XmlParser.getAttributeValue(elt, "IsSave");
		if (Validate.isNullOrEmpty(fieldName))
		{
			member.setFieldName(fieldName);
		}
		if (Validate.isNullOrEmpty(allowNull))
		{
			member.setAllowNull(new Boolean(allowNull));
		}
		if (Validate.isNullOrEmpty(length))
		{
			member.setLength(new Integer(length));
		}
		if (Validate.isNullOrEmpty(primaryKey))
		{
			member.setPrimaryKey(new Boolean(primaryKey));
		}
		if (Validate.isNullOrEmpty(dbType))
		{
			member.setDatabaseType(Convert.ToDatabaseType(dbType));
		}
		if (Validate.isNullOrEmpty(isSave))
		{
			member.setIsSave(new Boolean(isSave));
		}
	}

	private static Map<String, IMemberAttribute> reflexAlbianObjectMembers(
			String type)
	{
		Map<String, IMemberAttribute> map = new LinkedHashMap<String, IMemberAttribute>();
		PropertyDescriptor[] propertyDesc = ReflectionHelper
				.getBeanPropertyDescriptors(type);
		for (PropertyDescriptor p : propertyDesc)
		{
			IMemberAttribute member = reflexAlbianObjectMember(p);
			map.put(member.getName(), member);
		}
		return map;
	}

	private static IMemberAttribute reflexAlbianObjectMember(
			PropertyDescriptor propertyDescriptor)
	{
		IMemberAttribute member = new MemberAttribute();
		if ("id".equals(propertyDescriptor.getName()))
		{
			member.setAllowNull(false);
			member.setDatabaseType(Convert.ToDatabaseType(propertyDescriptor
					.getPropertyType()));
			member.setFieldName(propertyDescriptor.getName());
			member.setIsSave(true);
			member.setLength(36);
			member.setPrimaryKey(true);
			member.setName(propertyDescriptor.getName());
			return member;
		}
		if ("isAlbianNew".equals(propertyDescriptor.getName()))
		{
			member.setIsSave(false);
			member.setName(propertyDescriptor.getName());
		}
		member.setAllowNull(true);
		member.setDatabaseType(Convert.ToDatabaseType(propertyDescriptor
				.getPropertyType()));
		member.setFieldName(propertyDescriptor.getName());
		member.setIsSave(true);
		member.setLength(200);
		member.setPrimaryKey(false);
		member.setName(propertyDescriptor.getName());
		return member;
	}
}
