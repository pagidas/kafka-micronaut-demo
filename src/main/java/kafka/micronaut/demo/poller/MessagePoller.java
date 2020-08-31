package kafka.micronaut.demo.poller;

import io.micronaut.context.env.Environment;
import io.micronaut.scheduling.annotation.Scheduled;
import kafka.micronaut.demo.model.Message;
import kafka.micronaut.demo.poller.client.MessageProducerClient;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.inject.Singleton;

@Slf4j
@Singleton
public class MessagePoller {

    @Inject
    private MessageProducerClient messageProducerClient;
    @Inject
    Environment env;

    @Scheduled(initialDelay = "${poller.initial-delay}", fixedDelay = "${poller.fixed-delay}")
    public void periodically() {
        env.getProperty("poller.message", String.class)
                .ifPresent(present -> {
                    Message message = new Message(present);
                    log.debug("Polling for message: " + message);
                    poll(message);
                });
    }
    
    public void poll(Message message) {
        messageProducerClient.getMessage(message);
    }
}
