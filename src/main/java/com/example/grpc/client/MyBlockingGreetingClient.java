package com.example.grpc.client;

import com.example.grpc.services.GreetingServiceGrpc;
import com.example.grpc.services.HelloRequest;
import com.example.grpc.services.HelloResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class MyBlockingGreetingClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9777)
                .usePlaintext()
                .build();

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        HelloResponse response = stub.greeting(HelloRequest.newBuilder()
                .setName("Daniel")
                .setAge(35)
                .addHobbies("Coding")
                .addHobbies("Travelling")
                .putBagOfTricks("Coding tutorial", "Somewhat cool")
                .build());
        System.out.println(response);
    }
}
