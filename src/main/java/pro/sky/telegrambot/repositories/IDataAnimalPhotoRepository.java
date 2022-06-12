package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.DataAnimalPhoto;

import java.util.List;
import java.util.Optional;

public interface IDataAnimalPhotoRepository extends JpaRepository<DataAnimalPhoto,String> {
    Optional<DataAnimalPhoto> findById(String id);

    List<DataAnimalPhoto> findByDataAnimal(String idAnimal);

    void deleteById(String id);

    void deleteByDataAnimal(String idAnimal);

}
