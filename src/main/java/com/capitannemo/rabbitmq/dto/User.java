package com.capitannemo.rabbitmq.dto;

import lombok.Data;

/***
 * User
 * Class User implements serialization and deserialization
 *
 * @author Capitannemo
 * @since 1.0.0
 */
@Data
public class User {
    /** id */
    private int id;
    /** firstName */
    private String firstName;
    /** lastName */
    private String lastName;
}
