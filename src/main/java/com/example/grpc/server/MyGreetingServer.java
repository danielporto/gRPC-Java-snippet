package com.example.grpc.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MyGreetingServer {
    public static void main (String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(9777)
                .addService(new GreetingServiceImpl())
                .build();

        server.start();
        System.out.println("Server started");

        // shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("gRPC server is shutting down!");
            server.shutdown();
        }));


        // Don't exit the main thread. Wait until server is terminated.
        server.awaitTermination();
    }
}
