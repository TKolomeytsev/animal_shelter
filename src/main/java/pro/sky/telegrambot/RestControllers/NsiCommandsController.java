package pro.sky.telegrambot.RestControllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.telegrambot.models.NsiCommands;
import pro.sky.telegrambot.services.NsiCommandServices;

import java.util.List;

@RestController
@RequestMapping("nsiCommands")
public class NsiCommandsController {
    private final NsiCommandServices commandServices;

    public NsiCommandsController(NsiCommandServices commandServices) {
        this.commandServices = commandServices;
    }

    @Operation(
            summary = "Список команд"
            ,responses = {
                    @ApiResponse(
                            responseCode = "200"
                            ,description = "Список команд"
                            ,content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE
                            )
                    )
            }
            ,tags = "Commands"
    )
    @GetMapping
    public ResponseEntity<List<NsiCommands>> getAll(){
        List<NsiCommands> data = commandServices.getAllCommands();
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Добавление команды"
            ,responses = {
                @ApiResponse(
                        responseCode = "200"
                        ,description = "Команда добавлена"
                        ,content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                )
            }
            ,tags = "Commands"
    )
    @PostMapping(path = "/add")
    public ResponseEntity<NsiCommands> add(@RequestBody NsiCommands command){
        NsiCommands data = commandServices.save(command);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Редактирование команды"
            ,responses = {
                @ApiResponse(
                        responseCode = "200"
                        ,description = "Команда изменена"
                        ,content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                )
            }
            ,tags = "Commands"
    )
    @PutMapping(path = "/edit")
    public ResponseEntity<NsiCommands> edit(@RequestBody NsiCommands command){
        NsiCommands data = commandServices.update(command);
        return ResponseEntity.ok(data);
    }

    @Operation(
            summary = "Удаление команды"
            ,responses = {
                @ApiResponse(
                        responseCode = "200"
                        ,description = "Команда удалена"
                        ,content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE
                        )
                )
            }
            ,tags = "Commands"
    )
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<NsiCommands> delete(@PathVariable String id){
        NsiCommands data = commandServices.delete(id);
        return ResponseEntity.ok(data);
    }
}
