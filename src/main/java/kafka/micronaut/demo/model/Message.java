package kafka.micronaut.demo.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@Getter
public class Message {
    private UUID id;
    private String content;

    public Message(String content) {
        this.id = UUID.randomUUID();
        this.content = content;
    }
}
