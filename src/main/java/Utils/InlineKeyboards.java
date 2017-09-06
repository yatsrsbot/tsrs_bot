package Utils;

import Enums.Role;
import org.telegram.telegrambots.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class InlineKeyboards {
    public static ReplyKeyboardMarkup getDefaultKeyboard(Role role) {

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();


        if (Role.NONE.equals(role)) {
            firstRow.add("Запросить доступ");
            keyboardRows.add(firstRow);
        } else {
            firstRow.add("Работа с данными аукциона");
            keyboardRows.add(firstRow);
            if (Role.ADMIN.equals(role)) {
                KeyboardRow secondRow = new KeyboardRow();
                secondRow.add("Записи пользователей");
                keyboardRows.add(secondRow);
            }
        }

        return createKeyboard(keyboardRows);

    }

    public static ReplyKeyboardMarkup getAcutionKeyboard(Role role) {

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        if (Role.ADMIN.equals(role)) {
            KeyboardRow firstRow = new KeyboardRow();
            firstRow.add("Показать");
            firstRow.add("Добавить");

            KeyboardRow secondRow = new KeyboardRow();
            secondRow.add("Изменить");
            secondRow.add("Удалить");
            secondRow.add("Выход");

            keyboardRows.add(firstRow);
            keyboardRows.add(secondRow);
        } else {
            KeyboardRow firstRow = new KeyboardRow();
            firstRow.add("Показать");
            firstRow.add("Выход");

            keyboardRows.add(firstRow);
        }

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

    public static ReplyKeyboardMarkup getUnregisteredKeyBoard(Role role) {

        List<KeyboardRow> keyboardRows = new ArrayList<>();
        KeyboardRow firstRow = new KeyboardRow();

        firstRow.add("Запросить доступ");

        return createKeyboard(keyboardRows);

    }

    public static InlineKeyboardMarkup getRequestAccessKeyBoard(Integer userId, String userName) {

        InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
        List<InlineKeyboardButton> rowInline = new ArrayList<>();
        rowInline.add(new InlineKeyboardButton().setText("Да").setCallbackData("ACCESS_GRANTED:" + userId + ":" + userName));
        // Set the keyboard to the markup
        rowsInline.add(rowInline);
        // Add it to the message
        markupInline.setKeyboard(rowsInline);
        return markupInline;

    }

    public static ReplyKeyboardMarkup getUsersKeyboard() {

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow firstRow = new KeyboardRow();

        firstRow.add("Добавить права админа");
        firstRow.add("Удалить");
        firstRow.add("Выход");

        keyboardRows.add(firstRow);


        return createKeyboard(keyboardRows);
    }


}