package commands;

public interface Command {
    String getHelp();

    boolean isActive(String commandName);

    String Execute();
}
