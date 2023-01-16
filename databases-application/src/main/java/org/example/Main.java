package org.example;


import org.example.service.ShowDbChanges;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Main {
    static Thread t = new Thread( new ShowDbChanges());

    public static void main(String[] args) {

        t.run();
        SpringApplication.run(Main.class, args);
    }

}