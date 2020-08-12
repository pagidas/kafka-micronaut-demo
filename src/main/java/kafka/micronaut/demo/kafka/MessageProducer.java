package kafka.micronaut.demo.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import kafka.micronaut.demo.model.Message;

@KafkaClient
public interface MessageProducer {

    @Topic("messages")
    void sendMessage(@Body String raw);

    @Topic("messages")
    void sendMessage(@Body Message message);
}