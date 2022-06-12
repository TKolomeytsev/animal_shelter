package pro.sky.telegrambot.interfaces;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.models.DataAnimalPhoto;

import java.io.IOException;
import java.util.List;

public interface IDataAnimalPhoto {
    List<DataAnimalPhoto>  getAllAnimalPhoto();

    List<DataAnimalPhoto> getAllAnimalPhotoByIdAnimal(String idAnimal);

    DataAnimalPhoto getAllAnimalPhotoById(String id);

    DataAnimalPhoto save(DataAnimalPhoto dataAnimalPhoto, MultipartFile avatarFile) throws IOException;

    DataAnimalPhoto update(DataAnimalPhoto dataAnimalPhoto, MultipartFile avatarFile) throws IOException;

    DataAnimalPhoto delete(String id);

    List<DataAnimalPhoto> deleteByDataAnimal(String idAnimal);
}
