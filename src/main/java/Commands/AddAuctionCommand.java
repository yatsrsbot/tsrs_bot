package Commands;

import Utils.DatabaseUtil;
import Utils.CommonsUtil;
import Utils.Sender;

public class AddAuctionCommand {
    public static void execute(Long chatId, String auctionNameAndValue) {

        String auctionName = null;
        Integer value = null;
        try {
            String[] data = auctionNameAndValue.split("/");
            auctionName = CommonsUtil.capitalize(data[0].toLowerCase());
            value = Integer.parseInt(data[1]);
            if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)) {
                Sender.getInstance().sendTextMessage("Эта запись существует, попробуйте команду \"Изменить\"", chatId);
            } else {
                DatabaseUtil.insertAuctionRecordsIntoDatabase(auctionName, value);
                Sender.getInstance().sendTextMessage("Запись добавлена", chatId);
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            Sender.getInstance().sendTextMessage("Неверный формат", chatId);
        }
    }
}


