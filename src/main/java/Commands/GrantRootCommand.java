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

                String userName = strings[0];

                if (!UserHolder.getInstance().containsUserName(userName)) {
                    Sender.getInstance().sendTextMessage("Нет такой записи", chatId);
                } else {
                    DatabaseUtil.updateUserRecordsFromDatabase(userName, userId);
                    Sender.getInstance().sendTextMessage("Запись изменена", chatId);
                }
            }
        }
    }
}
