import commands.*;
import data.UsersInformation;
import db.Database;
import db.DatabasePostgreSQL;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            Database database = new DatabasePostgreSQL(
                    "postgres",
                    "KhlffAndI5432",
                    "jdbc:postgresql://localhost:5432/tgbot");

            // DI-container


            // Composite Root
            var repo = new UsersInformation();
            Help helpCommand = new Help();
            Start startCommand = new Start();
            Create createCommand = new Create(repo);
            Rate rateCommand = new Rate(repo);
            var commandList =new Command[]{startCommand, helpCommand, createCommand,rateCommand};;
            helpCommand.setList(commandList);
            //

                botsApi.registerBot(new RatingBot(database, commandList));

        } catch (TelegramApiException | SQLException e) {
            e.printStackTrace();
        }
    }
}