package pro.sky.telegrambot.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.models.StandartResponse;
import pro.sky.telegrambot.services.StandartResponseService;

import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>StandartResponseController</b> - контроллер ответов.<br/>
 */
@RestController
@RequestMapping("standartResponse")
public class StandartResponseController {
    private final StandartResponseService standartResponseService;

    public StandartResponseController(StandartResponseService standartResponseService) {
        this.standartResponseService = standartResponseService;
    }

    @Operation(
            summary = "Список стандартных ответов"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список стандартных ответов"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "standartResponse"
    )
    @GetMapping
    public ResponseEntity<List<StandartResponse>> getAll(){
        List<StandartResponse> data = standartResponseService.getAllStandartResponse();
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Ответ по его id"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Ответ по его id получен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "standartResponse"
    )
    @GetMapping(path = "/getStandartResponseByResid/{resId}")
    public ResponseEntity<StandartResponse> getStandartResponseByResid(@PathVariable String resId){
        StandartResponse data = standartResponseService.getStandartResponseByResId(resId);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Список всех ответов по id связки"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Список всех ответов по id связки получен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "standartResponse"
    )
    @GetMapping(path = "/getStandartResponseByRelationId/{relationId}")
    public ResponseEntity<List<StandartResponse>> getStandartResponseByRelationId(@PathVariable String relationId){
        List<StandartResponse> data = standartResponseService.getAllStandartResponseByRelationId(relationId);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Добавление нового ответ"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Ответ добавлен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "standartResponse"
    )
    @PostMapping(path = "/add")
    public ResponseEntity<StandartResponse> add(@RequestBody StandartResponse response){
        StandartResponse data = standartResponseService.save(response);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Редактирование ответа"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Ответ изменен"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "standartResponse"
    )
    @PutMapping(path = "/edit")
    public ResponseEntity<StandartResponse> edit(@RequestBody StandartResponse response){
        StandartResponse data = standartResponseService.update(response);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление ответа"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Ответ удален"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "standartResponse"
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<StandartResponse> delete(@PathVariable String id){
        StandartResponse data = standartResponseService.delete(id);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление ответов по id связки"
            ,responses = {
            @ApiResponse(
                    responseCode = "200"
                    ,description = "Ответы удалены"
                    ,content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE
            )
            )
    }
            ,tags = "standartResponse"
    )
    @DeleteMapping(path = "/deleteAllStandartResponseByRelationId/{id}")
    public ResponseEntity<List<StandartResponse>> deleteAllStandartResponseByRelationId(@PathVariable String id){
        List<StandartResponse> data = standartResponseService.deleteAllStandartResponseByRelationId(id);
        return ResponseEntity.ok(data);
    }
}
