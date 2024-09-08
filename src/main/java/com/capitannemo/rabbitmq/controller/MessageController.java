package com.capitannemo.rabbitmq.controller;

import com.capitannemo.rabbitmq.publisher.RabbitMQProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * MessageController Clase Controladora
 * -
 * RestController controller
 * RequestMappinp ruta de controlador
 * @author Capitannemo
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1")
public class MessageController {
    /**
     * Inyección de dependencia para RabbitMQProducer
     */
    private RabbitMQProducer producer;

    /**
     * Constructor de la clase con la inyección de dependencia
     * @param producer RabbitMQProducer
     */
    public MessageController(RabbitMQProducer producer) {
        this.producer = producer;
    }

    /**
     * Método para publicar un mensaje en RabbitMQ
     * @param message Mensaje a publicar
     * @return ResponseEntity con un mensaje de confirmación
     */
    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam("message") String message) {
        producer.sendMessage(message);
        return ResponseEntity.ok("Message sent successfully");
    }
}
