package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * @author AKolomeytsev<br/>
 * Модель <b>NsiBreedAnimal</b> - описывает справочник пород животных.<br/>
 * Таблица <b>nsi_breed_animal</b> базы данных <b>AnimalShelterDB</b>.
 */
@Entity
@Table(name = "nsi_breed_animal")
public class NsiBreedAnimal {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    @Column(name = "id")
    private String id;

    @OneToMany(mappedBy = "nsiBreedAnimal")
    private Collection<DataAnimal> dataAnimals;

    public NsiBreedAnimal() {
    }
    @Column(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NsiBreedAnimal that = (NsiBreedAnimal) o;
        return id.equals(that.id) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "NsiBreedAnimal{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
