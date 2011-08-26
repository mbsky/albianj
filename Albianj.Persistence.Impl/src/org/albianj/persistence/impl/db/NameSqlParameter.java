package org.albianj.persistence.impl.db;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameSqlParameter
{
	/**
	 * �������������������SQL��䡣ʹ��Map�洢������Ȼ�󽫲����滻��? <br/>
	 * ���ߣ�wallimn��ʱ�䣺2009-1-8������12:14:10<br/>
	 * �ʼ���wallimn@sohu.com<br/>
	 * ���ͣ�http://blog.csdn.net/wallimn<br/>
	 * ������<br/>
	 * 
	 * @param sql
	 * @return
	 */
	public static void parseSql(ICommand cmd)
	{
		String regex = "#\\w+#";//insert into tablename(col1,col2) values( #col1#,#col2#)
		String cmdText = cmd.getCommandText();
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(cmdText);
		Map<Integer, String> paramsMap = new HashMap<Integer, String>();
		
		int idx = 1;
		while (m.find())
		{
			// �������ƿ������ظ���ʹ���������Key
			paramsMap.put(new Integer(idx++), m.group());
		}
		cmdText = cmdText.replaceAll(regex, "?");
		cmd.setCommandText(cmdText);
		cmd.setParameterMapper(paramsMap);
		return;
	}
}
