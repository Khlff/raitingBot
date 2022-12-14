import commands.*;
import repository.StockOfTables;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

import java.sql.SQLException;


public class BotApp {
    Command[] commandList;

    private final static String defaultAnswer = "Команда не найдена. Введите /help для просмотра команд.";

    StockOfTables database;

    BotApp(StockOfTables database, Command[] commands) {
        this.database = database;
        this.commandList = commands;
    }

    public String commandHandler(String inputMessage, Long chatID) throws SQLException {
        for (var iterCommand : commandList) {
            if (iterCommand.isActive(inputMessage)) {
                if (iterCommand instanceof CanHaveChatID chatIDSetter)
                    chatIDSetter.setUserId(chatID);

                return iterCommand.Execute();
            }
        }
        return defaultAnswer;
    }

    public String setInformation(Long chatID, String name, PhotoSize photo) throws SQLException {
        if (photo == null) return "\uD83D\uDD34Ошибка фото";
        var photoId = photo.getFileId();

        if (name.length() > 20)
            return "\uD83D\uDD34Имя не может быть таким длинным";

        database.users.setStatusOfWaitingUpdate(chatID, false);
        database.users.updateUserInformation(chatID, name, photoId);
        return "Профиль изменён✅";
    }
}
