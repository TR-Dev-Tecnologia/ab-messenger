package br.com.edgarfreitas.ab.messenger.domain.email.dto;

import br.com.edgarfreitas.ab.messenger.domain.email.vo.EmailAdress;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class EmailDtoTest {

    @Test
    void SubjectValidation() {
        Exception ex = assertThrows(Exception.class, ()-> new EmailDto(
                "<h1>Título</h1><br><br><br>teste, teste ,teste",
                new EmailAdress("edgartf3@yahoo.com.br", "João da Silva"),
                null,
                "",
                true,
                List.of(new EmailAdress("edgartorresfreitas@gmail.com", "Edgar"))
        ));
        assertEquals("Subject is mandatory", ex.getMessage());

        ex = assertThrows(Exception.class, ()-> new EmailDto(
                "<h1>Título</h1><br><br><br>teste, teste ,teste",
                new EmailAdress("edgartf3@yahoo.com.br", "João da Silva"),
                null,
                null,
                true,
                List.of(new EmailAdress("edgartorresfreitas@gmail.com", "Edgar"))
        ));
        assertEquals("Subject is mandatory", ex.getMessage());
    }

    @Test
    void BodyValidation() {
        Exception ex = assertThrows(Exception.class, ()-> new EmailDto(
                "",
                new EmailAdress("edgartf3@yahoo.com.br", "João da Silva"),
                null,
                "Teste",
                true,
                List.of(new EmailAdress("edgartorresfreitas@gmail.com", "Edgar"))
        ));
        assertEquals("Body is mandatory", ex.getMessage());
    }

    @Test
    void RecipientsValidation() {
        Exception ex = assertThrows(Exception.class, ()-> new EmailDto(
                "Teste",
                new EmailAdress("edgartf3@yahoo.com.br", "João da Silva"),
                null,
                "Teste",
                true,
                List.of()
        ));
        assertEquals("Recipients is mandatory", ex.getMessage());
    }

}
