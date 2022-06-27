package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.models.DataAnimal;

import java.util.List;
import java.util.Optional;

/**
 * @author AKolomeytsev<br/>
 * <b>IDataAnimalRepository</b> - репозиторий животных.<br/>
 */
public interface IDataAnimalRepository extends JpaRepository<DataAnimal,String> {
    Optional<DataAnimal> findById(String id);
    @Query(nativeQuery = true, value = "SELECT * FROM data_animal WHERE id_kind  = :idKind ORDER BY id DESC")
    List<DataAnimal> findByNsiAnimalKind(String idKind);
    List<DataAnimal> findByNsiBreedAnimal(String idBreed);
    void deleteById(String id);
    void deleteByNsiAnimalKind(String idKind);
    void deleteByNsiBreedAnimal(String idBreed);
}
