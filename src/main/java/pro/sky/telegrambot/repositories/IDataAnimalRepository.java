package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.DataAnimal;

import java.util.List;
import java.util.Optional;

public interface IDataAnimalRepository extends JpaRepository<DataAnimal,String> {
    Optional<DataAnimal> findById(String id);
    List<DataAnimal> findByNsiAnimalKind(String idKind);
    List<DataAnimal> findByNsiBreedAnimal(String idBreed);
    void deleteById(String id);
    void deleteByNsiAnimalKind(String idKind);
    void deleteByNsiBreedAnimal(String idBreed);
}
