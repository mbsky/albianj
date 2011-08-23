package albianj.main;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import org.albianj.io.Path;
import org.albianj.kernel.AlbianBootService;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.kernel.AlbianState;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.persistence.PersistenceService;
import org.albianj.persistence.impl.persistence.ReflectionHelper;
import org.albianj.persistence.impl.service.AlbianPersistenceService;
import org.albianj.persistence.impl.storage.StorageService;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.impl.FreeAlbianObject;
import org.albianj.runtime.IStackTrace;
import org.albianj.runtime.RuningTrace;
import org.albianj.xml.IParser;
import org.apache.commons.dbcp.DelegatingStatement;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import albianj.objects.ILogInfo;
import albianj.objects.IUser;
import albianj.objects.LogInfo;
import albianj.objects.User;

import com.mysql.jdbc.Statement;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;

public class Main
{

	/**
	 * @param args
	 */

	static Logger logger = Logger.getLogger(Main.class.getName());

	public static void main(String[] args)
	{
		try
		{
			
//			 DOMConfigurator.configure(Path.getExtendResourcePath("../config/log4_copy.xml"));
//			 DOMConfigurator.configure(Path.getExtendResourcePath("../config/log4j.xml"));
//			 logger.info("aasdsadasd");
			// int i = 0;
			// System.out.print(
			// Connection conn = PoolingDriverTest.getConnection();
			// DelegatingStatement stmt = (DelegatingStatement)
			// conn.createStatement();
			// ResultSet rs = stmt.executeQuery("SELECT * FROM user_1");
			// System.out.println(rs.getMetaData().getColumnCount());
			// PoolingDriverTest.Test();

			// // Field[] fields =
			// ReflectionHelper.getFields("albianj.main.Order");
			// PropertyDescriptor[] beans =
			// ReflectionHelper.getBeanPropertyDescriptors("albianj.main.Order");
			// for(PropertyDescriptor b : beans)
			// {
			// System.out.printf(b.getPropertyType().getSimpleName());
			// // System.out.printf(b.getName());
			// // System.out.printf(b.);
			//
			// }

			// for(Field field : fields)
			// {
			// // field.
			// }
			//
			// IParser parser = new PersistenceParser();
			// parser.init();
			//
			// // AlbianBootService.class.

			// IStackTrace trace = RuningTrace.getThreadTraceInfo();
			// System.out.println(trace.toString());
			// throw new Exception();
			AlbianBootService.start();
			while (AlbianState.Running != AlbianBootService.getLifeState())
			{
				Thread.sleep(1000);
			}
			IAlbianLoggerService logger = AlbianServiceRouter.getService(
					IAlbianLoggerService.class, "logger");
			logger.info("i", "heat", "java");

			try
			{
				ILogInfo log = new LogInfo();
				log.setContent("add the user");
				log.setCreateTime(new Date());
				log.setId(UUID.randomUUID().toString());
				log.setCreator("seapeak");
				log.setRemark("remark");

				IUser user = new User();
				user.setCreateTime(new Date());
				user.setCreator("seapeak");
				user.setId(UUID.randomUUID().toString());
				user.setLastModifier("Seapeak");
				user.setLastMofidyTime(new Date());
				user.setMail("xvhfeng@126.com");
				user.setMobile("13611644029");
				user.setPassword("password");
				user.setUserName("xvhfeng");

				Vector<IAlbianObject> list = new Vector<IAlbianObject>();
				list.add(log);
				list.add(user);
				boolean isSuccess = AlbianPersistenceService.create(list);
				if (isSuccess)
				{
					logger.info("success");
				}
			}
			catch (Exception e)
			{
				logger.error(e, "fail");
			}
			//
			// Field[] fields = getField(Order.class);
			// for(Field f : fields)
			// {
			// logger.info(f.getName());
			// }
			//
			// Field[] fields2 = getField2(FreeAlbianObject.class);
			// for(Field f : fields)
			// {
			// logger.info(f.getName());
			// }
			//
			// Field[] fields3 = getSuperField(Order.class);
			// for(Field f : fields)
			// {
			// logger.info(f.toString());
			// }
			//
			// boolean is = IAlbianObject.class.isAssignableFrom(Order.class);
			// if(is)
			// {
			//
			// logger.info("true");
			//
			// }
			// else
			// {
			// logger.info("false");
			//
			// }
			//
			//
			// return;
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
			IStackTrace trace = RuningTrace.getTraceInfo(e);
			System.out.println(trace.toString());
		}

		// TODO Auto-generated method stub
		// String path = null;
		// try
		// {
		// //
		// DOMConfigurator.configure(Path.getExtendResourcePath("../config/log4j.xml"));
		// logger.info("aasdsadasd");
		// // try
		// // {
		// //// Document doc =
		// XmlParser.load(Path.getExtendResourcePath("../config/service.xml"));
		// //// List nodes = XmlParser.analyze(doc, "Services/Service");
		// //// Iterator it=nodes.iterator();
		// //// while(it.hasNext()){
		// //// Element ele=(Element)it.next();
		// //// //System.out.println(ele.getText());
		// //// String base = XmlParser.getAttributeValue(ele, "Base");
		// //// System.out.println(base);
		// //// logger.info(base);
		// // }
		// return;
		// }
		// catch (Exception e)
		// {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
		// catch (IOException e)
		// {
		//
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// catch (URISyntaxException e)
		// {
		//
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// System.out.println(path);
		runThreadTrace();
		try
		{
			Thread.sleep(5000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;

	}

	public static void runThreadTrace()
	{
		IStackTrace trace = RuningTrace.getThreadTraceInfo();
		System.out.println("run1" + trace.toString());
		IStackTrace trace1 = RuningTrace.getTraceInfo();
		System.out.println("run2" + trace1.toString());
	}

	public static <T extends IAlbianObject> T Load(Class<T> cla)
	{
		try
		{
			T obj = cla.newInstance();
			return obj;

		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static <T extends IAlbianObject> List<T> Load(Class<T> cla,
			String routerName)
	{
		List<T> test = new ArrayList<T>();
		T obj = null;
		try
		{
			obj = cla.newInstance();
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.add(obj);
		return test;
	}

	public static <T extends IAlbianObject> PropertyDescriptor[] Test(
			Class<T> cla) throws Exception
	{
		BeanInfo bi = Introspector.getBeanInfo(cla, Object.class);
		PropertyDescriptor[] propDesc = bi.getPropertyDescriptors();
		// MethodDescriptor[]
		return propDesc;
	}

	public static <T extends IAlbianObject> MethodDescriptor[] TestMethod(
			Class<T> cla) throws Exception
	{
		BeanInfo bi = Introspector.getBeanInfo(cla, Object.class);
		MethodDescriptor[] propDesc = bi.getMethodDescriptors();
		// MethodDescriptor[]
		// cla.isAssignableFrom(cls)
		return propDesc;
	}

	public static <T extends IAlbianObject> Field[] getField(Class<T> cla)
	{
		return cla.getDeclaredFields();
	}

	public static <T extends IAlbianObject> Field[] getField2(Class<T> cla)
	{
		return cla.getFields();
	}

	public static <T extends IAlbianObject> Field[] getSuperField(Class<T> cla)
	{
		return cla.getSuperclass().getFields();
	}

}
