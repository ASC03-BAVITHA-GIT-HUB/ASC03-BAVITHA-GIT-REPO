package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ApiGatewayApplication
{
    public static void main( String[] args )
    {

        System.out.println( "Welcome to our Java App!" );
        SpringApplication.run(ApiGatewayApplication.class,args);
        System.err.println("Welcome to our Spring App!!");
    }
}



