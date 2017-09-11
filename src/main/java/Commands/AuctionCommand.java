package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.InlineKeyboards;
import Utils.Sender;


public class AuctionCommand extends AbstractCommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStateEnum.DEFAULT)) {

            sender.sendMessageWithKeyboard(
                    "Выберите действие",
                    chatId,
                    InlineKeyboards.getAcutionKeyboard(role));

            stateHolder.setChatState(chatId,
                    ChatStateEnum.AUCTION,
                    userId);
        }
    }
}
