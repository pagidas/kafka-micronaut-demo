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

    private final BlockingQueue<Message> messages = new LinkedBlockingDeque<>();

    @Topic("my-topic")
    public void receive(@Body Message message) {
        System.out.println("Got Message - " + message);
        messages.add(message);
    }

    public BlockingQueue<Message> getMessages() {
        return messages;
    }
}