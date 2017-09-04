package Utils;

import Enums.Command;

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
        localizedCommands.put("Добавить", "/add");
        localizedCommands.put("Выход", "/exit");
    }

    public static Command getCommand(String label) {
        Command command = null;
        for (Command x: Command.values()){
            if (x.getName().equals(label)){
                command = x;
            }
        }
        return command;
    }
    public static boolean isCommand(String label){
        for (Command x: Command.values()){
            if (x.getName().equals(label)){
                return true;
            }
        }
        return false;
    }

}
