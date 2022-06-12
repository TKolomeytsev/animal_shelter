package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.NsiAnimalKind;

import java.util.List;
import java.util.Optional;

public interface INsiAnimalKindRepositiry extends JpaRepository<NsiAnimalKind,String> {
    Optional<NsiAnimalKind> findById(String id);

    List<NsiAnimalKind> findByName(String name);

    void deleteById(String id);
}
