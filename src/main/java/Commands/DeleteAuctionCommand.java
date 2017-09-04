package Commands;

import Utils.DatabaseUtil;
import Utils.Sender;

public class DeleteAuctionCommand {
    public static void execute(Long chatId, String auctionName) {
        if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)){
            DatabaseUtil.deleteAuctionRecordFromDataBase(auctionName);
            Sender.getInstance().sendTextMessage("Запись удалена",chatId);
        } else {
            Sender.getInstance().sendTextMessage("Нет такой записи", chatId);
        }
    }

}
