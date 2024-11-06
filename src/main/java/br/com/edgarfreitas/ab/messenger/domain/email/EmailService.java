package br.com.edgarfreitas.ab.messenger.domain.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;

@Service
public class EmailService {

    @Autowired
    private IEmailEngine emailEngine;

    public void Send(EmailDto emailDto) {
        // TODO Auto-generated method stub
        emailEngine.Send(emailDto);
    }

}
