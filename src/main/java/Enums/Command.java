package Enums;

public enum Command {
    START("/start"),
    AUCTION("Работа с данными аукциона"),
    AUCTION_VIEW("Показать"),
    AUCTION_EDIT("Изменить"),
    AUCTION_DELETE("Удалить"),
    AUCTION_ADD("Добавить"),
    EXIT("Выход");


    private final String name;
    private Command(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}



