package pro.sky.telegrambot.RestControllers;

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

    @GetMapping
    public ResponseEntity<List<NsiCommands>> getAll(){
        List<NsiCommands> data = commandServices.getAllCommands();
        return ResponseEntity.ok(data);
    }

    @PostMapping(path = "/add")
    public ResponseEntity<NsiCommands> add(@RequestBody NsiCommands command){
        NsiCommands data = commandServices.save(command);
        return ResponseEntity.ok(data);
    }

    @PutMapping(path = "/edit")
    public ResponseEntity<NsiCommands> edit(@RequestBody NsiCommands command){
        NsiCommands data = commandServices.update(command);
        return ResponseEntity.ok(data);
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<NsiCommands> delete(@PathVariable String id){
        NsiCommands data = commandServices.delete(id);
        return ResponseEntity.ok(data);
    }
}
