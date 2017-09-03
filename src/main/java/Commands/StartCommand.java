package Commands;


import Utils.InlineKeyboards;
import Utils.Sender;

public class StartCommand {


    public static void execute(Long chatId) {
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Привет!",chatId, InlineKeyboards.getDefaultKeyboard());
    }
}