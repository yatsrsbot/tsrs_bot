package Commands;


import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.InlineKeyboards;
import Utils.Sender;

public class StartCommand extends AbstractCommand {

    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {

        if (Role.NONE.equals(role)){
            stateHolder.setChatState(
                    chatId,
                    ChatStateEnum.DEFAULT_UNREGISTERED,
                    userId);
        }
        else {stateHolder.setChatState(chatId,ChatStateEnum.DEFAULT,userId);}
        sender.sendMessageWithKeyboard(
                "Привет!",
                chatId,
                InlineKeyboards.getDefaultKeyboard(role));
    }
}