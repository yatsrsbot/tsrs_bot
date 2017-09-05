package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.InlineKeyboards;
import Utils.Sender;


public class AuctionCommand implements ICommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Выберите действие", chatId, InlineKeyboards.getAcutionKeyboard());
        ChatStateHolder.getInstance().setChatState(chatId, ChatStateEnum.AUCTION,userId);
    }
}
