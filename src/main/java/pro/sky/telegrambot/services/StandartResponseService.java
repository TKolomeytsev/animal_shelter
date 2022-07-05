package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.enams.ServiceEnams;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.IStandartResponse;
import pro.sky.telegrambot.models.StandartResponse;
import pro.sky.telegrambot.repositories.IStandartResponseRepositiry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>StandartResponseService</b> - сервис для работы с ответами.<br/>
 */
@Service
public class StandartResponseService implements IStandartResponse {

    Logger logger = LoggerFactory.getLogger(StandartResponseService.class);

    private final IStandartResponseRepositiry standartResponseRepositiry;

    public StandartResponseService(IStandartResponseRepositiry standartResponseRepositiry) {
        this.standartResponseRepositiry = standartResponseRepositiry;
    }

    @Override
    public List<StandartResponse> getAllStandartResponse() {
        List<StandartResponse> list = standartResponseRepositiry.findAll();
        if(list.size() > 0){
            logger.info("Get all standard reports");
            return list;
        }
        logger.warn("Don`t find standard reports");
        return null;
    }

    @Override
    public StandartResponse getStandartResponseByResId(String resId) {
        logger.info("Get standard response by id");
        return standartResponseRepositiry.findByResId(resId);
    }

    @Override
    public List<StandartResponse> getAllStandartResponseByRelationId(String relationId) {
        List<StandartResponse> list = standartResponseRepositiry.findByRelationId(relationId);
        if(list.size() > 0){
            logger.info("Get all standard response by relationId");
            return list;
        }
        logger.warn("Don`t find standard response by relationId");
        return null;
    }

    @Override
    public StandartResponse save(StandartResponse response) {
        logger.info("Save new standard response");
        return standartResponseRepositiry.save(response);
    }

    @Override
    public StandartResponse update(StandartResponse response) {
        logger.info("Update standard response");
        return standartResponseRepositiry.save(response);
    }

    @Override
    public StandartResponse delete(String resId) {
        StandartResponse response = getStandartResponseByResId(resId);
        if(response!=null){
            try{
                logger.info("Delete standard response by res id");
                standartResponseRepositiry.deleteByResId(resId);
                return response;
            }catch (ExceptionServerError exceptionServerError){
                logger.error("Server delete response by res id error");
                throw exceptionServerError;
            }
        }
        logger.warn("Standart response is already null");
        return null;
    }

    @Override
    public List<StandartResponse> deleteAllStandartResponseByRelationId(String relationId) {
        List<StandartResponse> response = getAllStandartResponseByRelationId(relationId);
        if(response.size() > 0){
            logger.info("Delete all standard response by relation id");
            return response;
        }
        logger.warn("List of standard response is already null");
        return null;
    }

    public List<String> getCommands(List<StandartResponse> eList) {
        logger.info("Get all standard response name and command");
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
        if(item.getResponseText().substring(0,2).equals(ServiceEnams.LINK.getValue())){
            logger.info("Get Command");
            return item.getResponseText().substring(5);
        }
        logger.warn("Don`t command");
        return item.getResId();
    }

    public List<String> getStandartResponseInfo(StandartResponse eList) {
        logger.info("Get all standard response indo");
        List<String> list = new ArrayList<>();
        list.add("----------------" + "\n");
        list.add(eList.getResponseText()+"\n");
        list.add("----------------"+"\n");
        return list;
    }
}
