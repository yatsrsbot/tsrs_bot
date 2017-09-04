package Commands;

import Enums.ChatStates;
import Utils.ChatStateHolder;
import Utils.DatabaseUtil;
import Utils.InlineKeyboards;
import Utils.Sender;

public class DeleteAuctionCommand implements ICommand {
    @Override
    public void execute(Long chatId, String... strings) {
        if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStates.AUCTION)) {
            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_DELETE);
            Sender.getInstance().sendMessageWithKeyboard("Введите название аукциона", chatId, InlineKeyboards.getExitKeyboard());
        } else if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStates.AUCTION_DELETE)) {

            if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(strings[0])) {
                DatabaseUtil.deleteAuctionRecordFromDataBase(strings[0]);
                Sender.getInstance().sendTextMessage("Запись удалена", chatId);
            } else {
                Sender.getInstance().sendTextMessage("Нет такой записи", chatId);
            }
        }
    }

}
