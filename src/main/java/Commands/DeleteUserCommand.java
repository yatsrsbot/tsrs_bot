package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.*;

public class DeleteUserCommand extends AbstractCommand {

    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (Role.ADMIN.equals(role)) {
            if (stateHolder.getChatState(chatId).equals(ChatStateEnum.USERS)) {
                stateHolder.setChatState(
                        chatId,
                        ChatStateEnum.USERS_DELETE,
                        userId);

                sender.sendMessageWithKeyboard(
                        "Введите имя пользователя",
                        chatId,
                        InlineKeyboards.getExitKeyboard());
            } else if (stateHolder.getChatState(chatId).equals(ChatStateEnum.USERS_DELETE)) {

                if (UserHolder.getInstance().containsUserName(strings[0])) {
                    if (UserHolder.getInstance().getUserIdbyName(strings[0]).equals(userId)) {
                        sender.sendTextMessage("Нельзя удалить собственную запись", chatId);
                    } else {
                        DatabaseUtil.deleteAuctionRecordFromDataBase(strings[0]);
                        sender.sendTextMessage("Запись удалена", chatId);
                    }


                } else {
                    sender.sendTextMessage("Нет такой записи", chatId);
                }


            }
        }
    }
}


