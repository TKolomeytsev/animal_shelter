package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalKind;
import pro.sky.telegrambot.exception.ExceptionNotFoundDataAnimal;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.IDataAnimal;
import pro.sky.telegrambot.models.DataAnimal;
import pro.sky.telegrambot.repositories.IDataAnimalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author AKolomeytsev<br/>
 * <b>DataAnimalServices</b> - сервис для работы с животными.<br/>
 */
@Service
public class DataAnimalServices implements IDataAnimal {
    private final IDataAnimalRepository dataAnimalRepository;
    private Logger logger = LoggerFactory.getLogger(DataAnimalServices.class);

    public DataAnimalServices(IDataAnimalRepository dataAnimalRepository) {
        this.dataAnimalRepository = dataAnimalRepository;
    }

    @Override
    public List<DataAnimal> getAllAnimal() {
        logger.info("Пользователь запросил список животных...");
        List<DataAnimal> dataAnimals = dataAnimalRepository.findAll();
        if(dataAnimals!=null){
            logger.info("Список животных получен...");
            return dataAnimals;
        }else {
            logger.info("При получении списка животных произошла ошибка...");
            throw new ExceptionNotFoundDataAnimal();
        }
    }

    @Override
    public DataAnimal getAllDataAnimalById(String id) {
        logger.info("Пользователь запросил животного с id - " + id);
        Optional<DataAnimal> dataAnimal = dataAnimalRepository.findById(id);
        if(dataAnimal.isPresent()){
            logger.info("Животного с id - " + id + " найдено");
            return dataAnimal.get();
        }else {
            logger.info("Животного с id - " + id + " не найдено");
            return null;
        }
    }

    @Override
    public List<DataAnimal> getAllDataAnimalByIdKind(String idKind) {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findByNsiAnimalKind(idKind);
        if(dataAnimals!=null){
            return dataAnimals;
        }else {
            throw new ExceptionNotFoundDataAnimal();
        }
    }

    @Override
    public List<DataAnimal> getAllDataAnimalByIdBreed(String idBreed) {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findByNsiBreedAnimal(idBreed);
        if(dataAnimals!=null){
            return dataAnimals;
        }else {
            throw new ExceptionNotFoundDataAnimal();
        }
    }

    @Override
    public DataAnimal save(DataAnimal dataAnimal) {
        try{
            return dataAnimalRepository.save(dataAnimal);
        }catch (ExceptionServerError exceptionServerError){
            throw exceptionServerError;
        }
    }

    @Override
    public DataAnimal update(DataAnimal dataAnimal) {
        try{
            return dataAnimalRepository.save(dataAnimal);
        }catch (ExceptionServerError exceptionServerError){
            throw exceptionServerError;
        }
    }

    @Override
    public DataAnimal delete(String id) {
        DataAnimal dataAnimal = dataAnimalRepository.getById(id);
        if(dataAnimal!=null){
            try{
                dataAnimalRepository.deleteById(id);
                return dataAnimal;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }else{
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public List<DataAnimal> deleteAllDataAnimalByIdKind(String idKind) {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findByNsiAnimalKind(idKind);
        if(dataAnimals!=null){
            try{
                dataAnimalRepository.deleteByNsiAnimalKind(idKind);
                return dataAnimals;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }else{
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public List<DataAnimal> deleteAllDataAnimalByIdBreed(String idBreed) {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findByNsiAnimalKind(idBreed);
        if(dataAnimals!=null){
            try{
                dataAnimalRepository.deleteByNsiBreedAnimal(idBreed);
                return dataAnimals;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }else{
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    public List<String> getCommands(List<DataAnimal> eList) {
        List<String> list = new ArrayList<>();
        for(DataAnimal item : eList){
            list.add("----------------"+"\n");
            list.add("/" + item.getId()+"\n");
            list.add(item.getNickname()+"\n");
            list.add("----------------"+"\n");
        }
        return list;
    }

    public List<String> getAnimalInfo(DataAnimal eList) {
        List<String> list = new ArrayList<>();
        list.add("----------------" + "\n");
        list.add("Кличка: " + eList.getNickname()+"\n");
        list.add("Возраст: " + eList.getAge()+"\n");
        list.add("Рост: " + eList.getGrowth()+"\n");
        list.add("Вес: " + eList.getWeight()+"\n");
        list.add("Цет: " + eList.getColor()+"\n");
        list.add("----------------"+"\n");
        return list;
    }
}
