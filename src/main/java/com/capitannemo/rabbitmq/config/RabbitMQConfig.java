package com.capitannemo.rabbitmq.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/***
 * RabbitMQConfig
 * This class is responsible for configuring RabbitMQ components such as queues, exchanges, and bindings.
 * It uses Spring's @Configuration annotation to enable bean creation and dependency injection.
 * The configuration is based on the provided properties for queue name, exchange name, and routing key.
 *
 * @author CapitanNemo
 * @since 1.0.0
 */
@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue.name}")
    private String queue;

    @Value("${rabbitmq.queue.json.name}")
    private String jsonQueue;

    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Value("${rabbitmq.routing.key}")
    private String routingKey;

    @Value("${rabbitmq.routing.json.key}")
    private String routingJsonkey;

    /**
     * Creates a RabbitMQ queue bean with the name specified in the 'rabbitmq.queue.name' property.
     * @return a RabbitMQ queue bean
     */
    @Bean
    public Queue queue() {
        return new Queue(queue);
    }

    /**
     * Creates a RabbitMQ queue bean with the name specified in the 'rabbitmq.queue.json.name' property.
     * Spring bean for queue (store json messages)
     * @return a RabbitMQ queue bean
     */
    @Bean
    public Queue jsonQueue() {
        return new Queue(jsonQueue);
    }

    /**
     * Creates a RabbitMQ topic exchange bean with the name specified in the 'rabbitmq.exchange.name' property.
     * @return a RabbitMQ topic exchange bean
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange);
    }

    /**
     * Binds the queue to the exchange using the routing key specified in the 'rabbitmq.routing.key' property.
     * @return a RabbitMQ binding bean
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(routingKey);
    }

    /**
     * Binds the queue to the exchange using the routing key specified in the 'rabbitmq.routing.json.key' property.
     * Binding between json queue and exchanges routing key
     * @return a RabbitMQ binding bean
     */
    @Bean
    public Binding jsonBinding() {
        return BindingBuilder
                .bind(jsonQueue())
                .to(exchange())
                .with(routingJsonkey);
    }

    /***
     * MessageConverter
     * jsonMessageConverter
     * @return MessageConverter
     */
    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    /***
     * ConnectionFactory
     * RabbitTemplate
     * amqpTemplate
     * @param connectionFactory ConnectionFactory
     * @return AmqpTemplate
     */
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
    // RabbitAdmin
}