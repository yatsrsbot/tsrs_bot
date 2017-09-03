package Utils;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Sender {
    private AbsSender bot;

    private static Sender instance;


    private Sender() {
    }

    public static Sender getInstance() {
        if (instance == null) {
            instance = new Sender();
        }
        return instance;
    }
    public void setBot(AbsSender bot){
        this.bot = bot;
    }


    public void sendTextMessage(String text, Long chatId) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId)
                .setText(text);
        try {
            bot.sendMessage(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
    public void sendMessageWithKeyboard(String text, Long chatId, InlineKeyboardMarkup keyboardMarkup) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId)
                .setText(text);
        message.setReplyMarkup(keyboardMarkup);
        try {
            bot.sendMessage(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
