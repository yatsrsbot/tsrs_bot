package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.DatabaseUtil;
import Utils.InlineKeyboards;
import Utils.Sender;

import java.util.ArrayList;

public class RequestAccessCommand extends AbstractCommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (stateHolder.getChatState(chatId).equals(ChatStateEnum.DEFAULT_UNREGISTERED)) {
            stateHolder.setChatState(
                    chatId,
                    ChatStateEnum.DEFAULT_ACCESS_REQUESTED,
                    userId);

            ArrayList<Integer> adminsId = DatabaseUtil.getAdminRecordsFromDatabase();

            for (Integer adminId : adminsId) {
                Long adminChatId = stateHolder.getChatId(adminId);
                if (!(adminChatId == null)) {
                    sender.sendMessageWithKeyboard(
                            "Пользователь @" +strings[0]+"\nЗапросил доступ\nАвторизовать?",
                            adminChatId,
                            InlineKeyboards.getRequestAccessKeyBoard(
                                    userId,
                                    strings[0]));
                }
            }
            sender.sendTextMessage("Запрос отправлен", chatId);
        }
    }
}
