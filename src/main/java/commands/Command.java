package commands;

import java.sql.SQLException;

public interface Command {
    String getHelp();

    boolean isActive(String commandName);

    String Execute() throws SQLException;
}
