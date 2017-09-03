import Utils.AuctionUtil;
import Utils.MessageHandler;
import Utils.Sender;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class tsrs_bot extends TelegramLongPollingBot {


    private MessageHandler messageHandler;
    private String botToken = "449828975:AAFFC60Q31Z2Ip5wWyuLZwrMhW5SY7EwoBs";




    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables

            MessageHandler.getInstance(this).Handle(update);

        }
    }


    @Override
    public String getBotUsername() {

        return "tsrs_bot";
    }

    @Override
    public String getBotToken() {

        return botToken;
    }


}

