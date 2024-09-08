package com.capitannemo.rabbitmq.consumer;

import com.capitannemo.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/***
 * RabbitMQJsonConsumer
 * -
 * Clase Json consumer
 * @author Capitannemo
 * @since 1.0.0
 */
@Service
public class RabbitMQJsonConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQJsonConsumer.class);

    /***
     * consume
     * @param user Json User message to consume
     */
    @RabbitListener(queues = {"${rabbitmq.queue.json.name}"})
    private void consume(User user) {
        LOG.info(String.format("Received message: {%s}", user.toString()));
    }
}
