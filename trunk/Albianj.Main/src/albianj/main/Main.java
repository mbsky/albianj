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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.concurrent.ThreadPoolExecutor;

import net.rubyeye.xmemcached.MemcachedClient;

import org.albianj.algorithm.Hash;
import org.albianj.cache.ILocalCached;
import org.albianj.cached.service.ICacheService;
import org.albianj.io.Path;
import org.albianj.kernel.AlbianBootService;
import org.albianj.kernel.AlbianServiceRouter;
import org.albianj.kernel.AlbianState;
import org.albianj.logger.AlbianLoggerService;
import org.albianj.logger.IAlbianLoggerService;
import org.albianj.persistence.impl.context.IReaderJob;
import org.albianj.persistence.impl.context.IReaderJobAdapter;
import org.albianj.persistence.impl.context.ReaderJobAdapter;
import org.albianj.persistence.impl.db.IQueryScope;
import org.albianj.persistence.impl.db.QueryScope;
import org.albianj.persistence.impl.persistence.PersistenceService;
import org.albianj.persistence.impl.persistence.ReflectionHelper;
import org.albianj.persistence.impl.service.AlbianPersistenceService;
import org.albianj.persistence.impl.service.FilterCondition;
import org.albianj.persistence.impl.storage.StorageService;
import org.albianj.persistence.object.IAlbianObject;
import org.albianj.persistence.object.IFilterCondition;
import org.albianj.persistence.object.LogicalOperation;
import org.albianj.persistence.object.RelationalOperator;
import org.albianj.persistence.object.impl.FreeAlbianObject;
import org.albianj.runtime.IStackTrace;
import org.albianj.runtime.RuningTrace;
import org.albianj.security.Coder;
import org.albianj.security.DESCoder;
import org.albianj.security.MACStyle;
import org.albianj.service.AlbianIdService;
import org.albianj.service.parser.IParser;
import org.apache.commons.dbcp.DelegatingStatement;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import albianj.objects.ILogInfo;
import albianj.objects.IUser;
import albianj.objects.LogInfo;
import albianj.objects.Node;
import albianj.objects.User;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mysql.jdbc.Statement;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class Main
{

	/**
	 * @param args
	 */

//	static Logger logger = Logger.getLogger(Main.class.getName());

    private static final String exchange_name="my_exchange";
    private static final String exchange_type="fanout";
	
	public static void main(String[] args)
	{
		try
		{
//			new Runnable()
//			{
//				public void run()
//				{
//					try
//					{
//						Thread.sleep(30 * 1000);
//						ConnectionFactory factory=new ConnectionFactory();
//				        factory.setHost("localhost");
//				        factory.setVirtualHost("my_mq");
//				        factory.setUsername("xvhfeng");
//				        factory.setPassword("5035");
//				        com.rabbitmq.client.Connection connection = factory.newConnection();//.newConnection();
//				        com.rabbitmq.client.Channel channel=connection.createChannel();
//				        channel.exchangeDeclare(exchange_name, exchange_type); //声明exchange,以及类型
//				        String message="This mesaage is just for test!"+Math.random();
//				        channel.basicPublish(exchange_name, "", null, message.getBytes()); //将消息绑定了队列
//				        System.out.println("Message send:"+message);
//				        channel.close();
//				        connection.close();
//					}catch(Exception e)
//					{
//						e.printStackTrace();
//					}
//				}
//			}.run();
			
			
			
	        ConnectionFactory clientFactory=new ConnectionFactory();
	        clientFactory.setHost("localhost");
	        clientFactory.setVirtualHost("my_mq");
	        clientFactory.setUsername("xvhfeng");
	        clientFactory.setPassword("5035");
	        com.rabbitmq.client.Connection clientConnection=clientFactory.newConnection();
	        com.rabbitmq.client. Channel clientChannel=clientConnection.createChannel();
	        clientChannel.exchangeDeclare(exchange_name, exchange_type);
	        clientChannel.basicQos(1); 
	        //创建两个队列，将它们都绑定到同一个exchange
	        String queue_name1=clientChannel.queueDeclare().getQueue();
	        clientChannel.queueBind(queue_name1, exchange_name, "");
	        String queue_name2=clientChannel.queueDeclare().getQueue();
	        clientChannel.queueBind(queue_name2, exchange_name, "");
	        System.out.println("Wait for message received!");
	        //创建两个消费者，分别与两个队列相关联
	        QueueingConsumer consumer1=new QueueingConsumer(clientChannel);
	        clientChannel.basicConsume(queue_name1, true, consumer1);
	        QueueingConsumer consumer2=new QueueingConsumer(clientChannel);
	        clientChannel.basicConsume(queue_name2,true, consumer2);
	        //两个消费者分别从各自的队列里面收取消息
	        int i = 0;
	        while(i == 0){
	            QueueingConsumer.Delivery deliver=consumer1.nextDelivery();
	            String clientMsg=new String(deliver.getBody());
	            System.out.println("Message received[consumer1]:"+clientMsg);
	            QueueingConsumer.Delivery deliver1=consumer2.nextDelivery();
	            String clientMsg1=new String(deliver1.getBody());
	            System.out.println("Message received[consumer2]:"+clientMsg1);
	            Thread.sleep(500);
	        }
	        
//			Node node = new Node();
//			node.setEnable(true);
//			node.setName("OrderSwitch");
//			node.setValue("true");
//			Map<String,Node> childNodes = new HashMap<String,Node>();
//			Node childNode = new Node(); 
//			childNode.setEnable(true);
//			childNode.setName("BizofferSwitch");
//			childNode.setValue("false");
//			childNodes.put("BizofferSwitch",childNode);
//			node.setChildNodes(childNodes);
//			Gson gson = new Gson();
//			String json = gson.toJson(node);
//			System.out.println(json);
			
			Mongo mongo = new Mongo( "127.0.0.1" , 20000);
			DB db = mongo.getDB("test");
			
			DBCollection coll = db.getCollection("tt");

			Node node = new Node();
			node.setId(AlbianIdService.generate32UUID());
			node.setEnable(true);
			node.setForefatherKey("root");
			node.setName("test");
			node.setValue("open");
			coll.insert(node);
			
//			doc.values().add(e)

			
//			coll.update(doc,doc,true,false);
			//coll.insert(doc);
//			coll.up
//			coll.getWriteConcern().
			coll.setObjectClass(Node.class);
			Node nn =(Node) coll.findOne();
			nn.setEnable(false);
			coll.save(nn);
			Node nn1 =(Node) coll.findOne();
			System.out.println(nn1);
//			myDoc.containsField("test");
//			System.out.println(myDoc);
//			System.out.println(myDoc);

//			String v1 = Coder.encryptMD5("Seapeak");
//			String v2 = Coder.encryptSHA("Seapeak");
//			System.out.println(v1);
//			System.out.println(v2);
//			String vv = Coder.encryptHMAC("key", MACStyle.SHA256, "data");
//			System.out.println(vv);
//			String v3 = DESCoder.encrypt("Seapeak");
//			System.out.println(v3);
//			String v4 = DESCoder.decrypt(v3);
//			System.out.println(v4);
//			String v5 = DESCoder.encrypt("key", "Seapeak");
//			String v6 = DESCoder.decrypt("key", v5);
//			System.out.println(v5);
//			System.out.println(v6);
//			
//			int value = Hash.hashPJW("seapeak");
//			System.out.println(value);
			
			
//			ThreadPoolExecutor pool = new ThreadPoolExecutor();
			
			
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
//			IAlbianLoggerService logger = AlbianServiceRouter.getService(
//					IAlbianLoggerService.class, "logger");
			AlbianLoggerService.debug("%s %s %s","i", "heat", "java");
			
//			ICacheService service = AlbianServiceRouter.getService(ICacheService.class, "CacheService");
//			MemcachedClient client = service.getRemoterClient("list");
//			client.add("key", 1000, "value");
//			String value = client.get("key");
//			System.out.println(value);
//			
//			ILocalCached cache = service.getLocalCache("Single");
//			cache.put("kkkk", "i am success.");
//			String vvv = (String) cache.get("kkkk");
//			System.out.println(vvv);
			
//			Thread.sleep(10 * 1000);

			try
			{
				String key = AlbianIdService.generate("dd7777777777");
				AlbianLoggerService.info(key);
				AlbianLoggerService.info("%d",key.length());
//				return;
//				return;
//				ILogInfo log = new LogInfo();
//				log.setContent("add the user");
//				log.setCreateTime(new Date());
//				log.setId(UUID.randomUUID().toString());
//				log.setCreator("seapeak");
////				log.setRemark("remark");
//				log.setStyle(log.LOGGER_USER_STYLE);
//
//				IUser user = new User();
//				user.setCreateTime(new Date());
//				user.setCreator("seapeak");
//				user.setId("aaaaa");
//				user.setLastModifier("Seapeak");
//				user.setLastMofidyTime(new Date());
//				user.setMail("xvhfeng@126.com");
//				user.setMobile("13611644029");
//				user.setPassword("password");
//				user.setUserName("xvhfeng");
//				user.setNickname("nickname");
//				user.setRegistrDate(new Date());
//
//				Vector<IAlbianObject> list = new Vector<IAlbianObject>();
//				list.add(log);
//				list.add(user);
//				boolean isSuccess = AlbianPersistenceService.create(list);
//				if (isSuccess)
//				{
//					logger.info("success");
//				}
//				
//				//96791e91-1864-4840-8279-dd378b8f
				IReaderJobAdapter adapter = new ReaderJobAdapter();
				IFilterCondition filter = new FilterCondition();
				filter.setFieldName("id");
				filter.setFieldClass(String.class);
				filter.setLogicalOperation(LogicalOperation.Equal);
				filter.setRelationalOperator(RelationalOperator.And);
				filter.setValue("aaaa");
				IFilterCondition[] filters = new IFilterCondition[1];
				filters[0] = filter;
				IReaderJob job = adapter.buildReaderJob(User.class, "", 0, 0,filters, null);
				IQueryScope scope = new QueryScope();
				List<User> users = scope.execute(User.class, job);
				return;
				
			}
			catch (Exception e)
			{
				AlbianLoggerService.error(e, "fail");
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
