package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.DatabaseUtil;
import Utils.InlineKeyboards;
import Utils.Sender;

public class UpdateAuctionCommand extends AbstractCommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (Role.ADMIN.equals(role)) {
            if (stateHolder.getChatState(chatId).equals(ChatStateEnum.AUCTION)) {
                stateHolder.setChatState(
                        chatId,
                        ChatStateEnum.AUCTION_UPDATE,
                        userId);

                sender.sendMessageWithKeyboard(
                        "Введите название аукциона и значение в виде Аукицон/Значение",
                        chatId,
                        InlineKeyboards.getExitKeyboard());
            } else if (stateHolder.getChatState(chatId).equals(ChatStateEnum.AUCTION_UPDATE)) {
                String auctionName;
                Integer value;
                try {
                    String[] data = strings[0].split("/");
                    auctionName = data[0];
                    value = Integer.parseInt(data[1]);
                    if (!DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)) {
                        sender.sendTextMessage("Нет такой записи", chatId);
                    } else {
                        DatabaseUtil.updateAuctionRecordsFromDatabase(auctionName, value);
                        sender.sendTextMessage("Запись изменена", chatId);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    sender.sendTextMessage("Неверный формат", chatId);
                }

            }
        }
    }
}


