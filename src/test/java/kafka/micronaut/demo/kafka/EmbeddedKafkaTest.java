package kafka.micronaut.demo.kafka;

import io.micronaut.test.annotation.MicronautTest;
import kafka.micronaut.demo.model.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.UUID;
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
        messageConsumer.getMessages().clear();
    }

    @Test
    public void producesMessages() throws InterruptedException {
        var aMessage = new Message(
                UUID.fromString("38f2be50-d4f6-470d-8748-b7f6b133de70"),
                "My first Kafka message"
        );

        messageProducer.sendMessage(aMessage);

        // Timeout in poll depends on local machine because this test spins up a Kafka in the JVM
        var bodyOfMessages = messageConsumer.getMessages().poll(4, TimeUnit.SECONDS);

        assert bodyOfMessages != null;
        assertEquals(aMessage.getContent(), bodyOfMessages.getContent());
    }
}
