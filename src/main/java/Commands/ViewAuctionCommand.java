package Commands;

import Utils.AuctionUtil;
import Utils.Sender;

public class ViewAuctionCommand {
    public static void execute(Long chatId, String auctionId) {
        String responseMessage = AuctionUtil.getAuctionRecordsFromDatabase().get(auctionId).toString();
        Sender.getInstance().sendTextMessage(responseMessage,chatId);
    }
}
