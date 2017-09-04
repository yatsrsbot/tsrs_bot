package Utils;

import Enums.Command;


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
