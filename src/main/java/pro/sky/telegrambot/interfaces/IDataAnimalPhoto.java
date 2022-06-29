package pro.sky.telegrambot.interfaces;

import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.models.DataAnimalPhoto;

import java.io.IOException;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * Интерфейс <b>IDataAnimalPhoto</b> - декларирует методы работы с хранилищем фотографий животных.<br/>
 */
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
