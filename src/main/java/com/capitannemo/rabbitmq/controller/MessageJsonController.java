package com.capitannemo.rabbitmq.controller;

import com.capitannemo.rabbitmq.dto.User;
import com.capitannemo.rabbitmq.publisher.RabbitMQJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * MessageJsonController Clase Controladora
 * -
 * RestController controller
 * RequestMapping ruta de controlador
 * @author Capitannemo
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1")
public class MessageJsonController {
    /**
     * Inyección de dependencia para RabbitMQJsonProducer
     */
    private RabbitMQJsonProducer jsonProducer;

    /**
     * Constructor de la clase con la inyección de dependencia
     * @param jsonProducer RabbitMQJsonProducer
     */
    public MessageJsonController(RabbitMQJsonProducer jsonProducer) {
        this.jsonProducer = jsonProducer;
    }

    /**
     * Método para publicar un json mensaje en RabbitMQ
     * @param user json a publicar
     * @return ResponseEntity con un mensaje de confirmación
     */
    @PostMapping("/publish/json")
    public ResponseEntity<String> sendMessage(@RequestBody User user) {
        jsonProducer.sendJsonMessage(user);
        return ResponseEntity.ok("Json Message sent successfully");
    }
}
