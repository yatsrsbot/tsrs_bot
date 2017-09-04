package Utils;

import Enums.ChatStates;

import java.util.HashMap;
import java.util.Map;

public class ChatStateHolder {
    private Map<Long, ChatStates> chatStates = new HashMap<>();
    private static ChatStateHolder instance;

    private ChatStateHolder() {
    }

    public static ChatStateHolder getInstance() {
        if (instance == null){
            instance = new ChatStateHolder();
        }
        return instance;
    }
    public ChatStates getChatState(long chatId){
        return chatStates.get(chatId);
    }
    public void setChatState(Long chatId,ChatStates state){
        chatStates.put(chatId,state);

    }


}
