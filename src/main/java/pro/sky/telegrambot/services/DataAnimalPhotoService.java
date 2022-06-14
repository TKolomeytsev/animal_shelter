package pro.sky.telegrambot.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalKind;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalPhoto;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.IDataAnimalPhoto;
import pro.sky.telegrambot.models.DataAnimalPhoto;
import pro.sky.telegrambot.models.DataAnimalPhotoInputOutput;
import pro.sky.telegrambot.repositories.IDataAnimalPhotoRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class DataAnimalPhotoService implements IDataAnimalPhoto {
    private final IDataAnimalPhotoRepository dataAnimalPhotoRepository;


    public DataAnimalPhotoService(IDataAnimalPhotoRepository dataAnimalPhotoRepository) {
        this.dataAnimalPhotoRepository = dataAnimalPhotoRepository;
    }

    @Override
    public List<DataAnimalPhotoInputOutput> getAllAnimalPhoto() {
        List<DataAnimalPhoto> dataAnimalPhotos = dataAnimalPhotoRepository.findAll();
        if(dataAnimalPhotos!=null){
            List<DataAnimalPhotoInputOutput> dataAnimalPhotoInputOutputs = new ArrayList<DataAnimalPhotoInputOutput>();
            for(DataAnimalPhoto dataAnimalPhoto : dataAnimalPhotos){
                DataAnimalPhotoInputOutput dataAnimalPhotoInputOutput = new DataAnimalPhotoInputOutput();
                dataAnimalPhotoInputOutput.setIdPhoto(dataAnimalPhoto.getId());
                dataAnimalPhotoInputOutput.setDescription(dataAnimalPhoto.getDescription());
                dataAnimalPhotoInputOutput.setContent(dataAnimalPhoto.getContent());
                dataAnimalPhotoInputOutputs.add(dataAnimalPhotoInputOutput);
            }
            return dataAnimalPhotoInputOutputs;
        }else {
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public List<DataAnimalPhotoInputOutput> getAllAnimalPhotoByIdAnimal(String idAnimal) {
        List<DataAnimalPhoto> dataAnimalPhotos = dataAnimalPhotoRepository.findByDataAnimal(idAnimal);
        if(dataAnimalPhotos!=null){
            List<DataAnimalPhotoInputOutput> dataAnimalPhotoInputOutputs = new ArrayList<DataAnimalPhotoInputOutput>();
            for(DataAnimalPhoto dataAnimalPhoto : dataAnimalPhotos){
                DataAnimalPhotoInputOutput dataAnimalPhotoInputOutput = new DataAnimalPhotoInputOutput();
                dataAnimalPhotoInputOutput.setIdPhoto(dataAnimalPhoto.getId());
                dataAnimalPhotoInputOutput.setDescription(dataAnimalPhoto.getDescription());
                dataAnimalPhotoInputOutput.setContent(dataAnimalPhoto.getContent());
                dataAnimalPhotoInputOutputs.add(dataAnimalPhotoInputOutput);
            }
            return dataAnimalPhotoInputOutputs;
        }else {
            throw new ExceptionNotFoundAnimalPhoto();
        }
    }

    @Override
    public DataAnimalPhotoInputOutput getAllAnimalPhotoById(String id) {
        DataAnimalPhoto dataAnimalPhoto = dataAnimalPhotoRepository.findById(id).get();
        if(dataAnimalPhoto!=null){
            DataAnimalPhotoInputOutput dataAnimalPhotoInputOutput = new DataAnimalPhotoInputOutput();
            dataAnimalPhotoInputOutput.setIdPhoto(dataAnimalPhoto.getId());
            dataAnimalPhotoInputOutput.setDescription(dataAnimalPhoto.getDescription());
            dataAnimalPhotoInputOutput.setContent(dataAnimalPhoto.getContent());
            return dataAnimalPhotoInputOutput;
        }else {
            throw new ExceptionNotFoundAnimalPhoto();
        }
    }

    @Override
    public DataAnimalPhotoInputOutput save(DataAnimalPhotoInputOutput dataAnimalPhotoInputOutput, MultipartFile avatarFile) throws IOException {
        try{
            DataAnimalPhotoInputOutput dataAnimalPhoto = uploadPhoto(dataAnimalPhotoInputOutput,avatarFile);
            String id = UUID.randomUUID().toString();
            dataAnimalPhotoRepository.save(id,dataAnimalPhoto.getIdAnimal(),dataAnimalPhoto.getDescription(),dataAnimalPhoto.getContent());
            return dataAnimalPhoto;
        }catch (ExceptionServerError | IOException writeException){
            throw writeException ;
        }
    }

    @Override
    public DataAnimalPhotoInputOutput update(DataAnimalPhotoInputOutput dataAnimalPhotoInputOutput, MultipartFile avatarFile) throws IOException {
        try{
            DataAnimalPhotoInputOutput dataAnimalPhoto = uploadPhoto(dataAnimalPhotoInputOutput,avatarFile);
            String id = UUID.randomUUID().toString();
            dataAnimalPhotoRepository.save(id,dataAnimalPhoto.getIdAnimal(),dataAnimalPhoto.getDescription(),dataAnimalPhoto.getContent());
            return dataAnimalPhoto;
        }catch (ExceptionServerError | IOException writeException){
            throw writeException ;
        }
    }

    @Override
    public DataAnimalPhotoInputOutput delete(String id) {
        DataAnimalPhotoInputOutput dataAnimalPhoto = getAllAnimalPhotoById(id);
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
    public List<DataAnimalPhotoInputOutput> deleteByDataAnimal(String idAnimal) {
        List<DataAnimalPhotoInputOutput> dataAnimalPhotos = getAllAnimalPhotoByIdAnimal(idAnimal);
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

    private DataAnimalPhotoInputOutput uploadPhoto(DataAnimalPhotoInputOutput dataAnimalPhotoInputOutput, MultipartFile avatarFile) throws IOException {
        dataAnimalPhotoInputOutput.setContent(avatarFile.getBytes());
        return dataAnimalPhotoInputOutput;
    }

}
