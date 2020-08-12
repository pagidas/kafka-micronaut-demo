package kafka.micronaut.demo.model;

import java.util.UUID;

public class Message {

    public final String content;
    public final UUID id;

    public Message(UUID id, String content) {
        this.content = content;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", id=" + id +
                '}';
    }
}
