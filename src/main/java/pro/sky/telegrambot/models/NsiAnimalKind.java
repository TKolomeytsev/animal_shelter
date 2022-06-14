package pro.sky.telegrambot.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * @author AKolomeytsev<br/>
 * Модель <b>NsiAnimalKind</b> - описывает справочник видов животных.<br/>
 * Таблица <b>nsi_animal_kind</b> базы данных <b>AnimalShelterDB</b>.
 */
@Entity
@Table(name = "nsi_animal_kind")
public class NsiAnimalKind {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "id")
    private String idKind;

    @OneToMany(mappedBy = "nsiAnimalKind")
    private Collection<DataAnimal> dataAnimals;

    public NsiAnimalKind() {
    }

    @Column(name = "name")
    private String name;

    public String getId() {
        return idKind;
    }

    public void setId(String id) {
        this.idKind = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AnimalKind{" +
                "id='" + idKind + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NsiAnimalKind that = (NsiAnimalKind) o;
        return idKind.equals(that.idKind) && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idKind, name);
    }
}
