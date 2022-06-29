package pro.sky.telegrambot.interfaces;

import pro.sky.telegrambot.models.NsiAnimalKind;
import pro.sky.telegrambot.models.StandartResponse;

import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * Интерфейс <b>IStandartResponse</b> - декларирует методы работы с хранилищем ответов.<br/>
 */
public interface IStandartResponse {
    List<StandartResponse> getAllStandartResponse();

    StandartResponse getStandartResponseByResId(String resId);

    List<StandartResponse> getAllStandartResponseByRelationId(String relationId);

    StandartResponse save(StandartResponse response);

    StandartResponse update(StandartResponse response);

    StandartResponse delete(String resId);

    List<StandartResponse> deleteAllStandartResponseByRelationId(String relationId);
}
