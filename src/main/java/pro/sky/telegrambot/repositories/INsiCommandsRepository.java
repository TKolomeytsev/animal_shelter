package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.NsiCommands;

import java.util.List;
import java.util.Optional;

/**
 * @author AKolomeytsev<br/>
 * <b>INsiCommandsRepository</b> - репозиторий команд.<br/>
 */
public interface INsiCommandsRepository extends JpaRepository<NsiCommands,String> {
    List<NsiCommands> findByCommand(String command);
    List<NsiCommands> findByLevel(int level);

    Optional<NsiCommands> findById(String id);

    void deleteById(String id);
}
