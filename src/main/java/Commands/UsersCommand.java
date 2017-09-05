package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.InlineKeyboards;
import Utils.Sender;

public class UsersCommand implements ICommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (role.equals(Role.ADMIN)) {
            if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStateEnum.DEFAULT)) {
                Sender
                        .getInstance()
                        .sendMessageWithKeyboard("Выберите действие", chatId, InlineKeyboards.getUsersKeyboard());
                ChatStateHolder.getInstance().setChatState(chatId, ChatStateEnum.USERS, userId);
            }
        }
    }
}
