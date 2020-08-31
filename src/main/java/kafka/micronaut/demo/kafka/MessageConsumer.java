package kafka.micronaut.demo.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import kafka.micronaut.demo.model.Message;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Singleton;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
@Singleton
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class MessageConsumer {

    private final BlockingQueue<Message> messages = new LinkedBlockingDeque<>();

    @Topic("my-topic")
    public void receive(@Body Message message) {
        log.debug("Got Message - " + message);
        messages.add(message);
    }

    public BlockingQueue<Message> getMessages() {
        return messages;
    }
}