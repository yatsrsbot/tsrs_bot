import Utils.Sender;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

public class Main {
    public static void main(String[] args) {
        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            tsrs_bot bot = new tsrs_bot();
            botsApi.registerBot(bot);
            Sender.getInstance().setBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}