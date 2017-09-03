package Commands;

import Utils.InlineKeyboards;
import Utils.Sender;


public class AuctionCommand {

    public static void execute(Long chatId) {
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Выберите действие",chatId,InlineKeyboards.getAcutionKeyboard());
    }
}
