package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * @author AKolomeytsev<br/>
 * Модель <b>DataAnimal</b> - описывает хранилище животных.<br/>
 * Таблица <b>data_animal</b> базы данных <b>AnimalShelterDB</b>.
 */
@Entity
@Table(name = "data_animal")
public class DataAnimal {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "id")
    private String idAnimal;

    @OneToMany(mappedBy = "dataAnimal")
    private Collection<DataAnimalPhoto> dataAnimalPhotos;

    @ManyToOne
    @JoinColumn(name = "idKind")
    private NsiAnimalKind nsiAnimalKind;

    @ManyToOne
    @JoinColumn(name = "idBreed")
    private NsiBreedAnimal nsiBreedAnimal;

    @Column(name = "nickname")
    private String nickname;

    @Column(name  = "age")
    private int age;

    @Column(name = "weight")
    private int weight;

    @Column(name = "growth")
    private double growth;

    @Column(name = "color")
    private String color;

    public DataAnimal() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataAnimal that = (DataAnimal) o;
        return age == that.age && weight == that.weight && Double.compare(that.growth, growth) == 0 && idAnimal.equals(that.idAnimal) && dataAnimalPhotos.equals(that.dataAnimalPhotos) && nsiAnimalKind.equals(that.nsiAnimalKind) && nsiBreedAnimal.equals(that.nsiBreedAnimal) && nickname.equals(that.nickname) && color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimal, dataAnimalPhotos, nsiAnimalKind, nsiBreedAnimal, nickname, age, weight, growth, color);
    }

    public String getId() {
        return idAnimal;
    }

    public void setId(String id) {
        this.idAnimal = id;
    }

    public Collection<DataAnimalPhoto> getDataAnimalPhotos() {
        return dataAnimalPhotos;
    }

    public void setDataAnimalPhotos(Collection<DataAnimalPhoto> dataAnimalPhotos) {
        this.dataAnimalPhotos = dataAnimalPhotos;
    }

    public NsiAnimalKind getNsiAnimalKind() {
        return nsiAnimalKind;
    }

    public void setNsiAnimalKind(NsiAnimalKind nsiAnimalKind) {
        this.nsiAnimalKind = nsiAnimalKind;
    }

    public NsiBreedAnimal getNsiBreedAnimal() {
        return nsiBreedAnimal;
    }

    public void setNsiBreedAnimal(NsiBreedAnimal nsiBreedAnimal) {
        this.nsiBreedAnimal = nsiBreedAnimal;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getGrowth() {
        return growth;
    }

    public void setGrowth(double growth) {
        this.growth = growth;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "DataAnimal{" +
                "id='" + idAnimal + '\'' +
                ", dataAnimalPhotos=" + dataAnimalPhotos +
                ", nsiAnimalKind=" + nsiAnimalKind +
                ", nsiBreedAnimal=" + nsiBreedAnimal +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", growth=" + growth +
                ", color='" + color + '\'' +
                '}';
    }
}
