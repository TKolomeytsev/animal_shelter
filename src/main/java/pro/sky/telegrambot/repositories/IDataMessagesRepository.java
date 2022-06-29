package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pro.sky.telegrambot.models.DataMessage;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author AKolomeytsev<br/>
 * <b>IDataMessagesRepository</b> - репозиторий сообщений.<br/>
 */
public interface IDataMessagesRepository extends JpaRepository<DataMessage,String> {
    List<DataMessage> findAllByChatId(long chatId);

    List<DataMessage> findAllByDateSend(LocalDateTime dateSend);

    List<DataMessage> findAllByDateSendAndChatId(LocalDateTime dateSend, long chatId);
    @Query(nativeQuery = true, value = "SELECT id, message, chat_id, date_send FROM data_messages WHERE message LIKE '/%' AND chat_id = :chatId ORDER BY date_send DESC")
    List<DataMessage> findByChatIdOrderByDateSendDesc(@Param("chatId") long chatId);
}
