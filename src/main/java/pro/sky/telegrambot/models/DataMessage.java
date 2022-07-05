package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author AKolomeytsev<br/>
 * Модель <b>DataMessage</b> - описывает хранилище сообщений.<br/>
 * Таблица <b>data_messages</b> базы данных <b>AnimalShelterDB</b>.
 */
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataMessage that = (DataMessage) o;
        return chatId == that.chatId && id.equals(that.id) && message.equals(that.message) && dateSend.equals(that.dateSend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, chatId, message, dateSend);
    }
}
