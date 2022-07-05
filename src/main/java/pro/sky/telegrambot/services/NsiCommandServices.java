package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.interfaces.INsiCommandServices;
import pro.sky.telegrambot.models.NsiCommands;
import pro.sky.telegrambot.repositories.INsiCommandsRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>NsiCommandServices</b> - сервис для работы с командами.<br/>
 */
@Service
public class NsiCommandServices implements INsiCommandServices {

    Logger logger = LoggerFactory.getLogger(NsiCommandServices.class);

    private final INsiCommandsRepository nsiCommandsRepository;

    public NsiCommandServices(INsiCommandsRepository nsiCommandsRepository) {
        this.nsiCommandsRepository = nsiCommandsRepository;
    }

    @Override
    public List<NsiCommands> getAllCommands() {
        logger.info("Get all commands");
        return nsiCommandsRepository.findAll();
    }

    @Override
    public NsiCommands getCommandsById(String id) {
        logger.info("Get command by id");
        return nsiCommandsRepository.findById(id).get();
    }

    public List<NsiCommands> getCommandByCommand(String command) {
        logger.info("Get command by command");
        return nsiCommandsRepository.findByCommand(command);
    }

    @Override
    public List<NsiCommands> findByLevel(int level) {
        logger.info("Find commands by level");
        return nsiCommandsRepository.findByLevel(level);
    }

    @Override
    public NsiCommands save(NsiCommands command) {
        logger.info("Save new command");
        return nsiCommandsRepository.save(command);
    }

    @Override
    public NsiCommands update(NsiCommands command) {
        logger.info("Update command");
        return save(command);
    }

    @Override
    public NsiCommands delete(String id) {
        NsiCommands command  = getCommandsById(id);
        if(command!=null){
            logger.info("Delte command by id");
            nsiCommandsRepository.deleteById(id);
            return command;
        }
        logger.warn("Don`t find command to delete by id");
        return null;
    }

    public List<String> getCommands(List<NsiCommands> eList) {
        logger.info("Get all commands name and id");
        List<String> list = new ArrayList<>();
        for(NsiCommands item : eList){
            list.add("----------------------" + "\n");
            list.add("/" + item.getId() + "\n");
            list.add(item.getCommand() + "\n");
            list.add("----------------------" + "\n");
        }
        return list;
    }


}
