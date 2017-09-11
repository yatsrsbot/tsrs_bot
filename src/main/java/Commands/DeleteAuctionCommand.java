package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.DatabaseUtil;
import Utils.InlineKeyboards;
import Utils.Sender;

public class DeleteAuctionCommand extends AbstractCommand {

    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (Role.ADMIN.equals(role)) {
            if (stateHolder.getChatState(chatId).equals(ChatStateEnum.AUCTION)) {
                stateHolder.setChatState(
                        chatId,
                        ChatStateEnum.AUCTION_DELETE,
                        userId);

                sender.sendMessageWithKeyboard(
                        "Введите название аукциона",
                        chatId,
                        InlineKeyboards.getExitKeyboard());
            } else if (stateHolder.getChatState(chatId).equals(ChatStateEnum.AUCTION_DELETE)) {

                if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(strings[0])) {
                    DatabaseUtil.deleteAuctionRecordFromDataBase(strings[0]);
                    sender.sendTextMessage("Запись удалена", chatId);
                } else {
                    sender.sendTextMessage("Нет такой записи", chatId);
                }
            }
        }
    }
}