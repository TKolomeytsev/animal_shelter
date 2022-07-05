package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalKind;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.INsiAnimalKind;
import pro.sky.telegrambot.models.NsiAnimalKind;
import pro.sky.telegrambot.repositories.INsiAnimalKindRepositiry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author AKolomeytsev<br/>
 * <b>NsiAnimalKindServices</b> - сервис для работы с видами животных.<br/>
 */
@Service
public class NsiAnimalKindServices implements INsiAnimalKind {

    Logger logger = LoggerFactory.getLogger(NsiAnimalKindServices.class);

    private final INsiAnimalKindRepositiry nsiAnimalKindRepositiry;

    public NsiAnimalKindServices(INsiAnimalKindRepositiry nsiAnimalKindRepositiry) {
        this.nsiAnimalKindRepositiry = nsiAnimalKindRepositiry;
    }

    @Override
    public List<NsiAnimalKind> getAllAnimalKind() {
        List<NsiAnimalKind> nsiAnimalKinds = nsiAnimalKindRepositiry.findAll();
        if(nsiAnimalKinds!=null){
            logger.info("Get all animals kind");
            return nsiAnimalKinds;
        }else {
            logger.warn("Don` find any animal kind");
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public NsiAnimalKind getAllAnimalKindById(String id) {
        Optional<NsiAnimalKind> nsiAnimalKind = nsiAnimalKindRepositiry.findById(id);
        if(nsiAnimalKind.isPresent()){
            logger.info("Get all animal kind by id");
            return nsiAnimalKind.get();
        }else {
            logger.warn("Don`t find animal kind by id");
            return null;
        }
    }

    @Override
    public List<NsiAnimalKind> getAllAnimalKindByName(String name) {
        List<NsiAnimalKind> nsiAnimalKinds = nsiAnimalKindRepositiry.findByName(name);
        if(nsiAnimalKinds!=null){
            logger.info("Get all animal kind by name");
            return nsiAnimalKinds;
        }else {
            logger.warn("Don`t find animal kind by name");
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public NsiAnimalKind save(NsiAnimalKind kind) {
        try{
            logger.info("Save new animal kind");
            return nsiAnimalKindRepositiry.save(kind);
        }catch (ExceptionServerError exceptionServerError){
            logger.error("Server save animal kind error");
            throw exceptionServerError;
        }
    }

    @Override
    public NsiAnimalKind update(NsiAnimalKind kind) {
        try{
            logger.info("Update animal kind");
            return nsiAnimalKindRepositiry.save(kind);
        }catch (ExceptionServerError exceptionServerError){
            logger.error("Server update animal kind error");
            throw exceptionServerError;
        }
    }

    @Override
    public NsiAnimalKind delete(String id) {
        NsiAnimalKind nsiAnimalKind = getAllAnimalKindById(id);
        if(nsiAnimalKind!=null){
            try{
                logger.info("Delte animal kind by id");
                nsiAnimalKindRepositiry.deleteById(id);
                return nsiAnimalKind;
            }catch (ExceptionServerError exceptionServerError){
                logger.error("Server delete by id error");
                throw exceptionServerError;
            }
        }else{
            logger.warn("Don`t find to delete animal kind by id");
            throw new ExceptionNotFoundAnimalKind();
        }
    }


    public List<String> getCommands(List<NsiAnimalKind> eList) {
        logger.info("Get all animals kind name");
        List<String> list = new ArrayList<>();
        for(NsiAnimalKind item : eList){
            list.add("/" + item.getId()+"\n");
            list.add(item.getName()+"\n");
            list.add("\n");
        }
        return list;
    }
}
