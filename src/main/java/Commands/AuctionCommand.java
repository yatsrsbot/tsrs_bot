package Commands;

import Utils.InlineKeyboards;
import Utils.Sender;


public class AuctionCommand {

    public static void execute(Long chatId) {
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Auction \n list of commands\n /view \n /add \n /update \n /delete \n /exit \n pay attention:exit not working for now :( sorry\"",chatId, InlineKeyboards.getDefaultKeyboard());
    }
}
