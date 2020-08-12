package kafka.micronaut.demo.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import kafka.micronaut.demo.model.Message;

import javax.inject.Singleton;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

@Singleton
@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class MessageConsumer {

    private final BlockingQueue<String> RAW_MESSAGES = new LinkedBlockingDeque<>();

    private final BlockingQueue<Message> MESSAGES = new LinkedBlockingDeque<>();

    @Topic("messages")
    public void receive(@Body String raw) {
        System.out.println("Got Message - " + raw);
        RAW_MESSAGES.add(raw);
    }

    @Topic("messages")
    public void receive(@Body Message message) {
        System.out.println("Got Message - " + message.toString());
        MESSAGES.add(message);
    }

    public BlockingQueue<String> getRawMessages() {
        return RAW_MESSAGES;
    }

    public BlockingQueue<Message> getMessages() {
        return MESSAGES;
    }
}