package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * @author AKolomeytsev<br/>
 * Модель <b>NsiCommands</b> - описывает справочник команд.<br/>
 * Таблица <b>nsi_commands</b> базы данных <b>AnimalShelterDB</b>.
 */
@Entity
@Table(name = "nsi_commands")
public class NsiCommands {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    //идентификатор команды, тип данных строка (String), содержит уникальные заначения вида uuid
    private String id;

    @Column(name = "command")
    //название команды, тип данных строка (String)
    private String command;

    @Column(name = "level")
    private int level;

    @Column(name = "description")
    private int description;

    public NsiCommands() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "NsiCommands{" +
                "id:'" + id + '\'' +
                ", command:'" + command + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NsiCommands that = (NsiCommands) o;
        return level == that.level && description == that.description && id.equals(that.id) && command.equals(that.command);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, command, level, description);
    }
}
