package Commands;

import Utils.AuctionUtil;
import Utils.CommonsUtil;
import Utils.Sender;

public class AddAuctionCommand {
    public static void execute(Long chatId, String auctionNameAndValue) {

        String auctionName = null;
        Integer value = null;
        try {
            String[] data = auctionNameAndValue.split("/");
            auctionName = CommonsUtil.capitalize(data[0]);
            value = Integer.parseInt(data[1]);
            if (AuctionUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)) {
                Sender.getInstance().sendTextMessage("this record exists already, try update instead", chatId);
            } else {
                AuctionUtil.insertAuctionRecordsIntoDatabase(auctionName, value);
                Sender.getInstance().sendTextMessage("auction added, you can enter another value", chatId);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Sender.getInstance().sendTextMessage("wrong format, try again :)", chatId);
        }
    }
}


