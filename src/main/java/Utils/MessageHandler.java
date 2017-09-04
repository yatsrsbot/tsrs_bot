package Utils;


import Enums.ChatStates;
import Enums.Command;
import Enums.Role;
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
            Integer userId = message.getFrom().getId();
            long chatId = message.getChatId();
            Role userRole = UserHolder.getInstance().getUserRole(userId);
            Sender.getInstance().sendTextMessage(userRole.toString(),chatId);
            String messageText = message.getText();
            if (CommonsUtil.isCommand(messageText)) {
                Command command = CommonsUtil.getCommand(messageText);
                switch (command) {
                    case EXIT:
                        ChatStateHolder.getInstance().setChatState(chatId, ChatStates.DEFAULT);
                        Commands.ExitCommand.execute(chatId);
                        break;
                    case AUCTION:
                        Commands.AuctionCommand.execute(chatId);
                        ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION);
                        break;
                    case AUCTION_VIEW:
                        if (ChatStateHolder
                                .getInstance()
                                .getChatState(chatId)
                                .equals(ChatStates.AUCTION)) {
                            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_VIEW);
                            Sender.getInstance().sendMessageWithKeyboard("Введите название аукциона", chatId, InlineKeyboards.getExitKeyboard());
                        }
                        break;
                    case AUCTION_EDIT:
                        if (ChatStateHolder
                                .getInstance()
                                .getChatState(chatId)
                                .equals(ChatStates.AUCTION)) {
                            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_UPDATE);
                            Sender
                                    .getInstance()
                                    .sendMessageWithKeyboard("Введите название аукциона и значение в виде Аукицон/Значение", chatId, InlineKeyboards.getExitKeyboard());

                        }
                        break;
                    case AUCTION_DELETE:
                        if (ChatStateHolder
                                .getInstance()
                                .getChatState(chatId)
                                .equals(ChatStates.AUCTION)) {
                            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_DELETE);
                            Sender.getInstance().sendMessageWithKeyboard("Введите название аукциона", chatId, InlineKeyboards.getExitKeyboard());

                        }
                        break;
                    case AUCTION_ADD:
                        if (ChatStateHolder
                                .getInstance()
                                .getChatState(chatId)
                                .equals(ChatStates.AUCTION)) {
                            Sender
                                    .getInstance()
                                    .sendMessageWithKeyboard("Введите название аукциона и значение в виде Аукицон/Значение", chatId, InlineKeyboards.getExitKeyboard());

                        }
                        break;
                    case START:
                        Commands.StartCommand.execute(chatId);
                        ChatStateHolder.getInstance().setChatState(chatId, ChatStates.DEFAULT);
                        break;


                }
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

