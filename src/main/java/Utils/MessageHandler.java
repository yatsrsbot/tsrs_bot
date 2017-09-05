package Utils;


import Commands.*;
import Enums.ChatStateEnum;
import Enums.Role;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;


public class MessageHandler {

    private static class MessageHandlerHolder {
        private static final MessageHandler HOLDER_INSTANCE = new MessageHandler();
    }

    public static MessageHandler getInstance() {
        return MessageHandlerHolder.HOLDER_INSTANCE;
    }

    public void handle(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            Message message = update.getMessage();
            Integer userId = message.getFrom().getId();
            long chatId = message.getChatId();

            Role userRole = UserHolder.getInstance().getUserRole(userId);
            String messageText = message.getText();

            if (userRole == Role.NONE) {
                if (CommonsUtil.isCommand(messageText)) {
                    ICommand command = CommonsUtil.getCommand(messageText);
                    command.execute(chatId, userRole, userId);
                }
            } else {
                if (CommonsUtil.isCommand(messageText)) {
                    ICommand command = CommonsUtil.getCommand(messageText);
                    command.execute(chatId, userRole, userId);
                } else if (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStateEnum.AUCTION_VIEW)) {
                    ViewAuctionCommand viewAuctionCommand = new ViewAuctionCommand();
                    viewAuctionCommand.execute(chatId, userRole, userId, messageText);
                } else if (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStateEnum.AUCTION_UPDATE)) {
                    UpdateAuctionCommand updateAuctionCommand = new UpdateAuctionCommand();
                    updateAuctionCommand.execute(chatId, userRole, userId, messageText);
                } else if ((ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStateEnum.AUCTION_DELETE))) {
                    DeleteAuctionCommand deleteAuctionCommand = new DeleteAuctionCommand();
                    deleteAuctionCommand.execute(chatId, userRole, userId, messageText);
                } else if (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStateEnum.AUCTION_ADD)) {
                    AddAuctionCommand addAuctionCommand = new AddAuctionCommand();
                    addAuctionCommand.execute(chatId, userRole, userId, messageText);
                }

            }
        }
    }
}

