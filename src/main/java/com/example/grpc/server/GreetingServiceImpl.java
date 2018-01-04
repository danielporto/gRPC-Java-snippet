package com.example.grpc.server;

import com.example.grpc.services.GreetingServiceGrpc;
import com.example.grpc.services.HelloRequest;
import com.example.grpc.services.HelloResponse;
import io.grpc.stub.StreamObserver;

public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {
    @Override
    public void greeting(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        System.out.println(request);
        responseObserver.onNext(HelloResponse.newBuilder()
                .setGreeting("Hello "+ request.getName())
                        .build());

        responseObserver.onCompleted();
    }
}
