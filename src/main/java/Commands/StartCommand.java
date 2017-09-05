package Commands;


import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.InlineKeyboards;
import Utils.Sender;

public class StartCommand implements ICommand {

    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {

        if (Role.NONE.equals(role)){
            ChatStateHolder.getInstance().setChatState(chatId, ChatStateEnum.DEFAULT_UNREGISTERED,userId);
        }
        else {ChatStateHolder.getInstance().setChatState(chatId,ChatStateEnum.DEFAULT,userId);}
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Привет!",chatId, InlineKeyboards.getDefaultKeyboard(role));
    }
}