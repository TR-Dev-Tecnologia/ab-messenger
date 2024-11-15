package br.com.edgarfreitas.ab.messenger.controllers;

import org.springframework.web.bind.annotation.RestController;

import br.com.edgarfreitas.ab.messenger.domain.email.EmailService;
import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class MessengerController {
    private final EmailService emailService;

    public MessengerController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/email")
    public void postMethodName(@RequestBody EmailDto emailDto) {
        emailService.Send(emailDto);        
    }
    
}
