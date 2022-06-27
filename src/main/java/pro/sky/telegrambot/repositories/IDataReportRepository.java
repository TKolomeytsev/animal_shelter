package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.DataReport;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>IDataReportRepository</b> - репозиторий отчетов.<br/>
 */
public interface IDataReportRepository extends JpaRepository<DataReport,String> {

    List<DataReport> findAllBy();
    DataReport findAllByRepId (String repId);
    List<DataReport> findAllByChatId(long chatId);
    DataReport getDataReportByChatIdAndDateSend(long chatId, LocalDateTime dateSend);
    void deleteAllByRepId(String repId);
    void deleteAllByChatId(long chatId);

}
