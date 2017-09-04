package Commands;

import Enums.ChatStates;
import Utils.ChatStateHolder;
import Utils.InlineKeyboards;
import Utils.Sender;


public class AuctionCommand implements ICommand {
    @Override
    public void execute(Long chatId, String... strings) {
        Sender
                .getInstance()
                .sendMessageWithKeyboard("Выберите действие", chatId, InlineKeyboards.getAcutionKeyboard());
        ChatStateHolder.getInstance().setChatState(chatId, ChatStates.AUCTION);
    }
}
