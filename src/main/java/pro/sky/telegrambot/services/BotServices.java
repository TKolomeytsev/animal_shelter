package pro.sky.telegrambot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.interfaces.IBotServices;
import pro.sky.telegrambot.models.DataMessage;
import pro.sky.telegrambot.models.NsiCommands;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
public class BotServices implements IBotServices {
    private final NsiCommandServices commandServices;
    private final DataMessagesService dataMessagesService;
    private final String SYMBOL_COMMAND = "/";

    private final TelegramBot telegramBot;

    public BotServices(NsiCommandServices commandServices, DataMessagesService dataMessagesService, TelegramBot telegramBot) {
        this.commandServices = commandServices;
        this.dataMessagesService = dataMessagesService;
        this.telegramBot = telegramBot;
    }

    @Override
    public DataMessage readMessage(Update update) {
        DataMessage dataMessage = new DataMessage();
        dataMessage.setMessage(update.message().text().trim());
        dataMessage.setChatId(update.message().chat().id());
        dataMessage.setDateSend(getCurDate());
        return dataMessage;
    }

    // Метод перенаправления
    @Override
    public void router(Update update) {
        DataMessage dataMessage = readMessage(update);
        DataMessage savedMessage = dataMessagesService.saveMessage(dataMessage);
        if(dataMessagesService.getMessagesByChatId(dataMessage.getChatId()).size()==0){
            if(savedMessage != null){
                send(dataMessage.getChatId(),"Преветствую Вас в нашем телеграмм канале");
                send(dataMessage.getChatId(),"Список команд:");
                send(dataMessage.getChatId(),returnResponse(commandServices.getAllCommands()));
            }else{
                send(dataMessage.getChatId(),"Проблемы с сервисом");
            }
        }else if(dataMessage.getMessage().substring(0,1).equals(SYMBOL_COMMAND)){
            List<NsiCommands> commandsList = commandServices.getCommandByCommand(dataMessage.getMessage());
            if (commandsList.size() == 1) {

            } else if (commandsList.size() > 1) {
                send(dataMessage.getChatId(),"Найдено более одной команды. Уточните команду.");
            } else {
                send(dataMessage.getChatId(),"Данная комада отсутвует в списке зарегистрированных команд.");
                send(dataMessage.getChatId(),"Перевожу на волонтера.");
            }
        }else{
            //send(dataMessage.getChatId(),"Список команд:");
            String lastCommand = dataMessagesService.getLastCommand(dataMessage.getChatId());
            if(lastCommand!=null)
                send(dataMessage.getChatId(),lastCommand);
        }
    }

    // Метод отправки сообщения
    private void send(long chatId, String returnResponse) {
        SendMessage sendMessage = new SendMessage(chatId,returnResponse);
        telegramBot.execute(sendMessage);
    }

    // Метод возврата ответа
    private String returnResponse(List<NsiCommands> allCommands) {
        StringBuilder returnString = new StringBuilder();
        for(NsiCommands command : allCommands){
           returnString.append((command.responseAsString()));
        }
        return returnString.toString();
    }

    // Метод возврата текущего времени в нужном формате
    private LocalDateTime getCurDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String date = formatForDateNow.format(dateNow);
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}
