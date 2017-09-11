package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.InlineKeyboards;
import Utils.Sender;

public class ExitCommand extends AbstractCommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {

        sender.sendMessageWithKeyboard("Привет!",
                chatId,
                InlineKeyboards.getDefaultKeyboard(role));

        stateHolder.setChatState(chatId,
                ChatStateEnum.DEFAULT, userId);
    }
}
