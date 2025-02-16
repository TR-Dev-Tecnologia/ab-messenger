package br.com.edgarfreitas.ab.messenger.controllers.grpc;

import br.com.edgarfreitas.ab.messenger.domain.email.EmailService;
import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;
import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.EmailServiceGrpc;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.SendMailRequest;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.SendMailResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class EmailController extends EmailServiceGrpc.EmailServiceImplBase {

    @Autowired
    private EmailService emailService;

    @Override
    public void send(SendMailRequest request, StreamObserver<SendMailResponse> responseObserver) {

        EmailDto email = EmailDto.builder()
                .toName(request.getToName())
                .subject(request.getSubject())
                .body(request.getBody())
                .from(request.getFrom())
                .to(request.getTo())
                .fromName(request.getFromName())
                .bodyHtml(request.getBodyHtml())
                .withCopy(request.getWithCopy())
                .build();

        ResonseDto resonseDto = emailService.Send(email);

        responseObserver.onNext(SendMailResponse.newBuilder()
                .setSuccess(resonseDto.success())
                .setMessage(resonseDto.message())
                .build());
        responseObserver.onCompleted();
    }
}
