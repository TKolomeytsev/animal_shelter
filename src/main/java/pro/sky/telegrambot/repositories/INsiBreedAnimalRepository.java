package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.NsiBreedAnimal;

import java.util.List;
import java.util.Optional;

/**
 * @author AKolomeytsev<br/>
 * <b>INsiBreedAnimalRepository</b> - репозиторий парод животных.<br/>
 */
public interface INsiBreedAnimalRepository extends JpaRepository<NsiBreedAnimal,String> {
    Optional<NsiBreedAnimal> findById(String id);

    List<NsiBreedAnimal> findByName(String name);

    void deleteById(String id);
}
