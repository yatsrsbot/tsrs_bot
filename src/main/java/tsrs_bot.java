import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class tsrs_bot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables
            String message_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            SendMessage message = new SendMessage() // Create a message object object
                    .setChatId(chat_id)
                    .setText(message_text);
            try {
                sendMessage(message); // Sending our message object to user
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public String getBotUsername() {

        return "tsrs_bot";
    }

    @Override
    public String getBotToken() {

        return "449828975:AAFFC60Q31Z2Ip5wWyuLZwrMhW5SY7EwoBs";
    }
}