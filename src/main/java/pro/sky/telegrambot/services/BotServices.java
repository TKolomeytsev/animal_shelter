package pro.sky.telegrambot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.interfaces.IBotServices;
import pro.sky.telegrambot.models.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Класс имплементирован от интерфейса {@link IBotServices}
 */
@Service
public class BotServices implements IBotServices {
    private final NsiCommandServices commandServices;
    private final DataMessagesService dataMessagesService;
    private final NsiAnimalKindServices animalKindServices;
    private final NsiBreedAnimalServices breedAnimalServices;
    private final DataAnimalPhotoService animalPhotoService;
    private final DataAnimalServices animalServices;
    private final String SYMBOL_COMMAND = "/";

    private final TelegramBot telegramBot;

    public BotServices(NsiCommandServices commandServices, DataMessagesService dataMessagesService, NsiAnimalKindServices animalKindServices, NsiBreedAnimalServices breedAnimalServices, DataAnimalPhotoService animalPhotoService, DataAnimalServices animalServices, TelegramBot telegramBot) {
        this.commandServices = commandServices;
        this.dataMessagesService = dataMessagesService;
        this.animalKindServices = animalKindServices;
        this.breedAnimalServices = breedAnimalServices;
        this.animalPhotoService = animalPhotoService;
        this.animalServices = animalServices;
        this.telegramBot = telegramBot;
    }

    /**
     * Реализует метод интерфейса {@link IBotServices}
     * @param update
     * @return {@link DataMessage}
     */
    @Override
    public DataMessage readMessage(Update update) {
        DataMessage dataMessage = new DataMessage();
        dataMessage.setMessage(update.message().text().trim());
        dataMessage.setChatId(update.message().chat().id());
        dataMessage.setDateSend(getCurDate());
        return dataMessage;
    }

    /**
     * Реализует метод интерфейса {@link IBotServices}
     * @param update
     * @return ничего не возвращает
     */
    @Override
    public void router(Update update) {

    }

    @Override
    public void returnResponsToBot(Update update) {
        DataMessage dataMessage = readMessage(update);
        List<String> commandsList = new ArrayList<>();
        switch (dataMessage.getMessage().substring(1)){
            case "start":
                commandsList = commandServices.getCommands(commandServices.findByLevel(0));
                send(dataMessage.getChatId(), returnResponse(commandsList));
                break;
            case "О приюте":
                send(dataMessage.getChatId(),"Муниципальный приют для бездомных животных");
                send(dataMessage.getChatId(),"Приют работает с 9-00 до 18-00");
                send(dataMessage.getChatId(),"По адресу: 357100, г. Невинномысск, ул. Ленина 53");
                break;
            case "Общие рекомендации безопасности на территории приюта":
                send(dataMessage.getChatId(),"На собак не гавкать, на кошек не мяукать и на змей не шипеть");
                break;
            case "Записать контактные данные для связи":
                send(dataMessage.getChatId(),"Введите свои контакные данные.");
                send(dataMessage.getChatId(),"Формат вода - т: +7(000)000-00-00, Ф.И.О.");
                break;
            default:
                if(dataMessage.getMessage().substring(0,1).equals(SYMBOL_COMMAND)){
                    commandsList = commandServices.getCommands(commandServices.getCommandByCommand(dataMessage.getMessage().substring(1)));
                    List<String> commandsList1 = new ArrayList<>();
                    if (commandsList.size() == 1) {
                        NsiCommands nsiCommand = commandServices.getCommandByCommand(dataMessage.getMessage().substring(1)).get(0);
                        commandsList1 = commandServices.getCommands(commandServices.findByLevel(nsiCommand.getDescription()));
                        if(commandsList1.size() == 0) {
                            commandsList1 = subRouter(dataMessage);
                        }
                    }else{
                        commandsList1 = subRouter(dataMessage);
                    }
                    send(dataMessage.getChatId(), returnResponse(commandsList1));
                }else{
                    DataMessage savedMessage = dataMessagesService.saveMessage(dataMessage);
                    if(savedMessage==null){
                        System.out.println("при записи сообщения возникла ошибка");
                    }else{
                        send(dataMessage.getChatId(),"Контактная информция записана, ожидайте звонок волонтера.");
                    }
                }
                break;
        }
    }

    private List<String> subRouter(DataMessage message) {
        if(message.getMessage().substring(1).equals("Выбрать приют")) {
            List<NsiAnimalKind> shelters = animalKindServices.getAllAnimalKind();
            return  makeCommands(animalKindServices.getCommands(shelters));
        }else {
            List<NsiAnimalKind> kind = animalKindServices.getAllAnimalKindByName(message.getMessage().substring(1));
            List<DataAnimal> animals = new ArrayList<>();
            if(kind.size() > 0) {
                animals = animalServices.getAllDataAnimalByIdKind(kind.get(0).getId());
                if (animals.size() > 0) {
                    return makeCommands(animalServices.getCommands(animals));
                }
            }else if (kind.size() == 0) {
                List<NsiBreedAnimal> breedAnimals = breedAnimalServices.getAllBreedAnimalByName(message.getMessage().substring(1));
                if(breedAnimals.size() > 0) {
                    animals = animalServices.getAllDataAnimalByIdBreed(breedAnimals.get(0).getId());
                    if (animals.size() > 0) {
                        return makeCommands(animalServices.getCommands(animals));
                    }
                }else if(animals.size() == 0) {
                    DataAnimal animal = animalServices.getAllDataAnimalById(message.getMessage().substring(1));
                    Collection<DataAnimalPhoto> photos = animal.getDataAnimalPhotos();
                    if(photos.size()>0){
                        for(DataAnimalPhoto item : photos){
                            send(message.getChatId(),item.getContent());
                        }
                    }
                    return makeCommands(animalServices.getAnimalInfo(animal));
                }else {
                    List<String> commands = new ArrayList<>();
                    commands.add("Команда не распознана." + "\n");
                    commands.add("Перевожу на волонтера..." + "\n");
                    return  commands;
                }
            }
        }
        return null;
    }

    private List<String> makeCommands(List<String> list) {
        List<String> commands = new ArrayList<>();
        for(String item : list){
            commands.add(item);
        }
        return commands;
    }

    /**
     * Метод отправки сообщения
     * @param chatId идентификатор чата
     * @param returnResponse текстовая иформация
     */
    private void send(long chatId, String returnResponse) {
        SendMessage sendMessage = new SendMessage(chatId,returnResponse);
        telegramBot.execute(sendMessage);
    }

    private void send(long chatId, byte[] bytes) {
        SendPhoto sendPhoto = new SendPhoto(chatId,bytes);
        telegramBot.execute(sendPhoto);
    }

    private String returnResponse(List<String> allCommands) {
        StringBuilder returnString = new StringBuilder();
        for(String command : allCommands){
           returnString.append(command);
        }
        return returnString.toString();
    }

    private LocalDateTime getCurDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String date = formatForDateNow.format(dateNow);
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}
