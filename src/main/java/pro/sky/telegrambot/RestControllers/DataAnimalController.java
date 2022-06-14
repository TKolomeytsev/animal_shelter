package pro.sky.telegrambot.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.models.DataAnimal;
import pro.sky.telegrambot.models.NsiAnimalKind;
import pro.sky.telegrambot.services.DataAnimalServices;

import java.util.List;

@RestController
@RequestMapping("DataAnimal")
public class DataAnimalController {
    private final DataAnimalServices animalServices;

    public DataAnimalController(DataAnimalServices animalServices) {
        this.animalServices = animalServices;
    }

    @Operation(
            summary = "Список животных"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список животных"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimal"
    )
    @GetMapping
    public ResponseEntity<List<DataAnimal>> getAllAnimals(){
        List<DataAnimal> data = animalServices.getAllAnimal();
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Добавление животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Новое животное добавлено"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimal"
    )
    @PostMapping(path = "/add")
    public ResponseEntity<DataAnimal> add(@RequestBody DataAnimal dataAnimal){
        DataAnimal data = animalServices.save(dataAnimal);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Редактирование животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Животное изменено"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimal"
    )
    @PutMapping(path = "/edit")
    public ResponseEntity<DataAnimal> edit(@RequestBody DataAnimal dataAnimal){
        DataAnimal data = animalServices.update(dataAnimal);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Животное удалено"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimal"
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<DataAnimal> delete(@PathVariable String id){
        DataAnimal data = animalServices.delete(id);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление всех животных одного вида"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Животные удалены"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimal"
    )
    @DeleteMapping(path = "/deleteAnimalKind/{idKind}")
    public ResponseEntity<List<DataAnimal>> deleteAnimalsKind(@PathVariable String idKind){
        List<DataAnimal> data = animalServices.deleteAllDataAnimalByIdKind(idKind);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление всех животных одной породы"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Животные удалены"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimal"
    )
    @DeleteMapping(path = "/deleteAnimalBreed/{idBreed}")
    public ResponseEntity<List<DataAnimal>> deleteAnimalsBreed(@PathVariable String idBreed){
        List<DataAnimal> data = animalServices.deleteAllDataAnimalByIdBreed(idBreed);
        return ResponseEntity.ok(data);
    }
}
