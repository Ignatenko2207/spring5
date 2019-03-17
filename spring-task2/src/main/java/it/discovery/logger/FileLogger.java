package it.discovery.logger;

import javax.annotation.PostConstruct;

public class FileLogger implements Logger {
    @PostConstruct
    public void init() {
        System.out.println("File logger started");
    }

    @Override
    public void write(String message) {
        System.out.println("File logger: " + message);
    }
}
