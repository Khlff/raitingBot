import commands.Command;
import data.UsersInformation;
import db.Database;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class RatingBot extends TelegramLongPollingBot {
    private final String botToken = System.getenv("BOT_TOKEN");
    private final String botName = System.getenv("BOT_NAME");

    BotApp botApplication;

    private final ResultSet resultSetOfDB;

    private boolean resultSetOfDBIsEmpty = false;

    public RatingBot(Database database, Command[] commands) {
        this.botApplication = new BotApp(database, commands);
        this.resultSetOfDB = botApplication.database.getUsersTable();
    }


    @Override
    public void onUpdateReceived(Update update) {
        var message = new SendMessage();
        Long chatID = Long.valueOf(update.getMessage().getChatId().toString());
        message.setChatId(chatID);
        
        System.out.printf("Update from user: %s, message text: %s\n", chatID, update.getMessage().getText());

        /* TODO: Логика чет хромает, помимо миллионов обёрток в try catch миллион повторяющихся строк кода
            Нужно оптимизировать и убрать всё ненужное, например двойной вызов методо update.getMessage.getText()
         */
        if (UsersInformation.hasWaitingUpdate(chatID)) {
            if (update.getMessage().hasPhoto() && update.getMessage().getCaption() != null) {
                List<PhotoSize> photos = update.getMessage().getPhoto();
                PhotoSize maxPhoto = photos.stream().max(Comparator.comparing(PhotoSize::getFileSize)).orElse(null);

                String answer;
                try {
                    answer = botApplication.setInformation(chatID, update.getMessage().getCaption(), maxPhoto);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                message.setText(answer);
            } else message.setText("\uD83D\uDD34Пришли своё имя и фотокарточку одним сообщением!");

        } else if (update.hasMessage() && update.getMessage().hasText()) {
            message.setText(botApplication.commandHandler(update.getMessage().getText(), chatID));
        }


        if (UsersInformation.hasWaitingRate(chatID) && update.hasMessage() && update.getMessage().hasText()) {
            String userInput = update.getMessage().getText();
            boolean rateIsValid = true;
            if (!"/rate".equals(userInput))
                if (Objects.equals(update.getMessage().getText(), "/stop")) {
                    UsersInformation.updateStatusOfRate(chatID, false);
                    message.setText("Оценка фотографий остановлена");
                } else if (userInput.matches("\\d+") && Integer.parseInt(userInput) <= 10 && Integer.parseInt(userInput) > 0) {
                    message.setText("Если захотите прекратить оценку - /stop");
                } else {
                    message.setText("Вам нужно оценить фотографию (1-10)\nИли напишите /stop, чтобы остановить оценку");
                    rateIsValid = false;
                }
            final SendPhoto sendPhotoRequest = new SendPhoto();
            sendPhotoRequest.setChatId(String.valueOf(chatID));
            // Здесь посылаем фотографию из базы данных
            try {
                if (rateIsValid) {
                    if (resultSetOfDB.next()) {
                        sendPhotoRequest.setPhoto(new InputFile(resultSetOfDB.getString("photo_id")));
                        sendPhotoRequest.setCaption(resultSetOfDB.getString("username"));
                    } else {
                        UsersInformation.updateStatusOfRate(chatID, false);
                        resultSetOfDBIsEmpty = true;
                        message.setText("Больше нет фотографий для оценивания, оценивание фотографий остановлено");
                    }
                }
                else {
                    sendPhotoRequest.setPhoto(new InputFile(resultSetOfDB.getString("photo_id")));
                    sendPhotoRequest.setCaption(resultSetOfDB.getString("username"));
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            // TODO: Придумать, как убрать этот костыль
            if (!resultSetOfDBIsEmpty) {
                try {
                    execute(sendPhotoRequest);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
        // TODO: хочется видеть message над sendPhoto
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }


    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}
