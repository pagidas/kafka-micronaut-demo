package kafka.micronaut.demo.kafka;

import io.micronaut.test.annotation.MicronautTest;
import kafka.micronaut.demo.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@MicronautTest(environments = "kafka")
public class EmbeddedKafkaTest {

    @Inject
    public MessageProducer messageProducer;

    @Inject
    public MessageConsumer messageConsumer;

    @BeforeEach
    public void setUp() {
        messageConsumer.getMessages().clear();
    }

    @Test
    public void producesMessages() throws InterruptedException {
        Message aMessage = new Message(
                UUID.fromString("dbcc8f78-a088-4b4e-a15d-86f263126e1e"),
                "A modelled message"
                );

        messageProducer.sendMessage(aMessage);

        // Timeout in poll depends on local machine because this test spins up a Kafka in the JVM
        Message bodyOfMessages = messageConsumer.getMessages().poll(4, TimeUnit.SECONDS);

        assert bodyOfMessages != null;
        assertEquals(aMessage.content, bodyOfMessages.content);
    }
}
