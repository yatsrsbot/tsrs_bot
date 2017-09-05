package Commands;

import Enums.ChatStateEnum;
import Enums.Role;
import Utils.ChatStateHolder;
import Utils.DatabaseUtil;
import Utils.InlineKeyboards;
import Utils.Sender;

import java.util.ArrayList;

public class RequestAccessCommand implements ICommand {
    @Override
    public void execute(Long chatId, Role role, Integer userId, String... strings) {
        if (ChatStateHolder.getInstance().getChatState(chatId).equals(ChatStateEnum.DEFAULT_UNREGISTERED)) {
            ChatStateHolder.getInstance().setChatState(chatId,ChatStateEnum.DEFAULT_ACCESS_REQUESTED,userId);
            ArrayList<Integer> adminsId = DatabaseUtil.getAdminRecordsFromDatabase();
            for (Integer adminId : adminsId) {
                Long adminChatId = ChatStateHolder.getInstance().getChatId(adminId);
                if (!(adminChatId == null)) {
                    Sender.getInstance().sendMessageWithInlineButtons("Пользователь @" +strings[0]+"\nЗапросил доступ\nАвторизовать?", adminChatId, InlineKeyboards.getRequestAccessKeyBoard(userId));
                }
            }
            Sender.getInstance().sendTextMessage("Запрос отправлен", chatId);
        }
    }
}
