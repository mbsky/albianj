package org.albianj.io;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

//from shendl_s@hotmail.com,modify by Seapeak.Xu
public class Path
{

	public static ClassLoader getClassLoader()
	{

		return Path.class.getClassLoader();
	}
	
	public static ClassLoader getClassLoader(@SuppressWarnings("rawtypes") Class cla)
	{

		return cla.getClassLoader();// Path.class.getClassLoader();
	}
	
	public static String getAbsolutePathOfClassLoaderClassPath()
	{
		return getClassLoader().getResource("").toString();
	}
	
	public static String getAbsolutePathOfClassLoaderClassPath(@SuppressWarnings("rawtypes") Class cla)
	{
		return getClassLoader(cla).getResource("").toString();
	}

	public static String getExtendResourcePath(String relativePath)
			throws MalformedURLException, URISyntaxException
	{
		return getExtendResourcePath(Path.class,relativePath);
	}
	
	public static String getExtendResourcePath(@SuppressWarnings("rawtypes") Class cla,String relativePath) throws MalformedURLException, URISyntaxException
	{
		URL resourceAbsoluteURL = null;
		boolean isWindows = false;
		String system = System.getProperty("os.name");
		if(system.toLowerCase().contains("windows"))//start with '/'
		{
			isWindows = true;
		}
		String path = null;
		if (!relativePath.contains("../")) 
		{ 
			resourceAbsoluteURL = getResource(cla,relativePath);
		}
		else
		{
			String classPathAbsolutePath = getAbsolutePathOfClassLoaderClassPath(cla);
			if (relativePath.substring(0, 1).equals("/"))
			{
				relativePath = relativePath.substring(1);
			}
			String wildcardString = relativePath.substring(0,
					relativePath.lastIndexOf("../") + 3);
			relativePath = relativePath
					.substring(relativePath.lastIndexOf("../") + 3);
			int containSum = containSum(wildcardString, "../");
			classPathAbsolutePath = cutLastString(classPathAbsolutePath, "/",
					containSum);
			String resourceAbsolutePath = classPathAbsolutePath + relativePath;
			resourceAbsoluteURL = new URL(resourceAbsolutePath);
		}
		 if(null == resourceAbsoluteURL) throw new MalformedURLException("url is null!");
		 
		path = resourceAbsoluteURL.toURI().getPath();//encode not in gbk
		if(isWindows && path.startsWith("/"))
		{
			path = path.substring(1);
		}
		return path;	
	}

	private static int containSum(String source, String dest)
	{
		int containSum = 0;
		int destLength = dest.length();
		while (source.contains(dest))
		{
			containSum = containSum + 1;
			source = source.substring(destLength);

		}
		return containSum;
	}

	private static String cutLastString(String source, String dest, int num)
	{
		for (int i = 0; i < num; i++)
		{
			source = source.substring(0,
					source.lastIndexOf(dest, source.length() - 2) + 1);

		}
		return source;
	}

	public static URL getResource(String resource)
	{
		return getClassLoader().getResource(resource);
	}
	
	public static URL getResource(@SuppressWarnings("rawtypes") Class cla,String resource)
	{
		return getClassLoader(cla).getResource(resource);
	}
	

//	/**
//	 * ����Java�ࡣ ʹ��ȫ�޶�����
//	 * 
//	 * @paramclassName
//	 * @return
//	 */
//	public static Class loadClass(String className)
//	{
//		try
//		{
//			return getClassLoader().loadClass(className);
//		}
//		catch (ClassNotFoundException e)
//		{
//			throw new RuntimeException("class not found '" + className + "'", e);
//		}
////	}
//
//	/**
//	 * �õ��������
//	 * 
//	 * @return
//	 */
	
//	/**
//	 * �ṩ�����classpath����Դ·���������ļ���������
//	 * 
//	 * @paramrelativePath���봫����Դ�����·�����������classpath��·���������Ҫ����classpath�ⲿ����Դ����Ҫʹ��../������
//	 * @return �ļ�������
//	 * @throwsIOException
//	 * @throwsMalformedURLException
//	 */
//	public static InputStream getStream(String relativePath)
//			throws MalformedURLException, IOException
//	{
//		if (!relativePath.contains("../"))
//		{
//			return getClassLoader().getResourceAsStream(relativePath);
//
//		}
//		else
//		{
//			return getStreamByExtendResource(relativePath);
//		}
//
//	}

//	/**
//	 * 
//	 * @paramurl
//	 * @return
//	 * @throwsIOException
//	 */
//	public static InputStream getStream(URL url) throws IOException
//	{
//		return null != url ? url.openStream() : null;
//	}

//	/**
//	 * 
//	 * @paramrelativePath���봫����Դ�����·�����������classpath��·���������Ҫ����classpath�ⲿ����Դ����Ҫʹ��../������
//	 * @return
//	 * @throwsMalformedURLException
//	 * @throwsIOException
//	 */
//	public static InputStream getStreamByExtendResource(String relativePath)
//			throws MalformedURLException, IOException
//	{
//		return getStream(getExtendResource(relativePath));
//
//	}
//
//	/**
//	 * �ṩ�����classpath����Դ·�����������Զ�������һ��ɢ�б�
//	 * 
//	 * @paramresource
//	 * @return
//	 */
//	public static Properties getProperties(String resource)
//	{
//		Properties properties = new Properties();
//		try
//		{
//			properties.load(getStream(resource));
//		}
//		catch (IOException e)
//		{
//			throw new RuntimeException("couldn't load properties file '"
//					+ resource + "'", e);
//		}
//		return properties;
//	}


	

}
