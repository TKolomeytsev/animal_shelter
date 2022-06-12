package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "data_animal_photo")
public class DataAnimalPhoto {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "id")
    private String id;

    //@Column(name = "id_animal")
    @ManyToOne
    @JoinColumn(name = "id")
    private DataAnimal dataAnimal;

    @Column(name = "description")
    private String description;

    public DataAnimalPhoto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Lob
    @Column(name = "content")
    private byte[] content;

}
