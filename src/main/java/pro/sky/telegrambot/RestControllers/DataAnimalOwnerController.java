package pro.sky.telegrambot.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.models.DataAnimalOwner;
import pro.sky.telegrambot.services.DataAnimalOwnerServices;

import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>DataAnimalOwnerController</b> - контроллер опекунов животных.<br/>
 */
@RestController
@RequestMapping("DataAnimalOwner")
public class DataAnimalOwnerController {
    private final DataAnimalOwnerServices animalOwnerServices;

    public DataAnimalOwnerController(DataAnimalOwnerServices animalOwnerServices) {
        this.animalOwnerServices = animalOwnerServices;
    }

    @Operation(
            summary = "Список опекунов"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список опекунов"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimalOwner"
    )
    @GetMapping
    public ResponseEntity<List<DataAnimalOwner>> getAll(){
        List<DataAnimalOwner> data = animalOwnerServices.findAll();
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Опекун по id животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Опекун по id животного получен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimalOwner"
    )
    @GetMapping(path = "/getDataAnimalOwnerByAnimalId/{animalId}")
    public ResponseEntity<DataAnimalOwner> getDataAnimalOwnerByAnimalId(@PathVariable String animalId){
        DataAnimalOwner data = animalOwnerServices.findByIdAnimal(animalId);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Список по chatId"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список по chatId получен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimalOwner"
    )
    @GetMapping(path = "/getDataAnimalOwnerByChatId/{chatlId}")
    public ResponseEntity<List<DataAnimalOwner>> getDataAnimalOwnerByChatId(@PathVariable long chatlId){
        List<DataAnimalOwner> data = animalOwnerServices.findByChatId(chatlId);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Опекун по его id"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Опекун по его id получен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimalOwner"
    )
    @GetMapping(path = "/getDataAnimalOwnerByOwnerId/{ownerId}")
    public ResponseEntity<DataAnimalOwner> getDataAnimalOwnerByOwnerId(@PathVariable String ownerId){
        DataAnimalOwner data = animalOwnerServices.findByIdOwner(ownerId);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Добавление нового опекуна"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Опекун добавлен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimalOwner"
    )
    @PostMapping(path = "/add")
    public ResponseEntity<DataAnimalOwner> add(@RequestBody DataAnimalOwner animalOwner){
        DataAnimalOwner data = animalOwnerServices.save(animalOwner);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Редактирование опекуна"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Опекун изменен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimalOwner"
    )
    @PutMapping(path = "/edit")
    public ResponseEntity<DataAnimalOwner> edit(@RequestBody DataAnimalOwner animalOwner){
        DataAnimalOwner data = animalOwnerServices.update(animalOwner);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление опекуна по его id"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Опекун удален"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimalOwner"
    )
    @DeleteMapping(path = "/deleteByIdOwner/{idOwner}")
    public ResponseEntity<DataAnimalOwner> deleteByIdOwner(@PathVariable String idOwner){
        DataAnimalOwner data = animalOwnerServices.deleteByIdOwner(idOwner);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление опекуна по id животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Опекун по id животного удален"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimalOwner"
    )
    @DeleteMapping(path = "/deleteByIdAnimal/{idAnimal}")
    public ResponseEntity<DataAnimalOwner> deleteByIdAnimal(@PathVariable String idAnimal){
        DataAnimalOwner data = animalOwnerServices.deleteByIdAnimal(idAnimal);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление по chatId"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список удален"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "DataAnimalOwner"
    )
    @DeleteMapping(path = "/deleteAllStandartResponseByRelationId/{chatId}")
    public ResponseEntity<List<DataAnimalOwner>> deleteAllStandartResponseByRelationId(@PathVariable long chatId){
        List<DataAnimalOwner> data = animalOwnerServices.deleteAllByChatId(chatId);
        return ResponseEntity.ok(data);
    }
}
