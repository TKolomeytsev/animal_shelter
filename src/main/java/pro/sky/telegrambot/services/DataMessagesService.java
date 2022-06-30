package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(DataMessagesService.class);

    private final String SYMBOL_COMMAND = "/";
    private final IDataMessagesRepository dataMessagesRepository;

    public DataMessagesService(IDataMessagesRepository dataMessagesRepository) {
        this.dataMessagesRepository = dataMessagesRepository;
    }

    @Override
    public List<DataMessage> getAllMessages() {
        logger.info("Get all data messages");
        return dataMessagesRepository.findAll();
    }

    @Override
    public DataMessage getSingleMessageById(String id) {
        logger.info("Get single data message");
        return dataMessagesRepository.getById(id);
    }

    @Override
    public List<DataMessage> getMessagesByChatId(long chatId) {
        logger.info("Get all data message by  chatId");
        return dataMessagesRepository.findAllByChatId(chatId);
    }

    @Override
    public List<DataMessage> getMessagesByDateSend(LocalDateTime dateSend) {
        logger.info("Get all data message by data send");
        return dataMessagesRepository.findAllByDateSend(dateSend);
    }

    @Override
    public List<DataMessage> getMessagesByDateSendAndChatId(LocalDateTime dateSend, long chatId) {
        logger.info("Get all data message by data send and chat id");
        return dataMessagesRepository.findAllByDateSendAndChatId(dateSend,chatId);
    }

    @Override
    public DataMessage saveMessage(DataMessage dataMessage) {
        logger.info("Save new message");
        DataMessage saveMessage = dataMessagesRepository.save(dataMessage);
        return saveMessage;
    }

    // Метод возвращает последнее сообщение чата
    @Override
    public String getLastCommand(long chatId) {
        logger.info("Get last message from chat");
        List<DataMessage> dataMessageList = dataMessagesRepository.findByChatIdOrderByDateSendDesc(chatId);
        if(dataMessageList!=null){
            if(dataMessageList.get(0).getMessage().substring(0,1).equals(SYMBOL_COMMAND)){
                return dataMessageList.get(0).getMessage();
            }
        }
        return null;
    }
}
