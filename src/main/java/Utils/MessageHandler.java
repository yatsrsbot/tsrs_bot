package Utils;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.bots.AbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class MessageHandler {
    private AbsSender bot;

    public MessageHandler(AbsSender bot) {
        this.bot = bot;
    }

    public void Handle(String message, long chatId) {
        if (message.equals("/start@tsrs_bot")) {
            sendTextMessage("HELLo",chatId);
        }
        String responseMessage = AuctionUtil.getAuctionRecordsFromDatabase().get(message).toString();
        sendTextMessage(responseMessage, chatId);
    }

    private void sendTextMessage(String text, long chatId) {
        SendMessage message = new SendMessage() // Create a message object object
                .setChatId(chatId)
                .setText(text);
        try {
            bot.sendMessage(message); // Sending our message object to user
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
