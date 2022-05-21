package pro.sky.telegrambot.listener;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.sky.telegrambot.services.BotServices;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class TelegramBotUpdatesListener implements UpdatesListener {

    private Logger logger = LoggerFactory.getLogger(TelegramBotUpdatesListener.class);
    private final BotServices botServices;

    @Autowired
    private TelegramBot telegramBot;

    public TelegramBotUpdatesListener(BotServices botServices) {
        this.botServices = botServices;
    }

    @PostConstruct
    public void init() {
        telegramBot.setUpdatesListener(this);
    }

    //Слушатель
    @Override
    public int process(List<Update> updates) {
        updates.forEach(update -> {
            logger.info("Processing update: {}", update);
            botServices.router(update);
        });
        return UpdatesListener.CONFIRMED_UPDATES_ALL;
    }

}
