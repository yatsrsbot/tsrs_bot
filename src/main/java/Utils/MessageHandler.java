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
                    String userName = message.getFrom().getUserName();
                    command.execute(chatId, userRole, userId, userName);
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
                } else if (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStateEnum.USERS_GRANT_ROOT)) {
                    GrantRootCommand grantRoot = new GrantRootCommand();
                    grantRoot.execute(chatId, userRole, userId, messageText);
                } else if (ChatStateHolder
                        .getInstance()
                        .getChatState(chatId)
                        .equals(ChatStateEnum.USERS_DELETE)) {
                    DeleteUserCommand deleteUserCommand = new DeleteUserCommand();
                    deleteUserCommand.execute(chatId, userRole, userId, messageText);
                }

            }
        } else if (update.hasCallbackQuery()) {

            String call_data = update.getCallbackQuery().getData();

            String result = call_data.split(":")[0];
            Integer acceptedUserId = Integer.parseInt(call_data.split(":")[1]);
            Long acceptedUserChatId = ChatStateHolder.getInstance().getChatId(acceptedUserId);

            int message_id = update.getCallbackQuery().getMessage().getMessageId();
            long chat_id = update.getCallbackQuery().getMessage().getChatId();
            String answer = "";
            if (result.equals("ACCESS_GRANTED")) {
                answer = "Доступ предоставлен";

                DatabaseUtil.insertUserRecordsIntoDatabase(acceptedUserId);
                UserHolder.getInstance().reloadUsersHolder();

                Sender.getInstance().editMessageText(answer, chat_id, message_id);
                Sender.getInstance().sendTextMessage("Доступ предоставлен", acceptedUserChatId);

                Role acceptedUserRole = UserHolder.getInstance().getUserRole(acceptedUserId);

                ChatStateHolder.getInstance().setChatState(acceptedUserChatId, ChatStateEnum.DEFAULT, acceptedUserId);
                ICommand command = CommonsUtil.getCommand("/start");
                command.execute(acceptedUserChatId, acceptedUserRole, acceptedUserId);
            }
        }

    }
}



