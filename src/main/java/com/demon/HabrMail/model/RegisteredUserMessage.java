package com.demon.HabrMail.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RegisteredUserMessage {

    private String email;
    private String login;

}
