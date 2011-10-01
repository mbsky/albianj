package org.albianj.config.subscribe;

import org.albianj.config.entity.ConfigServiceConstants;
import org.albianj.config.entity.IConfigServerAttribute;
import org.albianj.kernel.KernelSetting;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class ConfigSubscribe
{
	private Connection clientConnection;
	private Channel clientChannel;
	private QueueingConsumer consumer;

	public void startup(IConfigServerAttribute server, boolean durable)
	{
		try
		{
			ConnectionFactory clientFactory = new ConnectionFactory();
			clientFactory.setHost(server.getAddress());
			clientFactory.setPort(server.getPort());
			clientFactory.setVirtualHost(ConfigServiceConstants.VIRTUALHOST);
			clientFactory.setUsername(server.getUserName());
			clientFactory.setPassword(server.getPassword());
			clientConnection = clientFactory.newConnection();
			clientChannel = clientConnection.createChannel();
			clientChannel.exchangeDeclare(ConfigServiceConstants.EXCHANGE_NAME,
					ConfigServiceConstants.EXCHANGE_TYPE);
			clientChannel.basicQos(1);
			String queueName = "Albian_Config_" + KernelSetting.getAppName()+"_" + KernelSetting.getKernelId();
			clientChannel.queueDeclare(queueName, true, false, false, null);
			clientChannel.queueBind(queueName,
					ConfigServiceConstants.EXCHANGE_NAME, "");
			consumer = new QueueingConsumer(clientChannel);
			clientChannel.basicConsume(queueName, true, consumer);
		}
		catch (Exception exc)
		{

		}
	}

	public void subscribe(ISubscribeHandler handler)
	{

		while (true)
		{
			try
			{
				QueueingConsumer.Delivery deliver = consumer.nextDelivery();
				String clientMsg = new String(deliver.getBody());
				handler.dealHandler(clientMsg);
				clientChannel.basicAck(deliver.getEnvelope().getDeliveryTag(), false);
				Thread.sleep(500);
			}
			catch(Exception e)
			{
				
			}
		}
	}

	public void stop()
	{
		try
		{
			clientChannel.close();
		}
		catch(Exception e)
		{
			
		}
		try
		{
			clientConnection.close();
		}
		catch(Exception e)
		{
			
		}
	}
}
