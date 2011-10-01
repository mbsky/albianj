package org.albianj.config.publish;

import org.albianj.config.entity.ConfigServiceConstants;
import org.albianj.config.entity.IConfigServerAttribute;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class ConfigPublish
{
	private Connection connection;
	private Channel channel;
	
	public void startup(IConfigServerAttribute server,boolean durable)
	{
		try
		{
			ConnectionFactory factory=new ConnectionFactory();
	        factory.setHost(server.getAddress());
	        factory.setPort(server.getPort());
	        factory.setVirtualHost(ConfigServiceConstants.VIRTUALHOST);
	        factory.setUsername(server.getUserName());
	        factory.setPassword(server.getPassword());
	        connection = factory.newConnection();
	        Channel channel=connection.createChannel();
	        channel.exchangeDeclare(ConfigServiceConstants.EXCHANGE_NAME, ConfigServiceConstants.EXCHANGE_TYPE); //����exchange,�Լ�����
        	channel.queueDeclare(ConfigServiceConstants.EXCHANGE_NAME, durable, false, false, null); //������Ϣ���У���Ϊ�ɳ־û���
		}
		catch(Exception exc)
		{
			
		}
	}
	
	public void publish(String msg)
	{
		try
		{
	        channel.basicPublish(ConfigServiceConstants.EXCHANGE_NAME, "", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes()); //����Ϣ���˶���
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void stop()
	{
		try
		{
			channel.close();
//	        connection.close();
		}
		catch(Exception e)
		{
			
		}
		try
		{
//			channel.close();
	        connection.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
