package kafka.micronaut.demo.kafka;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;
import io.micronaut.messaging.annotation.Body;
import kafka.micronaut.demo.model.Message;

@KafkaClient
public interface MessageProducer {

    @Topic("my-topic")
    void sendMessage(@Body Message message);

}