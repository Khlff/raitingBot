package commands;


import data.UsersInformation;


public class Create implements Command, CanHaveChatID {
    public static UsersInformation usersInformation;
    private Long chatId;

    public Create(UsersInformation usersInformation) {
        Create.usersInformation = usersInformation;
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
    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    @Override
    public String Execute() {
        if (!UsersInformation.hasWaitingUpdate(chatId))
            usersInformation.update(chatId, true, UsersInformation.hasWaitingRate(chatId));
        return """
                Пришли своё имя и фотокарточку одним сообщением...📝""";
    }
}
