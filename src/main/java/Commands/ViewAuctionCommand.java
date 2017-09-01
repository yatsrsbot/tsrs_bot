package Commands;

import Utils.AuctionUtil;
import Utils.Sender;

public class ViewAuctionCommand {
    public static void execute(Long chatId, String auctionName) {
//        String responseMessage = AuctionUtil.getAuctionRecordsFromDatabase().get(auctionName).toString();
        String responseMessage = AuctionUtil.getAuctionRecordsFromDatabase().get("Афиша").toString();
        Sender.getInstance().sendTextMessage(responseMessage,chatId);
        Sender.getInstance().sendTextMessage(auctionName,chatId);

    }
}
