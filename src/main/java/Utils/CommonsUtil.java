package Utils;

import Commands.ICommand;
import Enums.CommandEnum;


public class CommonsUtil {

    public static ICommand getCommand(String label) {
        ICommand command = null;
        for (CommandEnum x: CommandEnum.values()){
            if (x.getName().equals(label)){
                command = x.getCommand();
            }
        }
        return command;
    }
    public static boolean isCommand(String label){
        for (CommandEnum x: CommandEnum.values()){
            if (x.getName().equals(label)){
                return true;
            }
        }
        return false;
    }

}
