package kafka.micronaut.demo.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Message {
    private final UUID id;
    private final String content;
}
