package br.com.edgarfreitas.ab.messenger.domain.email;

import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EmailServiceTest {

    @Autowired    
    private EmailService emailService;

    @Test
	void send() {
        EmailDto email = EmailDto.builder()
        .to("edgartf3@yahoo.com.br")
        .toName("João da Silva")
        .subject("Unit Test")
        .from("edgartorresfreitas@gmail.com")
        .fromName("Edgar")
        .body("<h1>Título</h1><br><br><br>teste, teste ,teste")
        .bodyHtml(true)
        .build();

        ResonseDto resonseDto = emailService.Send(email);
        assertTrue(resonseDto.success());
	}

}
