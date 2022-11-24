package commands;

import data.UsersInformation;

public class Rate implements Command, CanHaveChatID{

    //Мы должны выдавать случайные картинки из базы данных и отправлять пользователю,
    //он ставит оценку от 1/10, считываем оценку, записываем в бд, присылаем новую картинку.
    //Так до тех пор пока пользователь не введёт выход

    public static UsersInformation usersInformation;
    private Long chatId;

    public Rate(UsersInformation usersInformation) {
        Rate.usersInformation = usersInformation;
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
    public String Execute() {
        if (!UsersInformation.hasWaitingRate(chatId))
            usersInformation.update(chatId, UsersInformation.hasWaitingUpdate(chatId),true);
    return "Оцените следующие фотографии:";
    }

    @Override
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }
}
