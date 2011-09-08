package org.albianj.persistence.impl.db;

import java.util.HashMap;
import java.util.Map;

import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IAlbianObjectAttribute;
import org.albianj.persistence.object.IMemberAttribute;
import org.albianj.persistence.object.IRoutingAttribute;
import org.albianj.persistence.object.IRoutingsAttribute;
import org.albianj.verify.Validate;

public class ModifyCommandAdapter implements IUpdateCommand
{

	@Override
	public ICommand builder(IAlbianObject object, IRoutingsAttribute routings,
			IAlbianObjectAttribute albianObject, Map<String, Object> mapValue,
			IRoutingAttribute routing)
	{

		ICommand cmd = new Command();
		StringBuilder text = new StringBuilder();
		StringBuilder cols = new StringBuilder();
		StringBuilder where = new StringBuilder();
		text.append("UPDATE ");// .append(routing.getTableName());
		String tableName = null;
		if (null != routings && null != routings.getHashMapping())
		{
			tableName = routings.getHashMapping().mappingWriterTable(routing,
					object);
		}
		tableName = Validate.isNullOrEmptyOrAllSpace(tableName) ? routing
				.getTableName() : tableName;
		text.append(tableName);

		Map<String, IMemberAttribute> mapMemberAttributes = albianObject
				.getMembers();
		Map<String, ISqlParameter> sqlParas = new HashMap<String, ISqlParameter>();
		for (Map.Entry<String, IMemberAttribute> entry : mapMemberAttributes
				.entrySet())
		{
			IMemberAttribute member = entry.getValue();
			if (!member.getIsSave()) continue;
			ISqlParameter para = new SqlParameter();
			para.setName(member.getName());
			para.setSqlFieldName(member.getSqlFieldName());
			para.setSqlType(member.getDatabaseType());
			para.setValue(mapValue.get(member.getName()));
			sqlParas.put(String.format("#%1$s#", member.getSqlFieldName()),
					para);

			if (member.getPrimaryKey())
			{
				where.append(" AND ").append(member.getSqlFieldName())
						.append(" = ").append("#")
						.append(member.getSqlFieldName()).append("# ");
			}
			else
			// cols
			{
				cols.append(member.getSqlFieldName()).append(" = ").append("#")
						.append(member.getSqlFieldName()).append("# ,");
			}
		}

		if (0 < cols.length())
		{
			cols.deleteCharAt(cols.length() - 1);
		}
		text.append(" SET ").append(cols).append(" WHERE 1=1 ").append(where);
		cmd.setCommandText(text.toString());
		cmd.setCommandType(CommandType.Text);
		cmd.setParameters(sqlParas);
		NameSqlParameter.parseSql(cmd);
		return cmd;
	}

}
