package pro.sky.telegrambot.interfaces;

import pro.sky.telegrambot.models.DataAnimalOwner;
import pro.sky.telegrambot.models.StandartResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * Интерфейс <b>IDataAnimalOwner</b> - декларирует методы работы с хранилищем опекунов животных.<br/>
 */
public interface IDataAnimalOwner {
    List<DataAnimalOwner> findAll();
    DataAnimalOwner findByIdOwner(String idOwner);
    DataAnimalOwner findByIdAnimal(String idAnimal);
    List<DataAnimalOwner> findByChatId(long chatId);
    DataAnimalOwner save(DataAnimalOwner animalOwner);
    DataAnimalOwner update(DataAnimalOwner animalOwner);
    DataAnimalOwner deleteByIdOwner (String idOwner);
    DataAnimalOwner deleteByIdAnimal(String idAnimal);
    List<DataAnimalOwner> deleteAllByChatId(long chatId);
    List<DataAnimalOwner> getSendList(LocalDateTime startDate, LocalDateTime endDate);
}
