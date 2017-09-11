package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.InlineKeyboards;
import Utils.Sender;

public class UsersCommand extends AbstractCommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (role.equals(Role.ADMIN)) {
            if (stateHolder.getChatState(chatId).equals(ChatStateEnum.DEFAULT)) {
                sender.sendMessageWithKeyboard(
                        "Выберите действие",
                        chatId,
                        InlineKeyboards.getUsersKeyboard());

                stateHolder.setChatState(chatId, ChatStateEnum.USERS, userId);
            }
        }
    }
}
