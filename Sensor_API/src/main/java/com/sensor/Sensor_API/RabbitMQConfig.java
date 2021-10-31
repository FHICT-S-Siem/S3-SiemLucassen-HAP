package com.sensor.Sensor_API;

import com.sensor.Sensor_API.room.RoomRepository;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private static final String MY_QUEUE = "sensor_data";

    private final RoomRepository roomRepository;

    public RabbitMQConfig(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Bean
    Queue myQueue() {
        return new Queue(MY_QUEUE, true);
    }

    @Bean
    Exchange myExchange(){
        return ExchangeBuilder.topicExchange("")
                .durable(true)
                .build();
    }

    @Bean
    ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(System.getenv("HOST_URL"));
        cachingConnectionFactory.setUsername(System.getenv("RMQ_USERNAME"));
        cachingConnectionFactory.setPassword(System.getenv("RMQ_PASSWORD"));
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer(){
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(myQueue());
        simpleMessageListenerContainer.setMessageListener(new RabbitMQMessageListener(roomRepository));
        return simpleMessageListenerContainer;
    }
}
