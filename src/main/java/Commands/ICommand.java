package Commands;

public interface ICommand {
    public void execute(Long chatId,String ...strings);
}
