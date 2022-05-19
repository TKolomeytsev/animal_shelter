package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.telegrambot.models.NsiCommands;

import java.util.List;
import java.util.Optional;

public interface INsiCommandsRepository extends JpaRepository<NsiCommands,String> {
    List<NsiCommands> findByCommand(String command);

    Optional<NsiCommands> findById(String id);

    void deleteById(String id);
}
