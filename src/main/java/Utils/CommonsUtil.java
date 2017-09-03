package Utils;

import java.util.HashMap;
import java.util.Map;

public class CommonsUtil {

    public static String capitalize(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You can add other chars here
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    private static final Map<String, String> localizedCommands;

    static {
        localizedCommands = new HashMap<>();
        localizedCommands.put("/start", "/start");
        localizedCommands.put("Работа с данными аукциона", "/auction");
        localizedCommands.put("Показать", "/view");
        localizedCommands.put("Изменить", "/update");
        localizedCommands.put("Удалить", "/delete");
        localizedCommands.put("/put", "/put");
        localizedCommands.put("/add", "/add");
        localizedCommands.put("Выход", "/exit");
    }

    public static String getCommand(String label) {
        return localizedCommands.get(label);
    }
    public static boolean isCommand(String label){
        return localizedCommands.containsKey(label);
    }

}
