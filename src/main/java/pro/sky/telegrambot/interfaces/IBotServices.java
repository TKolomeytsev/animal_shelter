package pro.sky.telegrambot.interfaces;

import com.pengrad.telegrambot.model.Update;
import pro.sky.telegrambot.models.DataMessage;

public interface IBotServices {
    DataMessage readMessage(Update update);

    void router(Update update);
}