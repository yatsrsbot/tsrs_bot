package Utils;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import static java.lang.Math.toIntExact;

public class Sender {

    private AbsSender bot;

    private Sender(){}

    private static class SenderHolder {
        private static final Sender HOLDER_INSTANCE = new Sender();
    }

    public static Sender getInstance() {
        return SenderHolder.HOLDER_INSTANCE;
    }

    public void setBot(AbsSender bot){
        this.bot = bot;
    }

    public void sendTextMessage(String text, Long chatId) {
        sendMessageWithKeyboard(text, chatId, null);
    }

    public void sendMessageWithKeyboard(String text, Long chatId, ReplyKeyboard keyboardMarkup) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId)
                .setText(text);

        if(keyboardMarkup != null)
            message.setReplyMarkup(keyboardMarkup);

        try {
            bot.sendMessage(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void editMessageText(String text, Long chatId, int messageId) {
        EditMessageText message = new EditMessageText()
                .setChatId(chatId)
                .setMessageId(toIntExact(messageId))
                .setText(text);
        try {
            bot.editMessageText(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
