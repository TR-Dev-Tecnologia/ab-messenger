package br.com.edgarfreitas.ab.messenger.controllers;

import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;
import br.com.edgarfreitas.ab.messenger.domain.email.dto.ResonseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessengerControllerTest {

    @LocalServerPort
    private int port;
    private String baseUrl;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setup() {
        baseUrl = "http://localhost:" + port + "/";
    }

    @Test
    void greetingShouldReturnDefaultMessage() {
        assertThat(this.restTemplate.getForObject(baseUrl,
                String.class)).contains("Hello, World");
    }

    @Test
    void givenEmailMessage_WhenSend_ThenSuccessTrue() {
        EmailDto request = EmailDto.builder()
                .to("edgartf3@yahoo.com.br")
                .body("<br><p>This email is a test, <b>please do not response</b></p>")
                .from("noreplay@edgarfreitas.com.br")
                .bodyHtml(true)
                .subject("Unit Test integration")
                .toName("System EdgarFreitas")
                .build();
        ResonseDto response = restTemplate.postForObject(baseUrl+"messenger/email", request, ResonseDto.class);
        assertTrue(response.success());
        assertEquals("E-Mail sent", response.message());
    }

    @Test
    void givenWithOutSubject_WhenSend_ThenDoesError() {
        EmailDto request = EmailDto.builder()
                .to("edgartf3@yahoo.com.br")
                .body("This email is a test, please do not response")
                .from("noreplay@edgarfreitas.com.br")
                .bodyHtml(false)
                .toName("System EdgarFreitas")
                .build();
        ResonseDto response = restTemplate.postForObject(baseUrl+"messenger/email", request, ResonseDto.class);
        assertFalse(response.success());
        assertEquals("Fail on send", response.message());
    }

}
