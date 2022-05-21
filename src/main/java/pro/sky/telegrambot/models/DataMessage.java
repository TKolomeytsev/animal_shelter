package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;

//Хранилище сообщений
@Entity
@Table(name = "data_messages")
public class DataMessage {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "message")
    private String message;

    @Column(name = "date_send")
    private LocalDateTime dateSend;

    public DataMessage() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDateSend() {
        return dateSend;
    }

    public void setDateSend(LocalDateTime dateSend) {
        this.dateSend = dateSend;
    }

    @Override
    public String toString() {
        return "DataMessages{" +
                "id:'" + id + '\'' +
                ", chatId:" + chatId +
                ", message:'" + message + '\'' +
                ", dateSend:" + dateSend +
                '}';
    }

}
