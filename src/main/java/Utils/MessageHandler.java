package Utils;


import Commands.CommandList;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
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


    public void handle(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            long chatId = message.getChatId();
            String messageText = message.getText();
            if (CommonsUtil.isCommand(messageText)) {
                if (CommonsUtil.getCommand(messageText)
                        .equals(CommandList.exit)) {
                    ChatStateHolder.getInstance().setChatState(chatId, ChatStates.DEFAULT);
                    Commands.ExitCommand.execute(chatId);
                } else if (CommonsUtil.getCommand(messageText).equals(CommandList.auction)) {
                    Commands.AuctionCommand.execute(chatId);
                    ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION);
                } else if (CommonsUtil.getCommand(messageText)
                        .equals(CommandList.view) && (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStates.AUCTION))) {
                    ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_VIEW);
                    Sender.getInstance().sendMessageWithKeyboard("Введите название аукциона", chatId, InlineKeyboards.getExitKeyboard());
                } else if (CommonsUtil.getCommand(messageText)
                        .equals(CommandList.update) && (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStates.AUCTION))) {
                    ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_UPDATE);
                    Sender
                            .getInstance()
                            .sendMessageWithKeyboard("Введите название аукциона м значение в виде Аукицон/Значение", chatId, InlineKeyboards.getExitKeyboard());
                } else if (CommonsUtil
                        .getCommand(messageText)
                        .equals(CommandList.delete) && (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStates.AUCTION))) {
                    ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_DELETE);
                    Sender.getInstance().sendMessageWithKeyboard("Введите название аукциона", chatId, InlineKeyboards.getExitKeyboard());
                } else if (CommonsUtil.getCommand(messageText)
                        .equals(CommandList.add) && (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStates.AUCTION))) {
                    ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_ADD);
                    Sender
                            .getInstance()
                            .sendMessageWithKeyboard("Введите название аукциона м значение в виде Аукицон/Значение", chatId, InlineKeyboards.getExitKeyboard());
                }
            } else if (messageText.equals(CommandList.start)) {
                Commands.StartCommand.execute(chatId);
                ChatStateHolder.getInstance().setChatState(chatId, ChatStates.DEFAULT);
            } else if (ChatStateHolder
                    .getInstance()
                    .getChatState(chatId)
                    .equals(ChatStates.AUCTION_VIEW)) {
                Commands.ViewAuctionCommand.execute(chatId, messageText);
            } else if (ChatStateHolder
                    .getInstance()
                    .getChatState(chatId)
                    .equals(ChatStates.AUCTION_UPDATE)) {
                Commands.UpdateAuctionCommand.execute(chatId, messageText);
            } else if ((ChatStateHolder
                    .getInstance()
                    .getChatState(chatId)
                    .equals(ChatStates.AUCTION_DELETE))) {
                Commands.DeleteAuctionCommand.execute(chatId, messageText);
            } else if (ChatStateHolder
                    .getInstance()
                    .getChatState(chatId)
                    .equals(ChatStates.AUCTION_ADD)) {
                Commands.AddAuctionCommand.execute(chatId, messageText);
            }

        }
    }

}

