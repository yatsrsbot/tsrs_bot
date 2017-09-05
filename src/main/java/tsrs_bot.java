import Utils.MessageHandler;
import Utils.UserHolder;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class tsrs_bot extends TelegramLongPollingBot {


    private MessageHandler messageHandler;
    private String botToken = "449828975:AAFFC60Q31Z2Ip5wWyuLZwrMhW5SY7EwoBs";
    public tsrs_bot(){
        UserHolder.getInstance().reloadUsersHolder();
    }





    @Override
    public void onUpdateReceived(Update update) {

            // Set variables

        MessageHandler.getInstance().handle(update);
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

