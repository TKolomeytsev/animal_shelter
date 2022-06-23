package pro.sky.telegrambot.services;

import org.springframework.stereotype.Service;
import pro.sky.telegrambot.exception.ExceptionNotFoundAnimalKind;
import pro.sky.telegrambot.exception.ExceptionServerError;
import pro.sky.telegrambot.interfaces.IDataReport;
import pro.sky.telegrambot.models.DataReport;
import pro.sky.telegrambot.repositories.IDataReportRepository;

import java.util.List;

@Service
public class DataReportService implements IDataReport {
    private final IDataReportRepository dataReportRepository;

    public DataReportService(IDataReportRepository dataReportRepository) {
        this.dataReportRepository = dataReportRepository;
    }

    @Override
    public List<DataReport> getAllDataReport() {
        List<DataReport> list = dataReportRepository.findAll();
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    @Override
    public DataReport getDataReportByRepId(String repId) {
        return dataReportRepository.findAllByRepId(repId);
    }

    @Override
    public List<DataReport> getAllDataReportByChatId(long chatId) {
        List<DataReport> list = dataReportRepository.findAllByChatId(chatId);
        if(list.size() > 0){
            return list;
        }
        return null;
    }

    @Override
    public DataReport save(DataReport report) {
        return dataReportRepository.save(report);
    }

    @Override
    public DataReport update(DataReport report) {
        return dataReportRepository.save(report);
    }

    @Override
    public DataReport deleteAllByRepId(String repId) {
        DataReport report = getDataReportByRepId(repId);
        if(report!=null){
            try{
                dataReportRepository.deleteAllByRepId(repId);
                return report;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }
        return null;
    }

    @Override
    public List<DataReport> deleteAllByChatId(long chatId) {
        List<DataReport> reports = getAllDataReportByChatId(chatId);
        if(reports!=null){
            try{
                dataReportRepository.deleteAllByChatId(chatId);
                return reports;
            }catch (ExceptionServerError exceptionServerError){
                throw exceptionServerError;
            }
        }else{
            throw new ExceptionNotFoundAnimalKind();
        }
    }
}
