package pro.sky.telegrambot.interfaces;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.models.DataAnimalPhoto;
import pro.sky.telegrambot.models.DataAnimalPhotoInputOutput;

import java.io.IOException;
import java.util.List;

public interface IDataAnimalPhoto {
    List<DataAnimalPhotoInputOutput> getAllAnimalPhoto();

    List<DataAnimalPhotoInputOutput> getAllAnimalPhotoByIdAnimal(String idAnimal);

    DataAnimalPhotoInputOutput getAllAnimalPhotoById(String id);

    DataAnimalPhotoInputOutput save(DataAnimalPhotoInputOutput dataAnimalPhotoInputOutput, MultipartFile avatarFile) throws IOException;

    DataAnimalPhotoInputOutput update(DataAnimalPhotoInputOutput dataAnimalPhotoInputOutput, MultipartFile avatarFile) throws IOException;

    DataAnimalPhotoInputOutput delete(String id);

    List<DataAnimalPhotoInputOutput> deleteByDataAnimal(String idAnimal);
}
