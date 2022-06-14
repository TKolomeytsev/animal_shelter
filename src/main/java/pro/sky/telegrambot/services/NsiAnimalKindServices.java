package pro.sky.telegrambot.services;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalKind;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.INsiAnimalKind;
import pro.sky.telegrambot.models.NsiAnimalKind;
import pro.sky.telegrambot.repositories.INsiAnimalKindRepositiry;

import java.util.List;

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
        NsiAnimalKind nsiAnimalKind = nsiAnimalKindRepositiry.findById(id).get();
        if(nsiAnimalKind!=null){
            return nsiAnimalKind;
        }else {
            throw new ExceptionNotFoundAnimalKind();
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
}
