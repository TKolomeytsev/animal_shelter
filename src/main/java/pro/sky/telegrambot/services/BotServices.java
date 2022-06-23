package pro.sky.telegrambot.services;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.PhotoSize;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.interfaces.IBotServices;
import pro.sky.telegrambot.models.*;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

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
    private final StandartResponseService responseService;
    private final DataAnimalServices animalServices;
    private final String SYMBOL_COMMAND = "/";

    private final TelegramBot telegramBot;
    private PhotoSize a;
    private PhotoSize a1;
    private PhotoSize a11;
    private PhotoSize a12;

    public BotServices(NsiCommandServices commandServices, DataMessagesService dataMessagesService, NsiAnimalKindServices animalKindServices, NsiBreedAnimalServices breedAnimalServices, DataAnimalPhotoService animalPhotoService, StandartResponseService responseService, DataAnimalServices animalServices, TelegramBot telegramBot) {
        this.commandServices = commandServices;
        this.dataMessagesService = dataMessagesService;
        this.animalKindServices = animalKindServices;
        this.breedAnimalServices = breedAnimalServices;
        this.animalPhotoService = animalPhotoService;
        this.responseService = responseService;
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
        if(update.message().photo()!=null || update.message().document()!=null){
            workWithFiles(update);
        }

        dataMessage.setMessage(update.message().text()!=null?update.message().text().trim():"");
        dataMessage.setChatId(update.message().chat().id());
        dataMessage.setDateSend(getCurDate());
        return dataMessage;
    }

    private void workWithFiles(Update update) {
        if(update.message().photo()!=null) {
            send(update.message().chat().id(), update.message().photo()[0].fileId().getBytes());
            send(update.message().chat().id(), "a");


        }else if(update.message().document()!=null){

        }else{

        }
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
        if(!dataMessage.getMessage().isEmpty()) {
            DataMessage savedMessage = dataMessagesService.saveMessage(dataMessage);
            List<String> commandsList = new ArrayList<>();
            switch (dataMessage.getMessage().substring(1)) {
                case "start":
                    commandsList = commandServices.getCommands(commandServices.findByLevel(1));
                    send(dataMessage.getChatId(), returnResponse(commandsList));
                    break;
                default:
                    if (dataMessage.getMessage().substring(0, 1).equals(SYMBOL_COMMAND)) {
                        commandsList = commandServices.getCommands(commandServices.getCommandByCommand(dataMessage.getMessage().substring(1)));
                        List<String> commandsList1 = new ArrayList<>();
                        if (commandsList.size() == 1) {
                            NsiCommands nsiCommand = commandServices.getCommandByCommand(dataMessage.getMessage().substring(1)).get(0);
                            commandsList1 = commandServices.getCommands(commandServices.findByLevel(nsiCommand.getDescription()));
                            if (commandsList1.size() == 0) {
                                commandsList1 = subRouter(dataMessage);
                            }
                        } else {
                            commandsList1 = subRouter(dataMessage);
                        }
                        send(dataMessage.getChatId(), returnResponse(commandsList1));
                    } else {
                        if (savedMessage == null) {
                            System.out.println("при записи сообщения возникла ошибка");
                        } else {
                            send(dataMessage.getChatId(), "Контактная информция записана, ожидайте звонок волонтера.");
                        }
                    }
                    break;
            }
        }
    }

    private List<String> subRouter(DataMessage message) {
        if(message.getMessage().substring(1).equals("Выбрать приют")) {
            List<NsiAnimalKind> shelters = animalKindServices.getAllAnimalKind();
            return  makeCommands(animalKindServices.getCommands(shelters));
        }else {

                NsiAnimalKind kind = animalKindServices.getAllAnimalKindById(message.getMessage().substring(1));
                List<DataAnimal> animals = new ArrayList<>();
                if (kind != null) {
                    animals = animalServices.getAllDataAnimalByIdKind(kind.getId());
                    if (animals.size() > 0) {
                        List<String> returnResponsesAndAnimals = new ArrayList<>();
                        returnResponsesAndAnimals.add("******* Список вопросов *******" + "\n");
                        List<StandartResponse> responses4Animals = responseService.getAllStandartResponseByRelationId(message.getMessage().substring(1));
                        List<String> listResponses = makeCommands(responseService.getCommands(responses4Animals));
                        returnResponsesAndAnimals.addAll(listResponses);
                        returnResponsesAndAnimals.add("*******************************" + "\n");
                        returnResponsesAndAnimals.add("\n");
                        returnResponsesAndAnimals.add("******* Список животных *******" + "\n");
                        List<String> listAnimasl = makeCommands(animalServices.getCommands(animals));
                        returnResponsesAndAnimals.addAll(listAnimasl);
                        returnResponsesAndAnimals.add("*******************************" + "\n");
                        return returnResponsesAndAnimals;
                    } else {
                        return makeCommands(getNullResponse("В приюте нет животных"));
                    }
                } else if (kind == null) {
                    NsiBreedAnimal breedAnimals = breedAnimalServices.getAllBreedAnimalById(message.getMessage().substring(1));
                    if (breedAnimals != null) {
                        animals = animalServices.getAllDataAnimalByIdBreed(breedAnimals.getId());
                        if (animals.size() > 0) {
                            return makeCommands(animalServices.getCommands(animals));
                        } else {
                            return makeCommands(getNullResponse("В приюте нет животных"));
                        }
                    } else if (breedAnimals == null) {
                        List<StandartResponse> responses = responseService.getAllStandartResponseByRelationId(message.getMessage().substring(1));
                        if(responses!=null) {
                            return makeCommands(responseService.getCommands(responses));
                        } else if(responses == null) {
                            StandartResponse response = responseService.getStandartResponseByResId(message.getMessage().substring(1));
                            if(response!=null) {
                                return makeCommands(responseService.getStandartResponseInfo(response));
                            }else if(response == null) {
                                DataAnimal animal = animalServices.getAllDataAnimalById(message.getMessage().substring(1));
                                if(animal!=null) {
                                    Collection<DataAnimalPhoto> photos = animal.getDataAnimalPhotos();
                                    if (photos.size() > 0) {
                                        for (DataAnimalPhoto item : photos) {
                                            send(message.getChatId(), item.getContent());
                                        }
                                    }
                                    return makeCommands(animalServices.getAnimalInfo(animal));
                                }
                            }

                        }
                    } else {
                        List<String> commands = new ArrayList<>();
                        commands.add("Команда не распознана." + "\n");
                        commands.add("Перевожу на волонтера..." + "\n");
                        return commands;
                    }
                }
        }
        return null;
    }

    private List<String> getNullResponse(String value) {
        List<String> response = new ArrayList<>();
        response.add(value);
        return response;
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
        if(allCommands == null) {
            return "Команда не распознана." + "\n" + "Перевожу на волонтера..." + "\n";
        }else{
            StringBuilder returnString = new StringBuilder();
            for (String command : allCommands) {
                returnString.append(command);
            }
            return returnString.toString();
        }
    }

    private LocalDateTime getCurDate(){
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        String date = formatForDateNow.format(dateNow);
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
}
