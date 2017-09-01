package Commands;

import Utils.AuctionUtil;
import Utils.Sender;

public class DeleteAuctionCommand {
    public static void execute(Long chatId, String auctionName) {
        String auctionRecord = AuctionUtil.getAuctionRecordsFromDatabase().get(auctionName).toString();
        if (!"".equals(auctionName)) {
            AuctionUtil.deleteAuctionRecordFromDataBase(auctionName);
            Sender.getInstance().sendTextMessage("record deleted",chatId);
        } else {
            Sender.getInstance().sendTextMessage("there are no such record", chatId);
        }
    }

}
