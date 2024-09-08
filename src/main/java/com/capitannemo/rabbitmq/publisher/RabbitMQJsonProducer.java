package com.capitannemo.rabbitmq.publisher;

import com.capitannemo.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/***
 * RabbitMQJsonProducer
 *
 * @author Capitannemo
 * @since 1.0.0
 */
@Service
public class RabbitMQJsonProducer {
    @Value("${rabbitmq.exchange.name}")
    private String exchange;
    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonKey;

    /**
     * Configuration logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQJsonProducer.class);

    /**
     * Instantiates a RabbitMQProducer
     */
    private final RabbitTemplate rabbitTemplate;

    /***
     * RabbitMQJsonProducer
     * Constructor for RabbitTemplate instance
     * @param rabbitTemplate RabbitTemplate
     */
    public RabbitMQJsonProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * Sends a Json message to the specified RabbitMQ exchange using the provided routing key.
     *
     * @param user The DTO json message deserialization to be sent.
     */
    public void sendJsonMessage(User user) {
        LOGGER.info(String.format("Json message sent -> %s", user.toString()));
        rabbitTemplate.convertAndSend(exchange, routingJsonKey, user);
    }
}
