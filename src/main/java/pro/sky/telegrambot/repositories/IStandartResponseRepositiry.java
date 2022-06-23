package pro.sky.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pro.sky.telegrambot.models.StandartResponse;

import java.util.List;

public interface IStandartResponseRepositiry extends JpaRepository<StandartResponse,String> {
    List<StandartResponse> findAll();
    StandartResponse findByResId(String resId);
    List<StandartResponse> findByRelationId(String relationId);
    void deleteByResId (String resId);
    void deleteByRelationId(String relationId);

}
