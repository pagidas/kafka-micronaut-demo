package kafka.micronaut.demo;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import kafka.micronaut.demo.kafka.MessageProducer;
import kafka.micronaut.demo.model.Message;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Controller
public class MyController {

    private final MessageProducer messageProducer;

    @Inject
    public MyController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @Get(value = "/send/{message}")
    public void sendMessage(@NotBlank @PathVariable String message) {
        messageProducer.sendMessage(new Message(UUID.randomUUID(), message));
    }
}
