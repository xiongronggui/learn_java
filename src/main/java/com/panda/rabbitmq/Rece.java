package com.panda.rabbitmq;

import com.rabbitmq.client.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.script.SimpleBindings;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class Rece
{
    private  final static String QUEUE_NAME = "RabbitMQ_Queue";

    @RequestMapping("revice")
    public String  index() throws IOException, TimeoutException,InterruptedException
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.1.142");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("123456");

        Connection connection = factory.newConnection(QUEUE_NAME);
        Channel channel = connection.createChannel();
        //channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        String message = "";
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME,false,consumer);
        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            message += new String(delivery.getBody());
            System.out.println(new String(delivery.getBody()));
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        }
    }
}
