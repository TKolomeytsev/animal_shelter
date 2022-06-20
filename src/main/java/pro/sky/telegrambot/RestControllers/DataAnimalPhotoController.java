package pro.sky.telegrambot.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.models.DataAnimalPhoto;
import pro.sky.telegrambot.services.DataAnimalPhotoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("animalPhoto")
public class DataAnimalPhotoController {
    private final DataAnimalPhotoService dataAnimalPhotoService;

    public DataAnimalPhotoController(DataAnimalPhotoService dataAnimalPhotoService) {
        this.dataAnimalPhotoService = dataAnimalPhotoService;
    }

    @Operation(
            summary = "Список всех фотографий животного по id"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список всех фотографий животного"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalPhoto"
    )
    @GetMapping(path = "/getAnimalPhotosByIdAnimal/{id}")
    public ResponseEntity<List<DataAnimalPhoto>> getAllAnimalPhotoByIdAnimal(@PathVariable String idAnimal){
        List<DataAnimalPhoto> data = dataAnimalPhotoService.getAllAnimalPhotoByIdAnimal(idAnimal);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Список всех фотографий животных"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список всех фотографий животных"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalPhoto"
    )
    @GetMapping
    public List<ResponseEntity<byte[]>> getAllAnimalPhotos(){
        List<DataAnimalPhoto> data = dataAnimalPhotoService.getAllAnimalPhoto();
        List<ResponseEntity<byte[]>> entityList = new ArrayList<>();
        for(DataAnimalPhoto item : data) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(item.getMediaType()));
            headers.setContentLength(item.getContent().length);
            entityList.add(ResponseEntity.status(HttpStatus.OK).headers(headers).body(item.getContent()));
        }
        return entityList;
    }

    @Operation(
            summary = "Добавление фото животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Фото животного добавлено"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalPhoto"
    )
    @PostMapping(value = "{idAnimal}/{description}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataAnimalPhoto> add(@PathVariable String idAnimal, @PathVariable String description, @RequestParam MultipartFile avatar) throws IOException {
        dataAnimalPhotoService.save(idAnimal,description,avatar);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Измение фото животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Фото животного изменено"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalPhoto"
    )
    @PutMapping(value = "/edit", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataAnimalPhoto> edit(@PathVariable String id, @PathVariable String idAnimal, @PathVariable String description, @RequestParam MultipartFile avatar) throws IOException {
        dataAnimalPhotoService.update(id,idAnimal,description,avatar);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Удалить фото животного по идетификатору фотографии"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Фото животного удалено по идетификатору фотографии"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalPhoto"
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<DataAnimalPhoto> delete(@PathVariable String id){
        DataAnimalPhoto data = dataAnimalPhotoService.delete(id);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удалить фото животного по идетификатору животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Фото животного удалено по идетификатору животного"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalPhoto"
    )
    @DeleteMapping(path = "/deleteByAnimalId/{idAnimal}")
    public ResponseEntity<List<DataAnimalPhoto>> deleteByAnimalId(@PathVariable String idAnimal){
        List<DataAnimalPhoto> data = dataAnimalPhotoService.deleteByDataAnimal(idAnimal);
        return ResponseEntity.ok(data);
    }
}
