package pro.sky.telegrambot.services;

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
    private final INsiBreedAnimalRepository nsiBreedAnimalRepository;

    public NsiBreedAnimalServices(INsiBreedAnimalRepository nsiBreedAnimalRepository) {
        this.nsiBreedAnimalRepository = nsiBreedAnimalRepository;
    }


    @Override
    public List<NsiBreedAnimal> getAllBreedAnimal() {
        List<NsiBreedAnimal> nsiBreedAnimals = nsiBreedAnimalRepository.findAll();
        if(nsiBreedAnimals!=null){
            return nsiBreedAnimals;
        }else {
            throw new ExceptionNotFoundBreedAnimal();
        }
    }

    @Override
    public NsiBreedAnimal getAllBreedAnimalById(String id) {
        Optional<NsiBreedAnimal> nsiBreedAnimal = nsiBreedAnimalRepository.findById(id);
        if(nsiBreedAnimal.isPresent()){
            return nsiBreedAnimal.get();
        }else {
            return null;
        }
    }

    @Override
    public List<NsiBreedAnimal> getAllBreedAnimalByName(String name) {
        List<NsiBreedAnimal> nsiBreedAnimal = nsiBreedAnimalRepository.findByName(name);
        if(nsiBreedAnimal!=null){
            return nsiBreedAnimal;
        }else {
            throw new ExceptionNotFoundBreedAnimal();
        }
    }

    @Override
    public NsiBreedAnimal save(NsiBreedAnimal breed) {
        try{
            return nsiBreedAnimalRepository.save(breed);
        }catch (ExceptionServerError exceptionServerError){
            throw exceptionServerError;
        }
    }

    @Override
    public NsiBreedAnimal update(NsiBreedAnimal breed) {
        try{
            return nsiBreedAnimalRepository.save(breed);
        }catch (ExceptionServerError exceptionServerError){
            throw exceptionServerError;
        }
    }

    @Override
    public NsiBreedAnimal delete(String id) {
        NsiBreedAnimal nsiBreedAnimal = getAllBreedAnimalById(id);
        if(nsiBreedAnimal!=null){
            try{
                nsiBreedAnimalRepository.deleteById(id);
                return nsiBreedAnimal;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }else{
            throw new ExceptionNotFoundBreedAnimal();
        }
    }

    public List<String> getCommands(List<NsiBreedAnimal> eList) {
        List<String> list = new ArrayList<>();
        for(NsiBreedAnimal item : eList){
            list.add("/" + item.getId()+"\n");
            list.add(item.getName()+"\n");
            list.add("\n");
        }
        return list;
    }
}
