package Commands;

import Utils.AuctionUtil;
import Utils.Sender;

public class DeleteAuctionCommand {
    public static void execute(Long chatId, String auctionName) {
        if (AuctionUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)){
            AuctionUtil.deleteAuctionRecordFromDataBase(auctionName);
            Sender.getInstance().sendTextMessage("Запись удалена",chatId);
        } else {
            Sender.getInstance().sendTextMessage("Нет такой записи", chatId);
        }
    }

}
