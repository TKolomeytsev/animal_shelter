package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalKind;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalPhoto;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.IDataAnimalPhoto;
import pro.sky.telegrambot.models.DataAnimalPhoto;
import pro.sky.telegrambot.repositories.IDataAnimalPhotoRepository;
import pro.sky.telegrambot.repositories.IDataAnimalRepository;

import java.io.*;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>DataAnimalPhotoService</b> - сервис для работы с изображениями животных.<br/>
 */
@Service
public class DataAnimalPhotoService implements IDataAnimalPhoto {
    private final IDataAnimalPhotoRepository dataAnimalPhotoRepository;
    private final IDataAnimalRepository dataAnimalRepository;
    private Logger logger = LoggerFactory.getLogger(DataAnimalPhotoService.class);

    public DataAnimalPhotoService(IDataAnimalPhotoRepository dataAnimalPhotoRepository, IDataAnimalRepository dataAnimalRepository) {
        this.dataAnimalPhotoRepository = dataAnimalPhotoRepository;
        this.dataAnimalRepository = dataAnimalRepository;
    }

    @Override
    public List<DataAnimalPhoto> getAllAnimalPhoto() {
        List<DataAnimalPhoto> dataAnimalPhotos = dataAnimalPhotoRepository.findAll();
        if(dataAnimalPhotos!=null){
            return dataAnimalPhotos;
        }else {
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public List<DataAnimalPhoto> getAllAnimalPhotoByIdAnimal(String idAnimal) {
        List<DataAnimalPhoto> dataAnimalPhotos = dataAnimalPhotoRepository.findByDataAnimal(idAnimal);
        if(dataAnimalPhotos.size()>0){
            return dataAnimalPhotos;
        }else {
            throw new ExceptionNotFoundAnimalPhoto();
        }
    }

    @Override
    public List<byte[]> getPhotoByIdAnimal(String idAnimal) {
        logger.info("Пользователь запросил фото животного");
        List<byte[]> photos = dataAnimalPhotoRepository.getPhotoByIdAnimal(idAnimal);
        if(photos.size()>0){
            logger.info("Пользователь получил фото животного");
            return photos;
        }else {
            logger.info("Фото животного отсутствует");
            throw new ExceptionNotFoundAnimalPhoto();
        }
    }

    @Override
    public DataAnimalPhoto getAllAnimalPhotoById(String id) {
        DataAnimalPhoto dataAnimalPhoto = dataAnimalPhotoRepository.findById(id).get();
        if(dataAnimalPhoto!=null){
            return dataAnimalPhoto;
        }else {
            throw new ExceptionNotFoundAnimalPhoto();
        }
    }

    @Override
    public DataAnimalPhoto save(String idAnimal,String description, MultipartFile avatarFile) throws IOException {
        logger.info("Администратор сохраняет фото животного");
        try{
            DataAnimalPhoto dataAnimalPhoto = uploadPhoto(null,idAnimal,description,avatarFile);
            dataAnimalPhoto = dataAnimalPhotoRepository.save(dataAnimalPhoto);
            logger.info("Фото животного сохранено");
            return dataAnimalPhoto;
        }catch (ExceptionServerError | IOException writeException){
            logger.info("При сохранении фото произошла ошибка");
            throw writeException ;
        }
    }

    @Override
    public DataAnimalPhoto update(String id, String idAnimal, String description, MultipartFile avatarFile) throws IOException {
        try{
            DataAnimalPhoto dataAnimalPhoto = uploadPhoto(id, idAnimal, description, avatarFile);
            dataAnimalPhoto = dataAnimalPhotoRepository.save(dataAnimalPhoto);
            return dataAnimalPhoto;
        }catch (ExceptionServerError | IOException writeException){
            throw writeException ;
        }
    }

    @Override
    public DataAnimalPhoto delete(String id) {
        DataAnimalPhoto dataAnimalPhoto = getAllAnimalPhotoById(id);
        if(dataAnimalPhoto!=null){
            try{
                dataAnimalPhotoRepository.deleteById(id);
                return dataAnimalPhoto;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }else{
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public List<DataAnimalPhoto> deleteByDataAnimal(String idAnimal) {
        List<DataAnimalPhoto> dataAnimalPhotos = getAllAnimalPhotoByIdAnimal(idAnimal);
        if(dataAnimalPhotos!=null){
            try{
                dataAnimalPhotoRepository.deleteById(idAnimal);
                return dataAnimalPhotos;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }else{
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    private DataAnimalPhoto uploadPhoto(String id, String idAnimal, String description, MultipartFile avatarFile) throws IOException {
        DataAnimalPhoto dataAnimalPhoto = new DataAnimalPhoto();
        dataAnimalPhoto.setDataAnimal(dataAnimalRepository.getById(idAnimal));
        dataAnimalPhoto.setId(id);
        dataAnimalPhoto.setDescription(description);
        dataAnimalPhoto.setContent(avatarFile.getBytes());
        dataAnimalPhoto.setMediaType(avatarFile.getContentType());
        return dataAnimalPhoto;
    }

}
