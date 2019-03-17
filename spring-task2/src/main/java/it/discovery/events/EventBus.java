package it.discovery.events;

import it.discovery.logger.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;

import java.util.List;

@RequiredArgsConstructor
public class EventBus {
    private final List<Logger> loggers;

    @EventListener
    public void onLogEvent(LogEvent event) {
        loggers.forEach(logger -> logger.write(event.getMessage()));
    }
}
