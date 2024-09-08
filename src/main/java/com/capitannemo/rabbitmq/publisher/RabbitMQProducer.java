package com.capitannemo.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/***
 * RabbitMQProducer
 * @author CapitanNemo
 * @since 1.0.0
 */
@Service
public class RabbitMQProducer {

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    /**
     * Configuration logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQProducer.class);

    /**
     * Instantiates a RabbitMQProducer
     */
    private final RabbitTemplate rabbitTemplate;

    /**
     * RabbitMQProducer
     * Constructor for RabbitTemplate instance
     * @param rabbitTemplate RabbitTemplate
     */
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Sends a message to the specified RabbitMQ exchange using the provided routing key.
     *
     * @param message The message to be sent.
     */
    public void sendMessage(String message) {
        LOGGER.info(String.format("Message sent -> %s", message));
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
