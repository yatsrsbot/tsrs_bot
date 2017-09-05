package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.*;

public class ViewAuctionCommand implements ICommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... auctionName) {
        if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStateEnum.AUCTION)) {
            ChatStateHolder.getInstance().setChatState(chatId, ChatStateEnum.AUCTION_VIEW,userId);
            Sender.getInstance().sendMessageWithKeyboard("Введите название аукциона", chatId, InlineKeyboards.getExitKeyboard());
        } else if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStateEnum.AUCTION_VIEW)) {
            if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName[0])) {
                String responseMessage = DatabaseUtil.getAuctionRecordsFromDatabase().get(auctionName[0]).toString();
                Sender.getInstance().sendTextMessage(responseMessage, chatId);
            } else {
                Sender.getInstance().sendTextMessage("Нет такой записи", chatId);
            }
        }
    }
}
