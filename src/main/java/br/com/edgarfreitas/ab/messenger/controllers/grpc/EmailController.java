package br.com.edgarfreitas.ab.messenger.controllers.grpc;

import br.com.edgarfreitas.ab.messenger.v1.email.stubs.EmailServiceGrpc;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.SendMailRequest;
import br.com.edgarfreitas.ab.messenger.v1.email.stubs.SendMailResponse;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class EmailController extends EmailServiceGrpc.EmailServiceImplBase {

    @Override
    public void send(SendMailRequest request, StreamObserver<SendMailResponse> responseObserver) {

        responseObserver.onNext(SendMailResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Sucesso")
                .build());
        responseObserver.onCompleted();
    }
}
