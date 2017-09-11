package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.*;

public class ViewAuctionCommand extends AbstractCommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... auctionName) {
        if (stateHolder.getChatState(chatId).equals(ChatStateEnum.AUCTION)) {
            stateHolder.setChatState(chatId, ChatStateEnum.AUCTION_VIEW, userId);

            sender.sendMessageWithKeyboard(
                    "Введите название аукциона",
                    chatId,
                    InlineKeyboards.getExitKeyboard());
        } else if (stateHolder.getChatState(chatId).equals(ChatStateEnum.AUCTION_VIEW)) {
            if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName[0])) {
                String responseMessage = DatabaseUtil.getAuctionRecordsFromDatabase().get(auctionName[0]).toString();
                sender.sendTextMessage(responseMessage, chatId);
            } else {
                sender.sendTextMessage("Нет такой записи", chatId);
            }
        }
    }
}
