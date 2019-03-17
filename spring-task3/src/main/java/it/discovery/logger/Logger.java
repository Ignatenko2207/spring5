package it.discovery.logger;

@FunctionalInterface
public interface Logger {
    void write(String message);
}
