package br.com.edgarfreitas.ab.messenger.controllers;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.ResonseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import br.com.edgarfreitas.ab.messenger.domain.email.EmailService;
import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;


@RestController
public class MessengerController {
    private final EmailService emailService;

    @Autowired
    public MessengerController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/messenger/email")
    public ResonseDto sendEmail(@RequestBody EmailDto emailDto) {
        return emailService.Send(emailDto);
    }

    @RequestMapping("/")
    public @ResponseBody String greeting() {
        return "Hello, World";
    }

}
