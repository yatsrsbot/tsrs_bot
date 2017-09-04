package Utils;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboards {
    public static ReplyKeyboardMarkup getDefaultKeyboard() {

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Работа с данными аукциона");
        keyboardRows.add(firstRow);

        return createKeyboard(keyboardRows);

    }

    public static ReplyKeyboardMarkup getAcutionKeyboard() {

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Показать");
        firstRow.add("Добавить");

        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add("Изменить");
        secondRow.add("Удалить");
        secondRow.add("Выход");

        keyboardRows.add(firstRow);
        keyboardRows.add(secondRow);

        return createKeyboard(keyboardRows);
    }
    public static ReplyKeyboardMarkup getExitKeyboard() {

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Выход");
        keyboardRows.add(firstRow);

        return createKeyboard(keyboardRows);
    }

    private static ReplyKeyboardMarkup createKeyboard(List<KeyboardRow> keyboardRows) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setKeyboard(keyboardRows);

        return replyKeyboardMarkup;
    }

}