import Utils.MessageHandler;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class tsrs_bot extends TelegramLongPollingBot {


    private long chat_id;
    private MessageHandler messageHandler = new MessageHandler(this);
    private String botToken = "449828975:AAFFC60Q31Z2Ip5wWyuLZwrMhW5SY7EwoBs";



    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            chat_id = update.getMessage().getChatId();

            messageHandler.Handle(message_text, chat_id);
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

