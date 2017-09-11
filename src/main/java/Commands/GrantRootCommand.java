package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.*;

public class GrantRootCommand extends AbstractCommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (Role.ADMIN.equals(role)) {
            if (stateHolder.getChatState(chatId).equals(ChatStateEnum.USERS)) {
                stateHolder.setChatState(
                        chatId,
                        ChatStateEnum.USERS_GRANT_ROOT,
                        userId);

                sender.sendMessageWithKeyboard(
                        "Введите имя пользователя",
                        chatId,
                        InlineKeyboards.getExitKeyboard());
            } else if (stateHolder.getChatState(chatId).equals(ChatStateEnum.USERS_GRANT_ROOT)) {

                String acceptedUserName = strings[0];

                if (!UserHolder.getInstance().containsUserName(acceptedUserName)) {
                    sender.sendTextMessage("Нет такой записи", chatId);
                } else {
                    Integer acceptedUserId = UserHolder.getInstance().getUserIdbyName(acceptedUserName);
                    Long acceptedUserChatId = stateHolder.getChatId(acceptedUserId);


                    DatabaseUtil.updateUserRecordsFromDatabase(acceptedUserName, acceptedUserId);
                    UserHolder.getInstance().reloadUsersHolder();
                    Role acceptedUserRole = UserHolder.getInstance().getUserRole(acceptedUserId);

                    sender.sendTextMessage("Запись изменена", chatId);

                    sender.sendTextMessage("Вам предоставлены права администратора", acceptedUserChatId);
                    ICommand command = CommonsUtil.getCommand("/start");
                    command.execute(acceptedUserChatId, acceptedUserRole, acceptedUserId);
                }
            }
        }
    }
}
