package Utils;


import Commands.CommandList;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.bots.AbsSender;


public class MessageHandler {

    private static MessageHandler instance;

    private MessageHandler() {
    }

    private MessageHandler(AbsSender bot) {
    }

    public static MessageHandler getInstance(AbsSender bot) {
        if (instance == null) {
            instance = new MessageHandler(bot);
        }
        Sender.getInstance().setBot(bot);
        return instance;
    }


    public void Handle(Message message, Long chatId) {
        if (message.getText().equalsIgnoreCase(CommandList.start)) {
            Commands.StartCommand.execute(chatId);
            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.DEFAULT);
        } else if (message.getText().equalsIgnoreCase(CommandList.auction)) {
            Commands.AuctionCommand.execute(chatId);
            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION);
        } else if ((message
                .getText()
                .equalsIgnoreCase(CommandList.view)) && (ChatStateHolder
                .getInstance()
                .getChatState(chatId)
                .equals(ChatStates.AUCTION))) {
            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_VIEW);
            Sender.getInstance().sendTextMessage("please insert Auction Name", chatId);
        } else if (ChatStateHolder
                .getInstance()
                .getChatState(chatId)
                .equals(ChatStates.AUCTION_VIEW)) {
            Commands.ViewAuctionCommand.execute(chatId, message.getText());
        } else if ((message
                .getText()
                .equalsIgnoreCase(CommandList.update)) && (ChatStateHolder
                .getInstance()
                .getChatState(chatId)
                .equals(ChatStates.AUCTION))) {
            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_UPDATE);
            Sender.getInstance().sendTextMessage("please insert Auction Name and value, just like this Auction:Value", chatId);
        } else if (ChatStateHolder
                .getInstance()
                .getChatState(chatId)
                .equals(ChatStates.AUCTION_UPDATE)) {
            Commands.UpdateAuctionCommand.execute(chatId, message.getText());
        }


    }
}
