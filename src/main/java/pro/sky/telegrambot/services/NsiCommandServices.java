package pro.sky.telegrambot.services;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.interfaces.INsiCommandServices;
import pro.sky.telegrambot.models.NsiCommands;
import pro.sky.telegrambot.repositories.INsiCommandsRepository;

import java.util.List;

@Service
public class NsiCommandServices implements INsiCommandServices {

    private final INsiCommandsRepository nsiCommandsRepository;

    public NsiCommandServices(INsiCommandsRepository nsiCommandsRepository) {
        this.nsiCommandsRepository = nsiCommandsRepository;
    }

    @Override
    public List<NsiCommands> getAllCommands() {
        return nsiCommandsRepository.findAll();
    }

    @Override
    public NsiCommands getCommandsById(String id) {
        return nsiCommandsRepository.getById(id);
    }

    public List<NsiCommands> getCommandByCommand(String command) {
        return nsiCommandsRepository.findByCommand(command);
    }

    @Override
    public NsiCommands save(NsiCommands command) {
        return nsiCommandsRepository.save(command);
    }


}
