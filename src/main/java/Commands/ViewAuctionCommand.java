package Commands;

import Utils.AuctionUtil;
import Utils.Sender;

public class ViewAuctionCommand {
    public static void execute(Long chatId, String auctionName) {
        String responseMessage = AuctionUtil.getAuctionRecordsFromDatabase().get(auctionName).toString();
        Sender.getInstance().sendTextMessage(responseMessage,chatId);
    }
}
