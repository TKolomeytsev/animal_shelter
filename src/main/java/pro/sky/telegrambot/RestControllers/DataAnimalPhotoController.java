package pro.sky.telegrambot.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.telegrambot.models.DataAnimalPhotoInputOutput;
import pro.sky.telegrambot.services.DataAnimalPhotoService;

import java.io.IOException;
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
    public ResponseEntity<List<DataAnimalPhotoInputOutput>> getAllAnimalPhotoByIdAnimal(@PathVariable String idAnimal){
        List<DataAnimalPhotoInputOutput> data = dataAnimalPhotoService.getAllAnimalPhotoByIdAnimal(idAnimal);
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
    public ResponseEntity<List<DataAnimalPhotoInputOutput>> getAllAnimalPhotos(){
        List<DataAnimalPhotoInputOutput> data = dataAnimalPhotoService.getAllAnimalPhoto();
        return ResponseEntity.ok(data);
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
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DataAnimalPhotoInputOutput> add(@RequestBody DataAnimalPhotoInputOutput dataAnimalPhoto, @RequestParam MultipartFile avatar) throws IOException {
        dataAnimalPhotoService.save(dataAnimalPhoto,avatar);
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
    public ResponseEntity<DataAnimalPhotoInputOutput> edit(@RequestBody DataAnimalPhotoInputOutput dataAnimalPhoto, @RequestParam MultipartFile avatar) throws IOException {
        dataAnimalPhotoService.update(dataAnimalPhoto,avatar);
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
    public ResponseEntity<DataAnimalPhotoInputOutput> delete(@PathVariable String id){
        DataAnimalPhotoInputOutput data = dataAnimalPhotoService.delete(id);
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
    public ResponseEntity<List<DataAnimalPhotoInputOutput>> deleteByAnimalId(@PathVariable String idAnimal){
        List<DataAnimalPhotoInputOutput> data = dataAnimalPhotoService.deleteByDataAnimal(idAnimal);
        return ResponseEntity.ok(data);
    }
}
