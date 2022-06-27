package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
/**
 * @author AKolomeytsev<br/>
 * Модель <b>DataAnimalPhoto</b> - описывает хранилище избражений животного.<br/>
 * Таблица <b>data_animal_photo</b> базы данных <b>AnimalShelterDB</b>.
 */
@Entity
@Table(name = "data_animal_photo")
public class DataAnimalPhoto {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "id")
    private String idPhoto;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "idAnimal")
    private DataAnimal dataAnimal;

    @Column(name = "description")
    private String description;

    @Column(name = "media_type")
    private String mediaType;

    @Lob
    @Column(name = "content")
    private byte[] content;

    public DataAnimalPhoto() {
    }

    public String getId() {
        return idPhoto;
    }

    public void setId(String id) {
        this.idPhoto = id;
    }

    public DataAnimal getDataAnimal() {
        return dataAnimal;
    }

    public void setDataAnimal(DataAnimal dataAnimal) {
        this.dataAnimal = dataAnimal;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
