package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author AKolomeytsev<br/>
 * Модель <b>StandartResponse</b> - описывает хранилище ответов.<br/>
 * Таблица <b>standart_response</b> базы данных <b>AnimalShelterDB</b>.
 */
@Entity
@Table(name = "standart_response")
public class StandartResponse {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    String resId;

    @Column(name = "relation_id")
    String relationId;

    @Column(name = "response_name")
    String responseName;
    @Column(name = "response_text")
    String responseText;

    public StandartResponse() {
    }

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getResponseName() {
        return responseName;
    }

    public void setResponseName(String responseName) {
        this.responseName = responseName;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandartResponse response = (StandartResponse) o;
        return resId.equals(response.resId) && relationId.equals(response.relationId) && responseName.equals(response.responseName) && responseText.equals(response.responseText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resId, relationId, responseName, responseText);
    }

    @Override
    public String toString() {
        return "StandartResponse{" +
                "resId='" + resId + '\'' +
                ", relationId='" + relationId + '\'' +
                ", responseName='" + responseName + '\'' +
                ", responseText='" + responseText + '\'' +
                '}';
    }
}
