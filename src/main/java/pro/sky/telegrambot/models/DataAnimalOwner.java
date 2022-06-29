package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author AKolomeytsev<br/>
 * Модель <b>DataAnimalOwner</b> - описывает хранилище опекунов животных.<br/>
 * Таблица <b>data_animal_owner</b> базы данных <b>AnimalShelterDB</b>.
 */
@Entity
@Table(name = "data_animal_owner")
public class DataAnimalOwner {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "id")
    private String idOwner;
    @Column(name = "owner_name")
    private String ownerName;
    @Column (name = "id_animal")
    private String idAnimal;
    @Column(name = "chat_id")
    private long chatId;
    @Column(name = "date_guard")
    private LocalDateTime dateGuard;

    public DataAnimalOwner() {
    }

    public String getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(String idOwner) {
        this.idOwner = idOwner;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(String idAnimal) {
        this.idAnimal = idAnimal;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public LocalDateTime getDateGuard() {
        return dateGuard;
    }

    public void setDateGuard(LocalDateTime dateGuard) {
        this.dateGuard = dateGuard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataAnimalOwner that = (DataAnimalOwner) o;
        return chatId == that.chatId && idOwner.equals(that.idOwner) && ownerName.equals(that.ownerName) && idAnimal.equals(that.idAnimal) && dateGuard.equals(that.dateGuard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOwner, ownerName, idAnimal, chatId, dateGuard);
    }

    @Override
    public String toString() {
        return "DataAnimalOwner{" +
                "idOwner='" + idOwner + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", idAnimal='" + idAnimal + '\'' +
                ", chatId=" + chatId +
                ", dateGuard=" + dateGuard +
                '}';
    }
}
