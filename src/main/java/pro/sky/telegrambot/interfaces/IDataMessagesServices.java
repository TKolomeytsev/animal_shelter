package pro.sky.telegrambot.interfaces;

import pro.sky.telegrambot.models.DataMessage;
import java.time.LocalDateTime;
import java.util.List;


public interface IDataMessagesServices {
    List<DataMessage> getAllMessages();

    DataMessage getSingleMessageById(String id);

    List<DataMessage> getMessagesByChatId(long chatId);

    List<DataMessage> getMessagesByDateSend(LocalDateTime dateSend);

    List<DataMessage> getMessagesByDateSendAndChatId(LocalDateTime dateSend, long chatId);

    DataMessage saveMessage(DataMessage dataMessage);

    String getLastCommand(long chatId);
}
