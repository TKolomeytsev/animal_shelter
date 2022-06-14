package pro.sky.telegrambot.services;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalKind;
import pro.sky.telegrambot.exception.ExceptionNotFoundDataAnimal;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.IDataAnimal;
import pro.sky.telegrambot.models.DataAnimal;
import pro.sky.telegrambot.repositories.IDataAnimalRepository;

import java.util.List;

@Service
public class DataAnimalServices implements IDataAnimal {
    private final IDataAnimalRepository dataAnimalRepository;

    public DataAnimalServices(IDataAnimalRepository dataAnimalRepository) {
        this.dataAnimalRepository = dataAnimalRepository;
    }

    @Override
    public List<DataAnimal> getAllAnimal() {
        List<DataAnimal> dataAnimals = dataAnimalRepository.findAll();
        if(dataAnimals!=null){
            return dataAnimals;
        }else {
            throw new ExceptionNotFoundDataAnimal();
        }
    }

    @Override
    public DataAnimal getAllDataAnimalById(String id) {
        DataAnimal dataAnimal = dataAnimalRepository.findById(id).get();
        if(dataAnimal!=null){
            return dataAnimal;
        }else {
            throw new ExceptionNotFoundDataAnimal();
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
}
