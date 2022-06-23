package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.DataReport;

import java.util.List;

public interface IDataReportRepository extends JpaRepository<DataReport,String> {

    List<DataReport> findAllBy();
    DataReport findAllByRepId (String repId);
    List<DataReport> findAllByChatId(long chatId);
    void deleteAllByRepId(String repId);
    void deleteAllByChatId(long chatId);

}
