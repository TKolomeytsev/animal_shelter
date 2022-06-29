package pro.sky.telegrambot.services;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.interfaces.IDataMessagesServices;
import pro.sky.telegrambot.models.DataMessage;
import pro.sky.telegrambot.repositories.IDataMessagesRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>DataMessagesService</b> - сервис для работы с сообщениями.<br/>
 */
@Service
public class DataMessagesService implements IDataMessagesServices {
    private final String SYMBOL_COMMAND = "/";
    private final IDataMessagesRepository dataMessagesRepository;

    public DataMessagesService(IDataMessagesRepository dataMessagesRepository) {
        this.dataMessagesRepository = dataMessagesRepository;
    }

    @Override
    public List<DataMessage> getAllMessages() {
        return dataMessagesRepository.findAll();
    }

    @Override
    public DataMessage getSingleMessageById(String id) {
        return dataMessagesRepository.getById(id);
    }

    @Override
    public List<DataMessage> getMessagesByChatId(long chatId) {
        return dataMessagesRepository.findAllByChatId(chatId);
    }

    @Override
    public List<DataMessage> getMessagesByDateSend(LocalDateTime dateSend) {
        return dataMessagesRepository.findAllByDateSend(dateSend);
    }

    @Override
    public List<DataMessage> getMessagesByDateSendAndChatId(LocalDateTime dateSend, long chatId) {
        return dataMessagesRepository.findAllByDateSendAndChatId(dateSend,chatId);
    }

    @Override
    public DataMessage saveMessage(DataMessage dataMessage) {
        DataMessage saveMessage = dataMessagesRepository.save(dataMessage);
        return saveMessage;
    }

    // Метод возвращает последнее сообщение чата
    @Override
    public String getLastCommand(long chatId) {
        List<DataMessage> dataMessageList = dataMessagesRepository.findByChatIdOrderByDateSendDesc(chatId);
        if(dataMessageList!=null){
            if(dataMessageList.get(0).getMessage().substring(0,1).equals(SYMBOL_COMMAND)){
                return dataMessageList.get(0).getMessage();
            }
        }
        return null;
    }
}
