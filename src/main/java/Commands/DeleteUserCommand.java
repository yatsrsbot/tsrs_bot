package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.*;

public class DeleteUserCommand implements ICommand{

    private ChatStateHolder stateHolder = ChatStateHolder.getInstance();
    private Sender sender = Sender.getInstance();
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (Role.ADMIN.equals(role)) {
            if (stateHolder.getChatState(chatId).equals(ChatStateEnum.USERS)) {
                stateHolder.setChatState(chatId, ChatStateEnum.USERS_DELETE, userId);
                sender.sendMessageWithKeyboard("Имя пользователя", chatId, InlineKeyboards.getExitKeyboard());
            } else if (stateHolder.getChatState(chatId).equals(ChatStateEnum.USERS_DELETE)) {

                if (UserHolder.getInstance().containsUserName(strings[0])) {
                    DatabaseUtil.deleteAuctionRecordFromDataBase(strings[0]);
                    sender.sendTextMessage("Запись удалена", chatId);
                } else {
                    sender.sendTextMessage("Нет такой записи", chatId);
                }
            }
        }

    }
}
