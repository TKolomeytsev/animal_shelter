package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pro.sky.telegrambot.models.DataAnimalOwner;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>IDataAnimalOwnerRepository</b> - репозиторий опекунов животных.<br/>
 */
public interface IDataAnimalOwnerRepository extends JpaRepository<DataAnimalOwner,String> {
    List<DataAnimalOwner> findAll();
    DataAnimalOwner findByIdOwner(String idOwner);
    DataAnimalOwner findByIdAnimal(String idAnimal);
    List<DataAnimalOwner> findByChatId(long chatId);
    void deleteByIdOwner (String idOwner);
    void deleteByIdAnimal(String idAnimal);
    void deleteAllByChatId(long chatId);

    @Query(nativeQuery = true, value = "SELECT * FROM data_animal_owner WHERE date_guard BETWEEN :startDate AND :endDate ORDER BY date_guard DESC")
    List<DataAnimalOwner> getSendList(LocalDateTime startDate, LocalDateTime endDate);
}
