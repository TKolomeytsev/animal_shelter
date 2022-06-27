package pro.sky.telegrambot.interfaces;

import pro.sky.telegrambot.models.NsiAnimalKind;

import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * Интерфейс <b>INsiAnimalKind</b> - декларирует методы работы со справочником видов животных.<br/>
 */
public interface INsiAnimalKind {
    List<NsiAnimalKind> getAllAnimalKind();

    NsiAnimalKind getAllAnimalKindById(String id);

    List<NsiAnimalKind> getAllAnimalKindByName(String name);

    NsiAnimalKind save(NsiAnimalKind name);

    NsiAnimalKind update(NsiAnimalKind name);

    NsiAnimalKind delete(String id);
}
