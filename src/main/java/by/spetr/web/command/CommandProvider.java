package by.spetr.web.command;

import by.spetr.web.command.list.*;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public CommandProvider() {
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.GO_TO_SIGN_UP_PAGE, new GoToSignUpPageCommand());
        commands.put(CommandType.PRINT_OUT_ALL_USERS, new PrintOutAllUsersCommand());
        commands.put(CommandType.GO_TO_SIGN_IN_RESULT_PAGE, new GoToSignInResultPageCommand());
        commands.put(CommandType.GO_TO_LOGIN_ERROR_PAGE, new GoToLoginErrorPageCommand());
        commands.put(CommandType.GO_TO_SIGN_UP_RESULT_PAGE, new GoToSignUpResultPageCommand());
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
