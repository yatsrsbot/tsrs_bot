package Commands;

import Utils.DatabaseUtil;
import Utils.Sender;

public class ViewAuctionCommand {
    public static void execute(Long chatId, String auctionName) {
        if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)) {
            String responseMessage = DatabaseUtil.getAuctionRecordsFromDatabase().get(auctionName).toString();
            Sender.getInstance().sendTextMessage(responseMessage, chatId);
        } else {
            Sender.getInstance().sendTextMessage("Нет такой записи", chatId);
        }
    }
}
