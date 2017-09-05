package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.InlineKeyboards;
import Utils.Sender;

public class ExitCommand implements ICommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Привет!",chatId, InlineKeyboards.getDefaultKeyboard(role));
        ChatStateHolder.getInstance().setChatState(chatId, ChatStateEnum.DEFAULT,userId);
    }
}
