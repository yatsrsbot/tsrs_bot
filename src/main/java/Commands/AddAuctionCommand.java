package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.*;

public class AddAuctionCommand extends AbstractCommand {

    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (Role.ADMIN.equals(role)) {

            ChatStateEnum chatState = stateHolder.getChatState(chatId);

            if (chatState.equals(ChatStateEnum.AUCTION)) {

                stateHolder.setChatState(chatId, ChatStateEnum.AUCTION_ADD, userId);

                sender.sendMessageWithKeyboard(
                        "Введите название аукциона и значение в виде Аукицон/Значение",
                        chatId,
                        InlineKeyboards.getExitKeyboard());

            } else if (chatState.equals(ChatStateEnum.AUCTION_ADD)) {
                String auctionName;
                Integer value;
                try {
                    String[] data = strings[0].split("/");
                    auctionName = data[0].toLowerCase();
                    value = Integer.parseInt(data[1]);
                    if (DatabaseUtil.getAuctionRecordsFromDatabase().containsKey(auctionName)) {
                        sender.sendTextMessage(
                                "Эта запись существует, попробуйте команду \"Изменить\"",
                                chatId);
                    } else {
                        DatabaseUtil.insertAuctionRecordsIntoDatabase(auctionName, value);
                        sender.sendTextMessage("Запись добавлена", chatId);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    sender.sendTextMessage("Неверный формат", chatId);
                }
            }
        }
    }
}




