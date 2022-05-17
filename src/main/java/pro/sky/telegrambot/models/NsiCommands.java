package pro.sky.telegrambot.models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "nsi_commands")
public class NsiCommands extends AbstractModel {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "id", unique = true)
    private String id;

    @Column(name = "command")

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
