package pro.sky.telegrambot.services;

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
    private final IDataAnimalOwnerRepository dataAnimalOwnerRepository;

    public DataAnimalOwnerServices(IDataAnimalOwnerRepository dataAnimalOwnerRepository) {
        this.dataAnimalOwnerRepository = dataAnimalOwnerRepository;
    }

    @Override
    public List<DataAnimalOwner> findAll() {
        List<DataAnimalOwner> list = dataAnimalOwnerRepository.findAll();
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    @Override
    public DataAnimalOwner findByIdOwner(String idOwner) {
        return dataAnimalOwnerRepository.findByIdOwner(idOwner);
    }

    @Override
    public DataAnimalOwner findByIdAnimal(String idAnimal) {
        return dataAnimalOwnerRepository.findByIdAnimal(idAnimal);
    }

    @Override
    public List<DataAnimalOwner> findByChatId(long chatId) {
        List<DataAnimalOwner> list = dataAnimalOwnerRepository.findByChatId(chatId);
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    @Override
    public DataAnimalOwner save(DataAnimalOwner animalOwner) {
        DataAnimalOwner dataAnimalOwner = dataAnimalOwnerRepository.save(animalOwner);
        return dataAnimalOwner;
    }

    @Override
    public DataAnimalOwner update(DataAnimalOwner animalOwner) {
        DataAnimalOwner dataAnimalOwner = dataAnimalOwnerRepository.save(animalOwner);
        return dataAnimalOwner;
    }

    @Override
    public DataAnimalOwner deleteByIdOwner(String idOwner) {
        DataAnimalOwner response = findByIdOwner(idOwner);
        if(response!=null){
            try{
                dataAnimalOwnerRepository.deleteByIdOwner(idOwner);
                return response;
            }catch (ExceptionServerError exceptionServerError){
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
                dataAnimalOwnerRepository.deleteByIdAnimal(idAnimal);
                return response;
            }catch (ExceptionServerError exceptionServerError){
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
                dataAnimalOwnerRepository.deleteAllByChatId(chatId);
                return response;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }
        return null;
    }

    @Override
    public List<DataAnimalOwner> getSendList(LocalDateTime startDate, LocalDateTime endDate) {
        List<DataAnimalOwner> list = dataAnimalOwnerRepository.getSendList(startDate,endDate);
        if(list.size() > 0){
            return list;
        }
        return null;
    }
}
