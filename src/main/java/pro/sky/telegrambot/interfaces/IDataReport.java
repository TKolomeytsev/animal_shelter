package pro.sky.telegrambot.interfaces;

import pro.sky.telegrambot.models.DataReport;
import pro.sky.telegrambot.models.StandartResponse;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * Интерфейс <b>IDataReport</b> - декларирует методы работы с хранилищем отчетов.<br/>
 */
public interface IDataReport {
    List<DataReport> getAllDataReport();

    DataReport getDataReportByRepId(String repId);

    List<DataReport> getAllDataReportByChatId(long chatId);

    DataReport save(DataReport report);

    DataReport update(DataReport report);

    DataReport deleteAllByRepId(String repId);

    List<DataReport> deleteAllByChatId(long chatId);

    DataReport getDataReportByChatIdAndDateSend(long chatId, LocalDateTime dateSend);
}
