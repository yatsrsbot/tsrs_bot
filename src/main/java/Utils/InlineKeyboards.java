package Utils;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboards {
    public static ReplyKeyboardMarkup getDefaultKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyobardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Работа с данными аукциона");
        keyobardRows.add(firstRow);
        replyKeyboardMarkup.setKeyboard(keyobardRows);
        return replyKeyboardMarkup;

    }

    public static ReplyKeyboardMarkup getAcutionKeyboard() {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(false);
        replyKeyboardMarkup.setResizeKeyboard(true);
        List<KeyboardRow> keyobardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();
        firstRow.add("Показать");
        firstRow.add("Добавить");
        KeyboardRow secondRow = new KeyboardRow();
        secondRow.add("Изменить");
        secondRow.add("Удалить");
        secondRow.add("Выход");
        keyobardRows.add(firstRow);
        keyobardRows.add(secondRow);
        replyKeyboardMarkup.setKeyboard(keyobardRows);
        return replyKeyboardMarkup;
    }

}
