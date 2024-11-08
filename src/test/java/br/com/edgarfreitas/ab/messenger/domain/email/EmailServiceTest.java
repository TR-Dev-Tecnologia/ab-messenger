package br.com.edgarfreitas.ab.messenger.domain.email;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;

@SpringBootTest
public class EmailServiceTest {

    @Autowired    
    private EmailService emailService;

    @Test
	void send() {
        EmailDto email = EmailDto.builder()
        .to("edgartf3@yahoo.com.br")
        .subject("teste")
        .from("edgartorresfreitas@gmail.com")
        .body("teste, teste ,teste")
        .build();
        emailService.Send(email);

	}

}
