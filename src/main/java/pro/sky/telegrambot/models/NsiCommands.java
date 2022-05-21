package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;
import pro.sky.telegrambot.interfaces.Utils4AnimalShelter;

import javax.persistence.*;
//Хранилище команд
@Entity
@Table(name = "nsi_commands")
// Справочник команд
public class NsiCommands implements Utils4AnimalShelter {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    //идентификатор команды, тип данных строка (String), содержит уникальные заначения вида uuid
    private String id;

    @Column(name = "command")
    //название команды, тип данных строка (String)
    private String command;

    public NsiCommands() {}

    @Override
    public String toString() {
        return "NsiCommands{" +
                "id:'" + id + '\'' +
                ", command:'" + command + '\'' +
                '}';
    }

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

    @Override
    public String responseAsString() {
        return command + "\n";
    }
}
