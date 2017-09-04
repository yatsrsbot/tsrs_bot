package Commands;

import Enums.ChatStates;
import Utils.ChatStateHolder;
import Utils.DatabaseUtil;
import Utils.InlineKeyboards;
import Utils.Sender;

public class UpdateAuctionCommand implements ICommand {
    @Override
    public void execute(Long chatId, String... strings) {
        if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStates.AUCTION)) {
            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_UPDATE);
            Sender
                    .getInstance()
                    .sendMessageWithKeyboard("Введите название аукциона и значение в виде Аукицон/Значение", chatId, InlineKeyboards.getExitKeyboard());
        } else if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStates.AUCTION_UPDATE)) {
            String auctionName;
            Integer value;
            try {
                String[] data = strings[0].split("/");
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
}


