package pro.sky.telegrambot.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalKind;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.IDataReport;
import pro.sky.telegrambot.models.DataReport;
import pro.sky.telegrambot.repositories.IDataReportRepository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>DataReportService</b> - сервис для работы с отчетами.<br/>
 */
@Service
public class DataReportService implements IDataReport {

    Logger logger = LoggerFactory.getLogger(DataReportService.class);

    private final IDataReportRepository dataReportRepository;

    public DataReportService(IDataReportRepository dataReportRepository) {
        this.dataReportRepository = dataReportRepository;
    }

    @Override
    public List<DataReport> getAllDataReport() {
        List<DataReport> list = dataReportRepository.findAll();
        if(list.size() > 0){
            logger.info("Get all reports");
            return list;
        }
        logger.info("Don` reports");
        return null;
    }

    @Override
    public DataReport getDataReportByRepId(String repId) {
        logger.info("Get report by Id");
        return dataReportRepository.findAllByRepId(repId);
    }

    @Override
    public List<DataReport> getAllDataReportByChatId(long chatId) {
        List<DataReport> list = dataReportRepository.findAllByChatId(chatId);
        if(list.size() > 0){
            logger.info("Get all reports by chat id");
            return list;
        }
        logger.info("Don`t reports by chat id");
        return null;
    }

    @Override
    public DataReport save(DataReport report) {
        logger.info("Save new report");
        return dataReportRepository.save(report);
    }

    @Override
    public DataReport update(DataReport report) {
        logger.info("Update report");
        return dataReportRepository.save(report);
    }

    @Override
    public DataReport deleteAllByRepId(String repId) {
        DataReport report = getDataReportByRepId(repId);
        if(report!=null){
            try{
                logger.info("Delete all report by id");
                dataReportRepository.deleteAllByRepId(repId);
                return report;
            }catch (ExceptionServerError exceptionServerError){
                logger.error("Delete report by id error");
                throw exceptionServerError;
            }
        }
        logger.warn("Don` delete reports by id");
        return null;
    }

    @Override
    public List<DataReport> deleteAllByChatId(long chatId) {
        List<DataReport> reports = getAllDataReportByChatId(chatId);
        if(reports!=null){
            try{
                logger.info("Delete all reports by chat id");
                dataReportRepository.deleteAllByChatId(chatId);
                return reports;
            }catch (ExceptionServerError exceptionServerError){
                logger.error("Delete all reports by chat id error");
                throw exceptionServerError;
            }
        }else{
            logger.warn("Don`t find reports by chat id to delete");
            throw new ExceptionNotFoundAnimalKind();
        }
    }

    @Override
    public DataReport getDataReportByChatIdAndDateSend(long chatId, LocalDateTime dateSend) {
        DataReport report = dataReportRepository.getDataReportByChatIdAndDateSend(chatId,dateSend);
        if(report!=null){
            logger.info("Get report by chat id and date send");
            return report;
        }
        logger.warn("Don` get report");
        return null;
    }
}
