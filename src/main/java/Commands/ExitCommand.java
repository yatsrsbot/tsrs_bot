package Commands;

import Utils.InlineKeyboards;
import Utils.Sender;

public class ExitCommand implements ICommand {
    @Override
    public void execute(Long chatId, String... strings) {
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Привет!",chatId, InlineKeyboards.getDefaultKeyboard());
    }
}
