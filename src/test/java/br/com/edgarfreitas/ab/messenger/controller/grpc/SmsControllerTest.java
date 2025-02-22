package br.com.edgarfreitas.ab.messenger.controller.grpc;

import br.com.edgarfreitas.ab.messenger.controllers.grpc.SmsController;
import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import br.com.edgarfreitas.ab.messenger.domain.sms.SmsService;
import br.com.edgarfreitas.ab.messenger.v1.sms.stubs.SmsRequest;
import br.com.edgarfreitas.ab.messenger.v1.sms.stubs.SmsResponse;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class SmsControllerTest {
    @Mock
    private StreamObserver<SmsResponse> responseObserver;

    @Mock
    private SmsService smsService;

    @InjectMocks
    private SmsController smsController;

    @Test
    void Send() {
        SmsRequest request = SmsRequest.newBuilder().build();
        when(smsService.Send(any())).thenReturn(new ResonseDto(true, "OK"));

        smsController.send(request, responseObserver);

    }


}
