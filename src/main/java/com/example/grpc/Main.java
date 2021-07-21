package com.example.grpc;

import com.example.grpc.client.MyAsyncGreetingClient;
import com.example.grpc.client.MyBlockingGreetingClient;
import com.example.grpc.server.MyGreetingServer;
import org.apache.commons.cli.*;
import org.apache.log4j.PropertyConfigurator;
import java.util.logging.Logger;
import java.io.IOException;


public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        // set logging capabilities
        String log4jConfPath = "resources/log4j.properties";
        System.setProperty("java.util.logging.SimpleFormatter.format", "%1$tF %1$tT %4$s: %2$s - %5$s%6$s%n");
        PropertyConfigurator.configure(log4jConfPath);

        // parse command lines
        CommandLineParser parser = new DefaultParser();
        Options options = new Options();
        //General command line options
        options.addOption("role",true,"Role of the application");

        // parse the command line arguments
        CommandLine line = parser.parse(options, args);

        if(line.getOptions().length ==0){
            LOGGER.info("You must choose one of the available roles: -role [server, client, client_async].");
        }

        // activate app role
        LOGGER.info("Activating role:"+line.getOptionValue("role"));
        switch (line.getOptionValue("role")){
            case "client": MyBlockingGreetingClient.main(null); break;
            case "client_async": MyAsyncGreetingClient.main(null); break;
            case "server": MyGreetingServer.main(null); break;
            default: LOGGER.severe("Role not defined:"+line.getOptionValue("role"));
                System.exit(-1);
        }



    }

}
