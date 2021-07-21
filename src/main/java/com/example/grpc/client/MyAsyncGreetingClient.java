package com.example.grpc.client;

import com.example.grpc.services.GreetingServiceGrpc;
import com.example.grpc.services.HelloRequest;
import com.example.grpc.services.HelloResponse;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class MyAsyncGreetingClient {

    public static void main(String[] args)  {
        final ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9777)
                .usePlaintext()
                .build();


        GreetingServiceGrpc.GreetingServiceFutureStub stub = GreetingServiceGrpc.newFutureStub(channel);
        HelloRequest request = HelloRequest.newBuilder().setName("Daniel")
                .setAge(39)
                .addHobbies("Coding")
                .addHobbies("Travelling")
                .putBagOfTricks("Coding tutorial", "Somewhat cool")
                .build();

        //send async request
        Future<HelloResponse> response = stub.greeting(request);

        System.out.println("check if the result is ready (should fail, because is too soon to ask): " + response.isDone());

        System.out.println("block waiting for a response");
        HelloResponse res = null;
        try {
            res = response.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("after get result should be done: "+ response.isDone());

        System.out.println("ready to print the output: " + res.getGreeting());

        // closing connection to the server before leave
        channel.shutdownNow();
    }
}
