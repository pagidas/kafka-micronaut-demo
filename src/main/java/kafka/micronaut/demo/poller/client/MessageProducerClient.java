package kafka.micronaut.demo.poller.client;

import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.client.annotation.Client;
import kafka.micronaut.demo.model.Message;

import javax.validation.constraints.NotBlank;

@Client("${message-producer.url}")
public interface MessageProducerClient {

    @Get("/send/{message}")
    void getMessage(@NotBlank @PathVariable Message message);

}
