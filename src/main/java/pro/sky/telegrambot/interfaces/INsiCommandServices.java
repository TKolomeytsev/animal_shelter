package pro.sky.telegrambot.interfaces;

import pro.sky.telegrambot.models.NsiCommands;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * Интерфейс <b>INsiAnimalKind</b> - декларирует методы работы со справочником команд.<br/>
 */
public interface INsiCommandServices {
    List<NsiCommands> getAllCommands();

    NsiCommands getCommandsById(String id);

    List<NsiCommands> getCommandByCommand(String command);

    List<NsiCommands> findByLevel(int level);

    NsiCommands save(NsiCommands command);

    NsiCommands update(NsiCommands command);

    NsiCommands delete(String id);
}
