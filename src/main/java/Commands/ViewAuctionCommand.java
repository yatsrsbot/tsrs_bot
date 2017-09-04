package Commands;

import Enums.ChatStates;
import Utils.*;

public class ViewAuctionCommand implements ICommand {
    @Override
    public void execute(Long chatId, String... auctionName) {
        if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStates.AUCTION)) {
            ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_VIEW);
            Sender.getInstance().sendMessageWithKeyboard("Введите название аукциона", chatId, InlineKeyboards.getExitKeyboard());
        } else if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStates.AUCTION_VIEW)) {
            if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName[0])) {
                String responseMessage = DatabaseUtil.getAuctionRecordsFromDatabase().get(auctionName[0]).toString();
                Sender.getInstance().sendTextMessage(responseMessage, chatId);
                ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION_VIEW);
            } else {
                Sender.getInstance().sendTextMessage("Нет такой записи", chatId);
            }
        }
    }
}
