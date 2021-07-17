package by.spetr.web.command;

import by.spetr.web.command.redirect.DefaultCommand;
import by.spetr.web.command.redirect.GoToSignUpPageCommand;
import by.spetr.web.command.user.*;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public CommandProvider() {
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.GO_TO_SIGN_UP_PAGE, new GoToSignUpPageCommand());
        commands.put(CommandType.PRINT_ALL_USERS, new PrintAllUsersCommand());
        commands.put(CommandType.CREATE_USER_COMMAND, new CreateUserCommand());
        commands.put(CommandType.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(CommandType.LOG_OUT_COMMAND, new LogOutCommand());
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandType.CHANGE_USER_STATE, new ChangeUserState());
        commands.put(CommandType.CHANGE_USER_ROLE, new ChangeUserRole());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }

        CommandType commandType;

        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }

        return commands.get(commandType);
    }
}
