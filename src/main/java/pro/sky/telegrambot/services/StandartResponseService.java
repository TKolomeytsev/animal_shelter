package pro.sky.telegrambot.services;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.IStandartResponse;
import pro.sky.telegrambot.models.StandartResponse;
import pro.sky.telegrambot.repositories.IStandartResponseRepositiry;

import java.util.ArrayList;
import java.util.List;
@Service
public class StandartResponseService implements IStandartResponse {
    private final IStandartResponseRepositiry standartResponseRepositiry;
    private  final String  ID = "id";

    public StandartResponseService(IStandartResponseRepositiry standartResponseRepositiry) {
        this.standartResponseRepositiry = standartResponseRepositiry;
    }

    @Override
    public List<StandartResponse> getAllStandartResponse() {
        List<StandartResponse> list = standartResponseRepositiry.findAll();
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    @Override
    public StandartResponse getStandartResponseByResId(String resId) {
        return standartResponseRepositiry.findByResId(resId);
    }

    @Override
    public List<StandartResponse> getAllStandartResponseByRelationId(String relationId) {
        List<StandartResponse> list = standartResponseRepositiry.findByRelationId(relationId);
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    @Override
    public StandartResponse save(StandartResponse response) {
        return standartResponseRepositiry.save(response);
    }

    @Override
    public StandartResponse update(StandartResponse response) {
        return standartResponseRepositiry.save(response);
    }

    @Override
    public StandartResponse delete(String resId) {
        StandartResponse response = getStandartResponseByResId(resId);
        if(response!=null){
            try{
                standartResponseRepositiry.deleteByResId(resId);
                return response;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }
        return null;
    }

    @Override
    public List<StandartResponse> deleteAllStandartResponseByRelationId(String relationId) {
        List<StandartResponse> response = getAllStandartResponseByRelationId(relationId);
        if(response.size() > 0){
            return response;
        }
        return null;
    }

    public List<String> getCommands(List<StandartResponse> eList) {
        List<String> list = new ArrayList<>();
        for(StandartResponse item : eList){
            list.add("----------------------" + "\n");
            list.add("/" + returnCommand(item) + "\n");
            list.add(item.getResponseName() + "\n");
            list.add("----------------------" + "\n");
        }
        return list;
    }

    private String returnCommand(StandartResponse item) {
        if(item.getResponseText().substring(0,2).equals(ID)){
            return item.getResponseText().substring(5);
        }
        return item.getResId();
    }

    public List<String> getStandartResponseInfo(StandartResponse eList) {
        List<String> list = new ArrayList<>();
        list.add("----------------" + "\n");
        list.add(eList.getResponseText()+"\n");
        list.add("----------------"+"\n");
        return list;
    }
}
