package com.panda.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.jboss.logging.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

@RestController
public class SendController
{
    private  final  static String QUEUE_NAME = "RabbitMQ_Queue";

    @RequestMapping("/send")
    public String index(String str) throws IOException,TimeoutException
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.142");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");
        Connection connection = null;
        connection = factory.newConnection();

        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String message = "hello "+str+"_"+(new Date()).toString();
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());

        channel.close();
        connection.close();
        return message;
    }
}
