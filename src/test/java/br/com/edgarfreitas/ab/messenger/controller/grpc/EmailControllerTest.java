package br.com.edgarfreitas.ab.messenger.controller.grpc;

import br.com.edgarfreitas.ab.messenger.controllers.grpc.EmailController;
import br.com.edgarfreitas.ab.messenger.domain.email.EmailService;
import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.Email;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.SendMailRequest;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.SendMailResponse;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmailControllerTest {

    @Mock
    private StreamObserver<SendMailResponse> responseObserver;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private EmailController emailController;

    @BeforeEach
    void Setup() {
        when(emailService.Send(any())).thenReturn(new ResonseDto(true, "OK"));
    }

    @Test
    void Send() {
        SendMailRequest request = SendMailRequest.newBuilder()
                .setBodyHtml(true)
                .setBody("Teste")
                .setFrom(Email.newBuilder().setEmail("test@test.com").build())
                .addRecipients(Email.newBuilder().setEmail("test@test.com").build())
                .addWithCopy(Email.newBuilder().setEmail("test@test.com").build())
                .setSubject("test")
                .build();

        assertDoesNotThrow(() -> emailController.send(request, responseObserver));

    }
}
