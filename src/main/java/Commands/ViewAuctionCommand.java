package Commands;

import Utils.AuctionUtil;
import Utils.Sender;

public class ViewAuctionCommand {
    public static void execute(Long chatId, String auctionName) {
        if (AuctionUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)) {
            String responseMessage = AuctionUtil.getAuctionRecordsFromDatabase().get(auctionName).toString();
            Sender.getInstance().sendTextMessage(responseMessage, chatId);
        } else {
            Sender.getInstance().sendTextMessage("there are no such record, try again", chatId);
        }
    }
}
