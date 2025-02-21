package br.com.edgarfreitas.ab.messenger.controllers.grpc;

import br.com.edgarfreitas.ab.messenger.domain.email.EmailService;
import br.com.edgarfreitas.ab.messenger.domain.email.dto.EmailDto;
import br.com.edgarfreitas.ab.messenger.domain.email.vo.EmailAdress;
import br.com.edgarfreitas.ab.messenger.domain.exception.ValidationException;
import br.com.edgarfreitas.ab.messenger.domain.response.ResonseDto;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.EmailServiceGrpc;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.SendMailRequest;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.SendMailResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@GrpcService
public class EmailController extends EmailServiceGrpc.EmailServiceImplBase {

    @Autowired
    private EmailService emailService;

    private EmailDto ToEmailDto(SendMailRequest request) throws ValidationException {
        List<EmailAdress> recipients = new ArrayList<>();
        request.getRecipientsList().forEach(a -> {
            recipients.add(new EmailAdress(a.getEmail(), a.getName()));
        });

        List<EmailAdress> withCopy = new ArrayList<>();
        request.getWithCopyList().forEach(a -> {
            withCopy.add(new EmailAdress(a.getEmail(), a.getName()));
        });

        return new EmailDto(
                request.getBody(),
                new EmailAdress(request.getFrom().getEmail(), request.getFrom().getName()),
                withCopy,
                request.getSubject(),
                request.getBodyHtml(),
                recipients
        );

    }

    @Override
    public void send(SendMailRequest request, StreamObserver<SendMailResponse> responseObserver) {
        try {
            EmailDto email = ToEmailDto(request);
            ResonseDto resonseDto = emailService.Send(email);
            responseObserver.onNext(SendMailResponse.newBuilder()
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
