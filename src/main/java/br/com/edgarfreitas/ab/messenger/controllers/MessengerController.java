package br.com.edgarfreitas.ab.messenger.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.edgarfreitas.ab.messenger.domain.email.EmailService;
import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController()
public class MessengerController {
    private final EmailService emailService;

    @Autowired
    public MessengerController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("send")
    public void postMethodName(@RequestBody EmailDto emailDto) {
        emailService.Send(emailDto);
    }

    @GetMapping("/teste")
    public String test() {
        return "ab-message";
    }
    
}
