package Utils;

import Enums.ChatStateEnum;

import java.util.HashMap;
import java.util.Map;

public class ChatStateHolder {
    private Map<Long, UserState> chatStates = new HashMap<>();
    private static ChatStateHolder instance;

    private ChatStateHolder() {
    }

    private class UserState {
        Integer userId;
        ChatStateEnum chatState;

        private UserState(Integer userId, ChatStateEnum chatState) {
            this.userId = userId;
            this.chatState = chatState;
        }



    }

    public static ChatStateHolder getInstance() {
        if (instance == null) {
            instance = new ChatStateHolder();
        }
        return instance;
    }

    public ChatStateEnum getChatState(long chatId) {
        return chatStates.get(chatId).chatState;
    }

    public void setChatState(Long chatId, ChatStateEnum state, Integer userId) {
        chatStates.put(chatId, new UserState(userId,state));

    }
    public Integer getUserId(Long chatId){
        return chatStates.get(chatId).userId;
    }


}
