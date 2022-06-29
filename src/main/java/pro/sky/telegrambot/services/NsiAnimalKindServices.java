package pro.sky.telegrambot.services;

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
    private final INsiAnimalKindRepositiry nsiAnimalKindRepositiry;

    public NsiAnimalKindServices(INsiAnimalKindRepositiry nsiAnimalKindRepositiry) {
        this.nsiAnimalKindRepositiry = nsiAnimalKindRepositiry;
    }

    @Override
    public List<NsiAnimalKind> getAllAnimalKind() {
        List<NsiAnimalKind> nsiAnimalKinds = nsiAnimalKindRepositiry.findAll();
        if(nsiAnimalKinds!=null){
            return nsiAnimalKinds;
        }else {
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public NsiAnimalKind getAllAnimalKindById(String id) {
        Optional<NsiAnimalKind> nsiAnimalKind = nsiAnimalKindRepositiry.findById(id);
        if(nsiAnimalKind.isPresent()){
            return nsiAnimalKind.get();
        }else {
            return null;
        }
    }

    @Override
    public List<NsiAnimalKind> getAllAnimalKindByName(String name) {
        List<NsiAnimalKind> nsiAnimalKinds = nsiAnimalKindRepositiry.findByName(name);
        if(nsiAnimalKinds!=null){
            return nsiAnimalKinds;
        }else {
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public NsiAnimalKind save(NsiAnimalKind kind) {
        try{
            return nsiAnimalKindRepositiry.save(kind);
        }catch (ExceptionServerError exceptionServerError){
            throw exceptionServerError;
        }
    }

    @Override
    public NsiAnimalKind update(NsiAnimalKind kind) {
        try{
            return nsiAnimalKindRepositiry.save(kind);
        }catch (ExceptionServerError exceptionServerError){
            throw exceptionServerError;
        }
    }

    @Override
    public NsiAnimalKind delete(String id) {
        NsiAnimalKind nsiAnimalKind = getAllAnimalKindById(id);
        if(nsiAnimalKind!=null){
            try{
                nsiAnimalKindRepositiry.deleteById(id);
                return nsiAnimalKind;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }else{
            throw new ExceptionNotFoundAnimalKind();
        }
    }


    public List<String> getCommands(List<NsiAnimalKind> eList) {
        List<String> list = new ArrayList<>();
        for(NsiAnimalKind item : eList){
            list.add("/" + item.getId()+"\n");
            list.add(item.getName()+"\n");
            list.add("\n");
        }
        return list;
    }
}
