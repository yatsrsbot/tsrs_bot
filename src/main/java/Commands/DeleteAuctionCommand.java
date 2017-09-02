package Commands;

import Utils.AuctionUtil;
import Utils.Sender;

public class DeleteAuctionCommand {
    public static void execute(Long chatId, String auctionName) {
        if (AuctionUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)){
            AuctionUtil.deleteAuctionRecordFromDataBase(auctionName);
            Sender.getInstance().sendTextMessage("record deleted, u can enter another Auction name",chatId);
        } else {
            Sender.getInstance().sendTextMessage("there are no such record, try again", chatId);
        }
    }

}
