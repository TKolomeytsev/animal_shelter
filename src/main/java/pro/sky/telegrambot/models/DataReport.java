package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author AKolomeytsev<br/>
 * Модель <b>DataReport</b> - описывает хранилище отчетов.<br/>
 * Таблица <b>data_reports</b> базы данных <b>AnimalShelterDB</b>.
 */
@Entity
@Table(name = "data_reports")
public class DataReport {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id",unique = true)
    private String repId;

    @Column(name = "chat_id")
    private long chatId;

    @Column(name = "message")
    private String message;

    @Column(name = "media_type")
    private String mediaType;

    @Lob
    @Column(name = "document")
    private byte[] document;

    @Column(name = "date_send")
    private LocalDateTime dateSend;

    public DataReport() {
    }

    public String getRepId() {
        return repId;
    }

    public void setRepId(String repId) {
        this.repId = repId;
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

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public LocalDateTime getDateSend() {
        return dateSend;
    }

    public void setDateSend(LocalDateTime dateSend) {
        this.dateSend = dateSend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataReport that = (DataReport) o;
        return chatId == that.chatId && repId.equals(that.repId) && message.equals(that.message) && mediaType.equals(that.mediaType) && Arrays.equals(document, that.document) && dateSend.equals(that.dateSend);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(repId, chatId, message, mediaType, dateSend);
        result = 31 * result + Arrays.hashCode(document);
        return result;
    }

    @Override
    public String toString() {
        return "DataReport{" +
                "repId='" + repId + '\'' +
                ", chatId=" + chatId +
                ", message='" + message + '\'' +
                ", mediaType='" + mediaType + '\'' +
                ", document=" + Arrays.toString(document) +
                ", dateSend=" + dateSend +
                '}';
    }
}
