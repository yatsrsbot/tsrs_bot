package Commands;

import Enums.Role;

public interface ICommand {
    public void execute(Long chatId, Role role, Integer userId, String... strings);
}
