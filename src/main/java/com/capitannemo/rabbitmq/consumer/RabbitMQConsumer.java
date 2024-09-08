package com.capitannemo.rabbitmq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/***
 * RabbitMQConsumer
 * -
 * Clase consumer
 * @author Capitannemo
 * @since 1.0.0
 */
@Service
public class RabbitMQConsumer {
    private static final Logger LOG = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.queue.name}"})
    private void consume(String message) {
        LOG.info(String.format("Received message: {%s}", message));
    }
}
