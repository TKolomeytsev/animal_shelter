package pro.sky.telegrambot.RestControllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.telegrambot.models.NsiCommands;
import pro.sky.telegrambot.services.NsiCommandServices;

@RestController
@RequestMapping("nsiCommands")
public class NsiCommandsController {
    private final NsiCommandServices commandServices;

    public NsiCommandsController(NsiCommandServices commandServices) {
        this.commandServices = commandServices;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<NsiCommands> add(@RequestBody NsiCommands command){
        NsiCommands data = commandServices.save(command);
        return ResponseEntity.ok(data);
    }
}
