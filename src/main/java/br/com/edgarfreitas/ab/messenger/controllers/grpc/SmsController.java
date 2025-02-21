package br.com.edgarfreitas.ab.messenger.controllers.grpc;

import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import br.com.edgarfreitas.ab.messenger.domain.sms.SmsService;
import br.com.edgarfreitas.ab.messenger.domain.sms.dto.SmsDto;
import br.com.edgarfreitas.ab.messenger.v1.sms.stubs.SmsRequest;
import br.com.edgarfreitas.ab.messenger.v1.sms.stubs.SmsResponse;
import br.com.edgarfreitas.ab.messenger.v1.sms.stubs.SmsServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class SmsController extends SmsServiceGrpc.SmsServiceImplBase {

    @Autowired
    private SmsService smsService;

    @Override
    public void send(SmsRequest request, StreamObserver<SmsResponse> responseObserver) {
        try {
            SmsDto smsDto = SmsDto.builder()
                    .text(request.getText())
                    .name(request.getName())
                    .namePersonalization(request.getNamePersonalization())
                    .toPhoneNumber(request.getToPhoneNumber())
                    .fromPhoneNumber(request.getFromPhoneNumber())
                    .build();

            ResonseDto resonseDto = smsService.Send(smsDto);

            responseObserver.onNext(SmsResponse.newBuilder()
                    .setSuccess(resonseDto.success())
                    .setMessage(resonseDto.message())
                    .build());
        } catch (Exception e) {
            responseObserver.onError(Status.INTERNAL.withDescription(e.getMessage()).asRuntimeException());
        } finally {
            responseObserver.onCompleted();
        }
    }
}
