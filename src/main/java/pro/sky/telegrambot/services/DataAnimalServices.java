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

    Logger logger = LoggerFactory.getLogger(DataAnimalServices.class);

    private final IDataAnimalRepository dataAnimalRepository;

    public DataAnimalServices(IDataAnimalRepository dataAnimalRepository) {
        this.dataAnimalRepository = dataAnimalRepository;
    }

    @Override
    public List<DataAnimal> getAllAnimal() {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findAll();
        if(dataAnimals!=null){
            logger.info("Return all Animals");
            return dataAnimals;
        }else {
            logger.warn("Don`t find animals");
            throw new ExceptionNotFoundDataAnimal();
        }
    }

    @Override
    public DataAnimal getAllDataAnimalById(String id) {
        Optional<DataAnimal> dataAnimal = dataAnimalRepository.findById(id);
        if(dataAnimal.isPresent()){
            logger.info("Return animal by id");
            return dataAnimal.get();
        }else {
            logger.info("Don`t find animal by id");
            return null;
        }
    }

    @Override
    public List<DataAnimal> getAllDataAnimalByIdKind(String idKind) {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findByNsiAnimalKind(idKind);
        if(dataAnimals!=null){
            logger.info("Find animal by idKind");
            return dataAnimals;
        }else {
            logger.warn("Not Found Animal by idKind");
            throw new ExceptionNotFoundDataAnimal();
        }
    }

    @Override
    public List<DataAnimal> getAllDataAnimalByIdBreed(String idBreed) {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findByNsiBreedAnimal(idBreed);
        if(dataAnimals!=null){
            logger.info("Find animal by idBreed");
            return dataAnimals;
        }else {
            logger.warn("Don`t find animal by idBreed");
            throw new ExceptionNotFoundDataAnimal();
        }
    }

    @Override
    public DataAnimal save(DataAnimal dataAnimal) {
        try{
            logger.info("Save new animal");
            return dataAnimalRepository.save(dataAnimal);
        }catch (ExceptionServerError exceptionServerError){
            logger.error("Save error");
            throw exceptionServerError;
        }
    }

    @Override
    public DataAnimal update(DataAnimal dataAnimal) {
        try{
            logger.info("Update animal");
            return dataAnimalRepository.save(dataAnimal);
        }catch (ExceptionServerError exceptionServerError){
            logger.error("Update error");
            throw exceptionServerError;
        }
    }

    @Override
    public DataAnimal delete(String id) {
        DataAnimal dataAnimal = dataAnimalRepository.getById(id);
        if(dataAnimal!=null){
            try{
                logger.info("Delete animal by ID");
                dataAnimalRepository.deleteById(id);
                return dataAnimal;
            }catch (ExceptionServerError exceptionServerError){
                logger.error("Delete by id error");
                throw exceptionServerError;
            }
        }else{
            logger.warn("Don`t found animal to delete by id");
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public List<DataAnimal> deleteAllDataAnimalByIdKind(String idKind) {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findByNsiAnimalKind(idKind);
        if(dataAnimals!=null){
            try{
                logger.info("Delete animal`s by IDKind");
                dataAnimalRepository.deleteByNsiAnimalKind(idKind);
                return dataAnimals;
            }catch (ExceptionServerError exceptionServerError){
                logger.error("Delete by idKind error");
                throw exceptionServerError;
            }
        }else{
            logger.warn("Don`t found animal to delete by idKind");
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public List<DataAnimal> deleteAllDataAnimalByIdBreed(String idBreed) {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findByNsiAnimalKind(idBreed);
        if(dataAnimals!=null){
            try{
                logger.info("Delete animal`s by IdBreed");
                dataAnimalRepository.deleteByNsiBreedAnimal(idBreed);
                return dataAnimals;
            }catch (ExceptionServerError exceptionServerError){
                logger.error("Delete by idBreed error");
                throw exceptionServerError;
            }
        }else{
            logger.warn("Don`t found animal to delete by idBreed");
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    public List<String> getCommands(List<DataAnimal> eList) {
        logger.info("Get id and name animals");
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
        logger.info("Get info by all animals");
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
