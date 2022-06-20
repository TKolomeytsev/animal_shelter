package pro.sky.telegrambot.interfaces;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.models.DataAnimalPhoto;
import pro.sky.telegrambot.models.DataAnimalPhotoInputOutput;

import java.io.IOException;
import java.util.List;

public interface IDataAnimalPhoto {
    List<DataAnimalPhoto> getAllAnimalPhoto();

    List<DataAnimalPhoto> getAllAnimalPhotoByIdAnimal(String idAnimal);
    List<byte[]> getPhotoByIdAnimal(String idAnimal);

    DataAnimalPhoto getAllAnimalPhotoById(String id);

    DataAnimalPhoto save(String idAnimal,String description, MultipartFile avatarFile) throws IOException;

    DataAnimalPhoto update(String id, String idAnimal, String description, MultipartFile avatarFile) throws IOException;

    DataAnimalPhoto delete(String id);

    List<DataAnimalPhoto> deleteByDataAnimal(String idAnimal);
}
