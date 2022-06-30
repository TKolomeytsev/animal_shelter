package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.IDataAnimalOwner;
import pro.sky.telegrambot.models.DataAnimalOwner;
import pro.sky.telegrambot.repositories.IDataAnimalOwnerRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>DataAnimalOwnerServices</b> - сервис для работы с опекунами животными.<br/>
 */
@Service
public class DataAnimalOwnerServices implements IDataAnimalOwner {

    Logger logger = LoggerFactory.getLogger(DataAnimalOwnerServices.class);

    private final IDataAnimalOwnerRepository dataAnimalOwnerRepository;

    public DataAnimalOwnerServices(IDataAnimalOwnerRepository dataAnimalOwnerRepository) {
        this.dataAnimalOwnerRepository = dataAnimalOwnerRepository;
    }

    @Override
    public List<DataAnimalOwner> findAll() {
        List<DataAnimalOwner> list = dataAnimalOwnerRepository.findAll();
        if(list.size() > 0){
            logger.info("Выводится список с опекунами животных");
            return list;
        }
        logger.info("Выводится пустой список");
        return null;
    }

    @Override
    public DataAnimalOwner findByIdOwner(String idOwner) {
        logger.info("Поиск опекуна по ID опекуна");
        return dataAnimalOwnerRepository.findByIdOwner(idOwner);
    }

    @Override
    public DataAnimalOwner findByIdAnimal(String idAnimal) {
        logger.info("Поиск опекуна по ID животного");
        return dataAnimalOwnerRepository.findByIdAnimal(idAnimal);
    }

    @Override
    public List<DataAnimalOwner> findByChatId(long chatId) {
        List<DataAnimalOwner> list = dataAnimalOwnerRepository.findByChatId(chatId);
        if(list.size() > 0){
            logger.info("Список опекунов по chatID");
            return list;
        }
        logger.info("Пустой список по поиску по chatId");
        return null;
    }

    @Override
    public DataAnimalOwner save(DataAnimalOwner animalOwner) {
        logger.info("Сохранение нового опекуна для животного");
        DataAnimalOwner dataAnimalOwner = dataAnimalOwnerRepository.save(animalOwner);
        return dataAnimalOwner;
    }

    @Override
    public DataAnimalOwner update(DataAnimalOwner animalOwner) {
        logger.info("Изменение данных опекуна");
        DataAnimalOwner dataAnimalOwner = dataAnimalOwnerRepository.save(animalOwner);
        return dataAnimalOwner;
    }

    @Override
    public DataAnimalOwner deleteByIdOwner(String idOwner) {
        DataAnimalOwner response = findByIdOwner(idOwner);
        if(response!=null){
            try{
                logger.info("Удаление опекуна по ID");
                dataAnimalOwnerRepository.deleteByIdOwner(idOwner);
                return response;
            }catch (ExceptionServerError exceptionServerError){
                logger.warn("При удалении опекуна по ID произошла ошибка");
                throw exceptionServerError;
            }
        }
        return null;
    }

    @Override
    public DataAnimalOwner deleteByIdAnimal(String idAnimal) {
        DataAnimalOwner response = findByIdAnimal(idAnimal);
        if(response!=null){
            try{
                logger.info("Удаление опекуна по ID животного");
                dataAnimalOwnerRepository.deleteByIdAnimal(idAnimal);
                return response;
            }catch (ExceptionServerError exceptionServerError){
                logger.warn("При удалении опекуна по ID животного произошла ошибка");
                throw exceptionServerError;
            }
        }
        return null;
    }

    @Override
    public List<DataAnimalOwner> deleteAllByChatId(long chatId) {
        List<DataAnimalOwner> response = findByChatId(chatId);
        if(response!=null){
            try{
                logger.info("Удаление всех опекунов по ChatId");
                dataAnimalOwnerRepository.deleteAllByChatId(chatId);
                return response;
            }catch (ExceptionServerError exceptionServerError){
                logger.warn("При удалении опекунов по chatId произошла ошибка");
                throw exceptionServerError;
            }
        }
        return null;
    }

    @Override
    public List<DataAnimalOwner> getSendList(LocalDateTime startDate, LocalDateTime endDate) {
        List<DataAnimalOwner> list = dataAnimalOwnerRepository.getSendList(startDate,endDate);
        if(list.size() > 0){
            logger.info("Выводится sendlist опекунов");
            return list;
        }
        logger.info("Выводится пустой список sendList");
        return null;
    }
}
