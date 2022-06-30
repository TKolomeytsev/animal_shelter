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

    Logger logger = LoggerFactory.getLogger(DataAnimalPhotoService.class);

    private final IDataAnimalPhotoRepository dataAnimalPhotoRepository;
    private final IDataAnimalRepository dataAnimalRepository;

    public DataAnimalPhotoService(IDataAnimalPhotoRepository dataAnimalPhotoRepository, IDataAnimalRepository dataAnimalRepository) {
        this.dataAnimalPhotoRepository = dataAnimalPhotoRepository;
        this.dataAnimalRepository = dataAnimalRepository;
    }

    @Override
    public List<DataAnimalPhoto> getAllAnimalPhoto() {
        List<DataAnimalPhoto> dataAnimalPhotos = dataAnimalPhotoRepository.findAll();
        if(dataAnimalPhotos!=null){
            logger.info("Return all animal photo");
            return dataAnimalPhotos;
        }else {
            logger.warn("Не найдены фотографии животных");
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public List<DataAnimalPhoto> getAllAnimalPhotoByIdAnimal(String idAnimal) {
        List<DataAnimalPhoto> dataAnimalPhotos = dataAnimalPhotoRepository.findByDataAnimal(idAnimal);
        if(dataAnimalPhotos.size()>0){
            logger.info("Фотографии всех животных по ID");
            return dataAnimalPhotos;
        }else {
            logger.warn("Не найдены фотографии всех животных по ID");
            throw new ExceptionNotFoundAnimalPhoto();
        }
    }

    @Override
    public List<byte[]> getPhotoByIdAnimal(String idAnimal) {
        List<byte[]> photos = dataAnimalPhotoRepository.getPhotoByIdAnimal(idAnimal);
        if(photos.size()>0){
            logger.info("Вывод фотографии по id животного");
            return photos;
        }else {
            logger.warn("Ошибка в выводе фотографии по id животного ");
            throw new ExceptionNotFoundAnimalPhoto();
        }
    }

    @Override
    public DataAnimalPhoto getAllAnimalPhotoById(String id) {
        DataAnimalPhoto dataAnimalPhoto = dataAnimalPhotoRepository.findById(id).get();
        if(dataAnimalPhoto!=null){
            logger.info("Фотографии всех животных по id");
            return dataAnimalPhoto;
        }else {
            logger.warn("Ошибка в выводе фотографий животных по id");
            throw new ExceptionNotFoundAnimalPhoto();
        }
    }

    @Override
    public DataAnimalPhoto save(String idAnimal,String description, MultipartFile avatarFile) throws IOException {
        try{
            logger.info("Сохранение фотографии нового животного");
            DataAnimalPhoto dataAnimalPhoto = uploadPhoto(null,idAnimal,description,avatarFile);
            dataAnimalPhoto = dataAnimalPhotoRepository.save(dataAnimalPhoto);
            return dataAnimalPhoto;
        }catch (ExceptionServerError | IOException writeException){
            logger.warn("Ошибка сохранении(чтении) фотографии");
            throw writeException;
        }
    }

    @Override
    public DataAnimalPhoto update(String id, String idAnimal, String description, MultipartFile avatarFile) throws IOException {
        try{
            logger.info("Редактирование фотографии животного");
            DataAnimalPhoto dataAnimalPhoto = uploadPhoto(id, idAnimal, description, avatarFile);
            dataAnimalPhoto = dataAnimalPhotoRepository.save(dataAnimalPhoto);
            return dataAnimalPhoto;
        }catch (ExceptionServerError | IOException writeException){
            logger.warn("Ошибка редактирования(чтения) фотографии");
            throw writeException;
        }
    }

    @Override
    public DataAnimalPhoto delete(String id) {
        DataAnimalPhoto dataAnimalPhoto = getAllAnimalPhotoById(id);
        if(dataAnimalPhoto!=null){
            try{
                logger.info("Delete photo by ID");
                dataAnimalPhotoRepository.deleteById(id);
                return dataAnimalPhoto;
            }catch (ExceptionServerError exceptionServerError){
                logger.warn("Delete server Error");
                throw exceptionServerError;
            }
        }else{
            logger.warn("Don`t find photo");
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public List<DataAnimalPhoto> deleteByDataAnimal(String idAnimal) {
        List<DataAnimalPhoto> dataAnimalPhotos = getAllAnimalPhotoByIdAnimal(idAnimal);
        if(dataAnimalPhotos!=null){
            try{
                logger.info("Delete all photos");
                dataAnimalPhotoRepository.deleteById(idAnimal);
                return dataAnimalPhotos;
            }catch (ExceptionServerError exceptionServerError){
                logger.warn("Delete all photos server error");
                throw exceptionServerError;
            }
        }else{
            logger.warn("Don`t find ALL photos");
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    private DataAnimalPhoto uploadPhoto(String id, String idAnimal, String description, MultipartFile avatarFile) throws IOException {
        logger.info("Upload new Photo");
        DataAnimalPhoto dataAnimalPhoto = new DataAnimalPhoto();
        dataAnimalPhoto.setDataAnimal(dataAnimalRepository.getById(idAnimal));
        dataAnimalPhoto.setId(id);
        dataAnimalPhoto.setDescription(description);
        dataAnimalPhoto.setContent(avatarFile.getBytes());
        dataAnimalPhoto.setMediaType(avatarFile.getContentType());
        return dataAnimalPhoto;
    }

}
