package Commands;

import Utils.ChatStateHolder;
import Utils.Sender;

/**
 * Created by ALEX on 11.09.2017.
 */
public abstract class AbstractCommand implements ICommand {

    protected final ChatStateHolder stateHolder = ChatStateHolder.getInstance();
    protected final Sender sender = Sender.getInstance();

}
