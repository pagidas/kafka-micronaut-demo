package kafka.micronaut.demo.model;

public class Message {

    public final String content;

    public Message(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                '}';
    }
}
