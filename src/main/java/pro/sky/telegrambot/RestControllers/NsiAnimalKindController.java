package pro.sky.telegrambot.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.models.NsiAnimalKind;
import pro.sky.telegrambot.services.NsiAnimalKindServices;

import java.util.List;

@RestController
@RequestMapping("nsiAnimalKind")
public class NsiAnimalKindController {
    private final NsiAnimalKindServices nsiAnimalKindServices;

    public NsiAnimalKindController(NsiAnimalKindServices nsiAnimalKindServices) {
        this.nsiAnimalKindServices = nsiAnimalKindServices;
    }

    @Operation(
            summary = "Список видов живодных"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список видов живодных"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalKind"
    )
    @GetMapping
    public ResponseEntity<List<NsiAnimalKind>> getAllKinds(){
        List<NsiAnimalKind> data = nsiAnimalKindServices.getAllAnimalKind();
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Добавление нового вида животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Новый вид животного добавлен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalKind"
    )
    @PostMapping(path = "/add")
    public ResponseEntity<NsiAnimalKind> add(@RequestBody NsiAnimalKind nsiAnimalKind){
        NsiAnimalKind data = nsiAnimalKindServices.save(nsiAnimalKind);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Редактирование вида животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Вид животного изменен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalKind"
    )
    @PutMapping(path = "/edit")
    public ResponseEntity<NsiAnimalKind> edit(@RequestBody NsiAnimalKind nsiAnimalKind){
        NsiAnimalKind data = nsiAnimalKindServices.update(nsiAnimalKind);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление вида животного"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Вид животного удален"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "AnimalKind"
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<NsiAnimalKind> delete(@PathVariable String id){
        NsiAnimalKind data = nsiAnimalKindServices.delete(id);
        return ResponseEntity.ok(data);
    }
}
