package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.ExceptionNotFoundBreedAnimal;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.INsiBreedAnimal;
import pro.sky.telegrambot.models.NsiBreedAnimal;
import pro.sky.telegrambot.repositories.INsiBreedAnimalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author AKolomeytsev<br/>
 * <b>NsiBreedAnimalServices</b> - сервис для работы с породами животных.<br/>
 */
@Service
public class NsiBreedAnimalServices implements INsiBreedAnimal {

    Logger logger = LoggerFactory.getLogger(NsiBreedAnimalServices.class);

    private final INsiBreedAnimalRepository nsiBreedAnimalRepository;

    public NsiBreedAnimalServices(INsiBreedAnimalRepository nsiBreedAnimalRepository) {
        this.nsiBreedAnimalRepository = nsiBreedAnimalRepository;
    }


    @Override
    public List<NsiBreedAnimal> getAllBreedAnimal() {
        List<NsiBreedAnimal> nsiBreedAnimals = nsiBreedAnimalRepository.findAll();
        if(nsiBreedAnimals!=null){
            logger.info("Get all animals breed");
            return nsiBreedAnimals;
        }else {
            logger.warn("Don`t find animals breed");
            throw new ExceptionNotFoundBreedAnimal();
        }
    }

    @Override
    public NsiBreedAnimal getAllBreedAnimalById(String id) {
        Optional<NsiBreedAnimal> nsiBreedAnimal = nsiBreedAnimalRepository.findById(id);
        if(nsiBreedAnimal.isPresent()){
            logger.info("Get animal breed by id");
            return nsiBreedAnimal.get();
        }else {
            logger.warn("Don`t find animal breed by id");
            return null;
        }
    }

    @Override
    public List<NsiBreedAnimal> getAllBreedAnimalByName(String name) {
        List<NsiBreedAnimal> nsiBreedAnimal = nsiBreedAnimalRepository.findByName(name);
        if(nsiBreedAnimal!=null){
            logger.info("Get all breed animal by name");
            return nsiBreedAnimal;
        }else {
            logger.warn("Don`t find animal breed by name");
            throw new ExceptionNotFoundBreedAnimal();
        }
    }

    @Override
    public NsiBreedAnimal save(NsiBreedAnimal breed) {
        try{
            logger.info("Save new animal breed");
            return nsiBreedAnimalRepository.save(breed);
        }catch (ExceptionServerError exceptionServerError){
            logger.error("Server save new animal breed error");
            throw exceptionServerError;
        }
    }

    @Override
    public NsiBreedAnimal update(NsiBreedAnimal breed) {
        try{
            logger.info("Update animal breed");
            return nsiBreedAnimalRepository.save(breed);
        }catch (ExceptionServerError exceptionServerError){
            logger.error("Server update animal breed error");
            throw exceptionServerError;
        }
    }

    @Override
    public NsiBreedAnimal delete(String id) {
        NsiBreedAnimal nsiBreedAnimal = getAllBreedAnimalById(id);
        if(nsiBreedAnimal!=null){
            try{
                logger.info("Delete animal breed by id");
                nsiBreedAnimalRepository.deleteById(id);
                return nsiBreedAnimal;
            }catch (ExceptionServerError exceptionServerError){
                logger.error("Server delete animal breed by id error");
                throw exceptionServerError;
            }
        }else{
            logger.warn("Don` find animal breed to delete");
            throw new ExceptionNotFoundBreedAnimal();
        }
    }

    public List<String> getCommands(List<NsiBreedAnimal> eList) {
        logger.info("Get all animal breed name and id");
        List<String> list = new ArrayList<>();
        for(NsiBreedAnimal item : eList){
            list.add("/" + item.getId()+"\n");
            list.add(item.getName()+"\n");
            list.add("\n");
        }
        return list;
    }
}
