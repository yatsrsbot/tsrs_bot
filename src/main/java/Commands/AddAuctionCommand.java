package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.*;

public class AddAuctionCommand implements ICommand {

    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        ChatStateEnum chatState = ChatStateHolder.getInstance().getChatState(chatId);
        if (chatState.equals(ChatStateEnum.AUCTION)) {
            ChatStateHolder.getInstance().setChatState(chatId, ChatStateEnum.AUCTION_ADD, userId);
            Sender
                    .getInstance()
                    .sendMessageWithKeyboard("Введите название аукциона и значение в виде Аукицон/Значение", chatId, InlineKeyboards.getExitKeyboard());
        } else if (chatState.equals(ChatStateEnum.AUCTION_ADD)) {
            String auctionName;
            Integer value;
            try {
                String[] data = strings[0].split("/");
                auctionName = data[0].toLowerCase();
                value = Integer.parseInt(data[1]);
                if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)) {
                    Sender.getInstance().sendTextMessage("Эта запись существует, попробуйте команду \"Изменить\"", chatId);
                } else {
                    DatabaseUtil.insertAuctionRecordsIntoDatabase(auctionName, value);
                    Sender.getInstance().sendTextMessage("Запись добавлена", chatId);

                }
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                Sender.getInstance().sendTextMessage("Неверный формат", chatId);
            }
        }
    }
}




