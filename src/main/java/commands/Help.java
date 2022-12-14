package commands;

public class Help implements Command, CanHaveCommandList {
    @Override
    public String getHelp() {
        return "/help - вызов справки";
    }

    @Override
    public boolean isActive(String commandName) {
        return commandName.equals("/help");
    }


    private Command[] commandList;

    @Override
    public void setList(Command[] commands) {
        this.commandList = commands;
    }

    @Override
    public String Execute() {
        StringBuilder result = new StringBuilder().append("Ты можешь управлять мной, используя эти команды:\n\n");
        if (this.commandList != null) {
            for (var i : this.commandList) {
                result.append(i.getHelp()).append('\n');
            }
            return result.toString();
        }
        return null;
    }
}
