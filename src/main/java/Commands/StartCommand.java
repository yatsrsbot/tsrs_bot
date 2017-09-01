package Commands;


import Utils.Sender;

public class StartCommand {


    public static void execute(Long chatId) {
        Sender.getInstance().sendTextMessage("Oh, Hi there!",chatId);
    }
}