package br.com.edgarfreitas.ab.messenger.domain.email;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;
import br.com.edgarfreitas.ab.messenger.domain.email.vo.EmailAdress;
import br.com.edgarfreitas.ab.messenger.domain.exception.ValidationException;
import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDtoTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EmailServiceTest {

    @Autowired    
    private EmailService emailService;

    @Test
	void send() {
        assertDoesNotThrow(() -> { EmailDto email = new EmailDto(
                "<h1>Título</h1><br><br><br>teste, teste ,teste",
                new EmailAdress("edgartf3@yahoo.com.br", "João da Silva"),
                null,
                "Unit Test",
                true,
                List.of(new EmailAdress("edgartorresfreitas@gmail.com", "Edgar")));
            ResonseDto resonseDto = emailService.Send(email);
            assertTrue(resonseDto.success());
        });
	}

    @Test
    void WrongEmailTo() {
        Exception ex = assertThrows(Exception.class, ()-> new EmailDto(
                "<h1>Título</h1><br><br><br>teste, teste ,teste",
                new EmailAdress("edgartf3@yahoo.com.br", "João da Silva"),
                null,
                "Unit Test",
                true,
                List.of(new EmailAdress("edgartorresfreitasxgmail.com", "Edgar"))
        ));
        assertEquals("email edgartorresfreitasxgmail.com is invalid", ex.getMessage());
    }

    @Test
    void WrongEmailFrom() throws ValidationException {
        Exception ex = assertThrows(Exception.class, ()-> new EmailDto(
                "<h1>Título</h1><br><br><br>teste, teste ,teste",
                new EmailAdress("edgartf3xyahoo.com.br", "João da Silva"),
                null,
                "Unit Test",
                true,
                List.of(new EmailAdress("edgartorresfreitas@gmail.com", "Edgar"))
        ));
        assertEquals("email edgartf3xyahoo.com.br is invalid", ex.getMessage());
    }

    @Test
    void RequiredBody() {
        Exception ex = assertThrows(Exception.class, ()-> new EmailDto(
                null,
                new EmailAdress("edgartf3@yahoo.com.br", "João da Silva"),
                null,
                "Unit Test",
                true,
                List.of(new EmailAdress("edgartorresfreitas@gmail.com", "Edgar"))
        ));
        assertEquals("Body is mandatory", ex.getMessage());
    }

    @Test
    void RequiredTo() {
        Exception ex = assertThrows(Exception.class, ()-> new EmailDto(
                "<h1>Título</h1><br><br><br>teste, teste ,teste",
                new EmailAdress("edgartf3@yahoo.com.br", "João da Silva"),
                null,
                "Unit Test",
                true,
                null
                ));
        assertEquals("Recipients is mandatory", ex.getMessage());
    }

    @Test
    void RequiredFrom() {
        Exception ex = assertThrows(Exception.class, ()-> new EmailDto(
                "<h1>Título</h1><br><br><br>teste, teste ,teste",
                null,
                null,
                "Unit Test",
                true,
                List.of(new EmailAdress("edgartorresfreitas@gmail.com", "Edgar"))
        ));
        assertEquals("From is mandatory", ex.getMessage());
    }
}
