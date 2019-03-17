package it.discovery.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class LogEvent extends ApplicationEvent {

    private String message;

    public LogEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
}
