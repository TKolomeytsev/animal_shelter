package pro.sky.telegrambot.interfaces;

import pro.sky.telegrambot.models.NsiCommands;
import java.util.List;

public interface INsiCommandServices {
    List<NsiCommands> getAllCommands();

    NsiCommands getCommandsById(String id);

    List<NsiCommands> getCommandByCommand(String command);

    NsiCommands save(NsiCommands command);

    NsiCommands update(NsiCommands command);

    NsiCommands delete(String id);
}
