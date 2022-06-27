package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.models.DataAnimalPhoto;

import java.util.List;
import java.util.Optional;

/**
 * @author AKolomeytsev<br/>
 * <b>IDataAnimalPhotoRepository</b> - репозиторий изображений животных.<br/>
 */
public interface IDataAnimalPhotoRepository extends JpaRepository<DataAnimalPhoto,String> {
    Optional<DataAnimalPhoto> findById(String id);


    List<DataAnimalPhoto> findByDataAnimal(String idAnimal);
    @Query(nativeQuery = true, value = "SELECT content FROM data_animal_photo WHERE id_animal  = :idAnimal ORDER BY id_animal DESC")
    List<byte[]> getPhotoByIdAnimal(String idAnimal);

    void deleteById(String id);

    void deleteByDataAnimal(String idAnimal);
    DataAnimalPhoto save(DataAnimalPhoto dataAnimalPhoto);

}
