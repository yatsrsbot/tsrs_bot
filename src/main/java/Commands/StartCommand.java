package Commands;


import Utils.Sender;

public class StartCommand {


    public static void execute(Long chatId) {
        Sender
                .getInstance()
                .sendTextMessage("Oh, Hi there!\nYou can work with auction records by sending /auction command",chatId);
    }
}