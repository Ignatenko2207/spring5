package it.discovery.logger;

import javax.annotation.PostConstruct;

public class InMemoryLogger implements Logger {
    @PostConstruct
    public void init() {
        System.out.println("In-memory logger started");
    }

    @Override
    public void write(String message) {
        System.out.println("In-memory logger: " + message);

    }
}
