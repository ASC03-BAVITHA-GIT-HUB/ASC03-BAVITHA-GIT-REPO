package com.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App
{
    public static void main( String[] args )
    {

        System.out.println( "Welcome to our Java App!" );
        SpringApplication.run(App.class,args);
        System.err.println("Welcome to our Spring App!!");
    }
}
