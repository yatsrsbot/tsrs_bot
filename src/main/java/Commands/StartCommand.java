package Commands;


import Enums.Role;
import Utils.InlineKeyboards;
import Utils.Sender;

public class StartCommand implements ICommand {

    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Привет!",chatId, InlineKeyboards.getDefaultKeyboard(role));
    }
}