package Utils;


import Commands.*;
import Enums.ChatStates;
import Enums.CommandEnum;
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

    public static MessageHandler getInstance() {
        if (instance == null) {
            instance = new MessageHandler();
        }
        return instance;
    }


    public void handle(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            Integer userId = message.getFrom().getId();
            long chatId = message.getChatId();
            Role userRole = UserHolder.getInstance().getUserRole(userId);
            if (userRole == Role.NONE) {
                Sender.getInstance().sendTextMessage("У вас нет доступа", chatId);
            } else {
                String messageText = message.getText();
                if (CommonsUtil.isCommand(messageText)) {
                    ICommand command = CommonsUtil.getCommand(messageText);
                    command.execute(chatId);

                } else if (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStates.AUCTION_VIEW)) {
                    ViewAuctionCommand viewAuctionCommand = new ViewAuctionCommand();
                    viewAuctionCommand.execute(chatId, messageText);
                } else if (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStates.AUCTION_UPDATE)) {
                    UpdateAuctionCommand updateAuctionCommand = new UpdateAuctionCommand();
                    updateAuctionCommand.execute(chatId, messageText);
                } else if ((ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStates.AUCTION_DELETE))) {
                    DeleteAuctionCommand deleteAuctionCommand = new DeleteAuctionCommand();
                    deleteAuctionCommand.execute(chatId, messageText);
                } else if (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStates.AUCTION_ADD)) {
                    AddAuctionCommand addAuctionCommand = new AddAuctionCommand();
                    addAuctionCommand.execute(chatId, messageText);
                }

            }
        }
    }
}

