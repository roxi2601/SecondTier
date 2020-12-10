package shared;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
public class Message {
    private String username;
    private String messageBody;
    private LocalDateTime dateTime;

    public Message(String username, String message) {
        this.dateTime = LocalDateTime.now();
        this.username = username;
        this.messageBody = message;
    }

    public Message(MessageDTO dto) {
        this(dto.getUsername(), dto.getBody());
    }

    public Message update() {
        this.dateTime = LocalDateTime.now();
        return this;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBody() {
        return messageBody;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDateTime(String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm:ss");
        return "username=" + username + ", time=" + dateTime.format(formatter) + ", message=\"" + messageBody + "\"";
    }
}
