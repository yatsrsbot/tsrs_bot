package Commands;


import Utils.InlineKeyboards;
import Utils.Sender;

public class StartCommand {


    public static void execute(Long chatId) {
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Oh, Hi there!\nYou can work with auction records by sending /auction command",chatId, InlineKeyboards.getDefaultKeyboard());
    }
}