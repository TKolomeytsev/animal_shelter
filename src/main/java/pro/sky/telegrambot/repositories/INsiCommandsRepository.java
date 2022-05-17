package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.NsiCommands;

import java.util.List;

public interface INsiCommandsRepository extends JpaRepository<NsiCommands,String> {
    List<NsiCommands> findByCommand(String command);
}
