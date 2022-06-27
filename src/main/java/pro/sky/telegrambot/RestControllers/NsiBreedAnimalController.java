package pro.sky.telegrambot.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.models.NsiBreedAnimal;
import pro.sky.telegrambot.services.NsiBreedAnimalServices;

import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>NsiBreedAnimalController</b> - контроллер пород животных.<br/>
 */
@RestController
@RequestMapping("nsiBreedAnimal")
public class NsiBreedAnimalController {
    private final NsiBreedAnimalServices nsiBreedAnimalServices;

    public NsiBreedAnimalController(NsiBreedAnimalServices nsiBreedAnimalServices) {
        this.nsiBreedAnimalServices = nsiBreedAnimalServices;
    }

    @Operation(
            summary = "Список пород живодных"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список пород живодных"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "BreedAnimal"
    )
    @GetMapping
    public ResponseEntity<List<NsiBreedAnimal>> getAllKinds(){
        List<NsiBreedAnimal> data = nsiBreedAnimalServices.getAllBreedAnimal();
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Добавление новой породы животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Новая порода животного добавлен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "BreedAnimal"
    )
    @PostMapping(path = "/add")
    public ResponseEntity<NsiBreedAnimal> add(@RequestBody NsiBreedAnimal nsiBreedAnimal){
        NsiBreedAnimal data = nsiBreedAnimalServices.save(nsiBreedAnimal);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Редактирование породы животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Порода животного изменена"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "BreedAnimal"
    )
    @PutMapping(path = "/edit")
    public ResponseEntity<NsiBreedAnimal> edit(@RequestBody NsiBreedAnimal nsiBreedAnimal){
        NsiBreedAnimal data = nsiBreedAnimalServices.update(nsiBreedAnimal);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление породы животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Порода животного удалена"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "BreedAnimal"
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<NsiBreedAnimal> delete(@PathVariable String id){
        NsiBreedAnimal data = nsiBreedAnimalServices.delete(id);
        return ResponseEntity.ok(data);
    }
}
