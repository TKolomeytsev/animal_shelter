package pro.sky.telegrambot.interfaces;

import com.pengrad.telegrambot.model.Update;
import pro.sky.telegrambot.models.DataMessage;

/**
 * @author AKolomeytsev<br/>
 * Интерфейс <b>IBotServices</b> - декларирует методы работы с входящими сообщениями.<br/>
 */
public interface IBotServices {
    /**
     * Метод парсит входящие сообщения
     * @param update
     * @return {@link DataMessage}
     */
    DataMessage readMessage(Update update);

    /**
     * Метод маршрутизации
     * @param update
     * @return метод ничего не возращает
     */

    void returnResponsToBot(Update update);
}
