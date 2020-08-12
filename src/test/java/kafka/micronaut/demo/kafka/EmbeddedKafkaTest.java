package kafka.micronaut.demo.kafka;

import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest(environments = "kafka")
public class EmbeddedKafkaTest {

    @Inject
    public MessageProducer messageProducer;

    @Inject
    public MessageConsumer messageConsumer;

    @BeforeEach
    public void setUp() {
        messageConsumer.getRawMessages().clear();
        messageConsumer.getMessages().clear();
    }

    @Test
    public void producesMessages() throws InterruptedException {
        messageProducer.sendMessage("This is my first Kafka message!");

        String bodyOfMessages = messageConsumer.getRawMessages().poll(2, TimeUnit.SECONDS);
        assertEquals("This is my first Kafka message!", bodyOfMessages);
    }
}
