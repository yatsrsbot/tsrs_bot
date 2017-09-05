package Enums;

import Commands.*;

public enum CommandEnum {
    START("/start", new StartCommand()),
    AUCTION("Работа с данными аукциона", new AuctionCommand()),
    AUCTION_VIEW("Показать", new ViewAuctionCommand()),
    AUCTION_EDIT("Изменить", new UpdateAuctionCommand()),
    AUCTION_DELETE("Удалить", new DeleteAuctionCommand()),
    AUCTION_ADD("Добавить", new AddAuctionCommand()),
    EXIT("Выход", new ExitCommand());
    //REQUEST_ACCESS("Запросить доступ",new DefaultCommand());

    private final String name;
    private final ICommand command;
    CommandEnum(String name, ICommand command) {
        this.name = name;
        this.command =command;
    }

    public String getName() {
        return name;
    }


    public ICommand getCommand() {
        return command;
    }
}


