package Commands;

import Utils.Sender;


public class AuctionCommand {

    public static void execute(Long chatId) {
        Sender.getInstance().sendTextMessage("Auction \n list of commands\n /view \n /add \n /update \n /delete \n /exit",chatId);
    }
}
