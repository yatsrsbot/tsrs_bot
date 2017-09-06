package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.*;

public class GrantRootCommand implements ICommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (Role.ADMIN.equals(role)) {
            if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStateEnum.USERS)) {
                ChatStateHolder.getInstance().setChatState(chatId, ChatStateEnum.USERS_GRANT_ROOT, userId);
                Sender
                        .getInstance()
                        .sendMessageWithKeyboard("Введите имя пользователя", chatId, InlineKeyboards.getExitKeyboard());
            } else if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStateEnum.USERS_GRANT_ROOT)) {

                String accepteduserName = strings[0];



                if (!UserHolder.getInstance().containsUserName(accepteduserName)) {
                    Sender.getInstance().sendTextMessage("Нет такой записи", chatId);
                } else {
                    Integer acceptedUserId = UserHolder.getInstance().getUserIdbyName(accepteduserName);
                    Long acceptedUserChatId = ChatStateHolder.getInstance().getChatId(acceptedUserId);


                    DatabaseUtil.updateUserRecordsFromDatabase(accepteduserName, acceptedUserId);
                    UserHolder.getInstance().reloadUsersHolder();
                    Role acceptedUserRole = UserHolder.getInstance().getUserRole(acceptedUserId);

                    Sender.getInstance().sendTextMessage("Запись изменена", chatId);

                    Sender.getInstance().sendTextMessage("Вам предоставлены права администратора", acceptedUserChatId);
                    ICommand command = CommonsUtil.getCommand("/start");
                    command.execute(acceptedUserChatId, acceptedUserRole, acceptedUserId);
                }
            }
        }
    }
}
