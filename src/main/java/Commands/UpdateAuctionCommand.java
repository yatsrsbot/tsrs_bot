package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.DatabaseUtil;
import Utils.InlineKeyboards;
import Utils.Sender;

public class UpdateAuctionCommand implements ICommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (Role.ADMIN.equals(role)) {
            if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStateEnum.AUCTION)) {
                ChatStateHolder.getInstance().setChatState(chatId, ChatStateEnum.AUCTION_UPDATE, userId);
                Sender
                        .getInstance()
                        .sendMessageWithKeyboard("Введите название аукциона и значение в виде Аукицон/Значение", chatId, InlineKeyboards.getExitKeyboard());
            } else if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStateEnum.AUCTION_UPDATE)) {
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
}


