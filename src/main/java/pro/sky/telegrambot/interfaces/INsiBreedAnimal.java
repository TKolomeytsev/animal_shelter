package pro.sky.telegrambot.interfaces;

import pro.sky.telegrambot.models.NsiBreedAnimal;

import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * Интерфейс <b>INsiAnimalKind</b> - декларирует методы работы со справочником парод животных.<br/>
 */
public interface INsiBreedAnimal {
    List<NsiBreedAnimal> getAllBreedAnimal();

    NsiBreedAnimal getAllBreedAnimalById(String id);

    List<NsiBreedAnimal> getAllBreedAnimalByName(String name);

    NsiBreedAnimal save(NsiBreedAnimal name);

    NsiBreedAnimal update(NsiBreedAnimal name);

    NsiBreedAnimal delete(String id);
}
