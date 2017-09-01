package Commands;

import Utils.AuctionUtil;
import Utils.ChatStateHolder;
import Utils.ChatStates;
import Utils.Sender;

public class UpdateAuctionCommand {
    public static void execute(Long chatId, String auctionNameAndValue) {
        if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStates.AUCTION_UPDATE)) {
            String[] data = auctionNameAndValue.split(":");
            String auctionName = data[0];
            Integer value = Integer.parseInt(data[1]);
            String auctionRecordName = AuctionUtil.getAuctionRecordsFromDatabase().get(auctionName).toString();
            if ("".equals(auctionRecordName)) {
                Sender.getInstance().sendTextMessage("there are no such record", chatId);
            }else {AuctionUtil.updateAuctionRecordsFromDatabase(auctionName,value);}        }
    }
}

