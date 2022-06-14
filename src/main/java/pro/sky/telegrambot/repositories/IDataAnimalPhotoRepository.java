package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.models.DataAnimalPhoto;

import java.util.List;
import java.util.Optional;

public interface IDataAnimalPhotoRepository extends JpaRepository<DataAnimalPhoto,String> {
    Optional<DataAnimalPhoto> findById(String id);

    List<DataAnimalPhoto> findByDataAnimal(String idAnimal);

    void deleteById(String id);

    void deleteByDataAnimal(String idAnimal);
    @Query(value = "INSERT INTO data_animal_photo (id,id_animal,description,content) VALUES (?1,?2,?3,?4)",nativeQuery = true)
    void save(String id,String IdAnimal,String description,byte[] content);
    @Query(value = "UPDATE data_animal_photo SET  id_animal = ?2,description = ?3,content = ?4 WHERE id = ?1",nativeQuery = true)
    void update(String id,String IdAnimal,String description,byte[] content);

}
