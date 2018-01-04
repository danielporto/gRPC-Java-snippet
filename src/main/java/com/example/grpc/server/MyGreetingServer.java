package com.example.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MyGreetingServer {
    public static void main ( String args [] ) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(8080)
                .addService(new GreetingServiceImpl())
                .build();
        server.start();
        System.out.println("Server started");
        server.awaitTermination();
    }
}
