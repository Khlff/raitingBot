package commands;

import repository.StockOfTables;

import java.sql.SQLException;

public class Rate implements Command, CanHaveChatID {

    StockOfTables database;
    private Long chatID;

    public Rate(StockOfTables database) {
        this.database = database;
    }

    @Override
    public String getHelp() {
        return "/rate - начать оценивать";
    }

    @Override
    public boolean isActive(String commandName) {
        return "/rate".equals(commandName);
    }

    @Override

    public String Execute() throws SQLException {
        if (database.users.isUserExists(chatID)) {
            if (!database.users.getStatusOfRating(chatID))
                database.users.setStatusOfRating(chatID, true);
            return "Если хочешь остановить оценивание напиши Стоп";
        } else {
            return "Сначала создай свой профиль командой /create";
        }
    }

    @Override
    public void setUserId(Long userId) {
        this.chatID = userId;
    }
}
