package Commands;

import Utils.DatabaseUtil;
import Utils.Sender;

public class UpdateAuctionCommand {
    public static void execute(Long chatId, String auctionNameAndValue) {

            String auctionName = null;
            Integer value = null;
            try {
                String[] data = auctionNameAndValue.split("/");
                auctionName = data[0];
                value = Integer.parseInt(data[1]);
                if (!DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)) {
                    Sender.getInstance().sendTextMessage("Нет такой записи", chatId);
                } else {
                    DatabaseUtil.updateAuctionRecordsFromDatabase(auctionName, value);
                    Sender.getInstance().sendTextMessage("Запись изменена", chatId);
                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Sender.getInstance().sendTextMessage("Неверный формат", chatId);
            }

        }
    }


