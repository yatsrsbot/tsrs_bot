package Commands;

import Utils.AuctionUtil;
import Utils.ChatStateHolder;
import Utils.ChatStates;
import Utils.Sender;

public class UpdateAuctionCommand {
    public static void execute(Long chatId, String auctionNameAndValue) {

            String auctionName = null;
            Integer value = null;
            try {
                String[] data = auctionNameAndValue.split("/");
                auctionName = data[0];
                value = Integer.parseInt(data[1]);
                if (!AuctionUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)) {
                    Sender.getInstance().sendTextMessage("there are no such record", chatId);
                } else {
                    AuctionUtil.updateAuctionRecordsFromDatabase(auctionName, value);
                    Sender.getInstance().sendTextMessage("auction updated, you can enter another value", chatId);
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Sender.getInstance().sendTextMessage("wrong format, try again :)", chatId);
            }

        }
    }


