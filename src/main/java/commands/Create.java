package commands;


import repository.StockOfTables;

import java.sql.SQLException;

public class Create implements Command, CanHaveChatID {
    private Long chatId;
    protected StockOfTables database;

    public Create(StockOfTables database) {
        this.database = database;
    }

    @Override
    public String getHelp() {
        return "/create - создать/изменить свой профиль";
    }

    @Override
    public boolean isActive(String commandName) {
        return "/create".equals(commandName);
    }

    @Override
    public void setUserId(Long userId) {
        this.chatId = userId;
    }

    @Override
    public String Execute() throws SQLException {
        if (database.users.isUserExists(chatId)) {
            if (!(database.users.getStatusOfWaitingUpdate(chatId)))
                database.users.setStatusOfWaitingUpdate(chatId, true);
        } else {
            database.users.createNewUser(chatId);
            database.users.setStatusOfWaitingUpdate(chatId, true);
        }

        return """
                Пришли своё имя и фотокарточку одним сообщением...📝""";
    }
}