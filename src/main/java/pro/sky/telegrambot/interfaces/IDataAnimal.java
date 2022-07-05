package pro.sky.telegrambot.interfaces;

import pro.sky.telegrambot.models.DataAnimal;

import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * Интерфейс <b>IDataAnimal</b> - декларирует методы работы с хранилищем животных.<br/>
 */
public interface IDataAnimal {
    List<DataAnimal> getAllAnimal();

    DataAnimal getAllDataAnimalById(String id);

    List<DataAnimal> getAllDataAnimalByIdKind(String idKind);

    List<DataAnimal> getAllDataAnimalByIdBreed(String idBreed);

    DataAnimal save(DataAnimal dataAnimal);

    DataAnimal update(DataAnimal dataAnimal);

    DataAnimal delete(String id);

    List<DataAnimal> deleteAllDataAnimalByIdKind(String idKind);

    List<DataAnimal> deleteAllDataAnimalByIdBreed(String idBreed);
}
