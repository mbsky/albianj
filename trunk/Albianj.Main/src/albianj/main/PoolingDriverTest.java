package albianj.main;

//import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.ConnectionFactory;
import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.dbcp.PoolableConnectionFactory;
import org.apache.commons.dbcp.PoolingDriver;
import org.apache.commons.pool.ObjectPool;
import org.apache.commons.pool.impl.GenericObjectPool;

public class PoolingDriverTest
{

	// 一些common-dbcp内部定义的protocol
	private static final String POOL_DRIVER_KEY = "jdbc:apache:commons:dbcp:";
	private static final String POLLING_DRIVER = "org.apache.commons.dbcp.PoolingDriver";

	/**
	 * 取得池化驱动器
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private static PoolingDriver getPoolDriver() throws ClassNotFoundException,
			SQLException
	{
		Class.forName(POLLING_DRIVER);
		return (PoolingDriver) DriverManager.getDriver(POOL_DRIVER_KEY);
	}

	/**
	 * 销毁所有连接
	 * 
	 * @throws Exception
	 */
	public static void destory() throws Exception
	{
		PoolingDriver driver = getPoolDriver();
		String[] names = driver.getPoolNames();
		for (String name : names)
		{
			driver.getConnectionPool(name).close();
		}
	}

	/**
	 * 从连接池中获取数据库连接
	 */
	public static Connection getConnection() throws Exception
	{
		String key = "StorageName";

		PoolingDriver driver = getPoolDriver();

		// driver.

		ObjectPool pool = null;

		ConnectionFactory connectionFactory = null;

		String url = "jdbc:mysql://localhost/baseinfo?user=root&password=xuhf&useUnicode=true&characterEncoding=8859_1";
		Class.forName("com.mysql.jdbc.Driver");
		connectionFactory = new DriverManagerConnectionFactory(url, null);

		// 构造连接池
		ObjectPool connectionPool = new GenericObjectPool(null);

		new PoolableConnectionFactory(connectionFactory, connectionPool, null,
				null, false, false);

		// 将连接池注册到driver中
		driver.registerPool(key, connectionPool);

		// 从连接池中拿一个连接
		return DriverManager.getConnection(POOL_DRIVER_KEY + key);
	}

	public static DataSource setupDataSource()
	{
		BasicDataSource ds = new BasicDataSource();
		try
		{
			String url = "jdbc:mysql://localhost/baseinfo?useUnicode=true&characterEncoding=8859_1";
			// Properties cfgpp = new Properties();
			// cfgpp.load(DbcpDataSourceExample.class
			// .getResourceAsStream(cfgFileName));
			ds.setDriverClassName("com.mysql.jdbc.Driver");
			ds.setUrl(url);
			ds.setUsername("root");
			ds.setPassword("xuhf");
			ds.setDefaultAutoCommit(false);
			ds.setDefaultReadOnly(false);
			ds.setDefaultTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
			ds.setInitialSize(5);
			ds.setMaxIdle(20);
			ds.setMinIdle(5);
			ds.setMaxWait(6000);
			ds.setRemoveAbandoned(true);
			ds.setRemoveAbandonedTimeout(300);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}

		return ds;
	}

	public static void Test()
	{
		String testSql = "select * from user_2";
		String cfgFileName = "dbcp.jdbc.properties";

		System.out.println("Setting up data source.");
		DataSource dataSource = setupDataSource();
		System.out.println("dataSource Done.");

		printDataSourceStats(dataSource);

		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;

		try
		{
			System.out.println("Creating connection start.");
			conn = dataSource.getConnection();

			System.out.println("Creating statement start.");
			stmt = conn.createStatement();

			System.out.println("Executing statement start.");
			rset = stmt.executeQuery(testSql);

			System.out.println("executeQuery Results:");
			int numcols = rset.getMetaData().getColumnCount();

			while (rset.next())
			{
				for (int i = 1; i <= numcols; i++)
				{
					System.out.print("\t" + rset.getString(i));
				}
				System.out.println("");
			}
			System.out.println("Results display done.");
			printDataSourceStats(dataSource);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (rset != null) rset.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				if (stmt != null) stmt.close();
			}
			catch (Exception e)
			{
			}
			try
			{
				if (conn != null) conn.close();
				// shutdownDataSource(dataSource);
			}
			catch (Exception e)
			{
			}
			printDataSourceStats(dataSource);
		}
	}

	public static void printDataSourceStats(DataSource ds)
	{
		BasicDataSource bds = (BasicDataSource) ds;
		System.out.println("NumActive: " + bds.getNumActive());
		System.out.println("NumIdle: " + bds.getNumIdle());
	}

	   public static void shutdownDataSource(DataSource ds) throws SQLException {
	        BasicDataSource bds = (BasicDataSource) ds;
	        bds.close();
	    }
}
