package kafka.micronaut.demo.kafka;

import io.micronaut.test.annotation.MicronautTest;
import kafka.micronaut.demo.model.Message;
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
        messageConsumer.getMessages().clear();
    }

    @Test
    public void producesMessages() throws InterruptedException {
        // Given
        var aMessage = new Message("My first Kafka message!");

        // When
        messageProducer.sendMessage(aMessage);

        // Then
        // Timeout in poll depends on local machine because this test spins up a Kafka in the JVM
        var polledMessage = messageConsumer.getMessages().poll(4, TimeUnit.SECONDS);

        assert polledMessage != null;
        assertEquals(aMessage.getContent(), polledMessage.getContent());
    }
}
