package Commands;

import Utils.Sender;

public class ExitCommand {
    public static void execute(Long chatId) {
        Sender
                .getInstance()
                .sendTextMessage("Привет!",chatId);
    }
}
