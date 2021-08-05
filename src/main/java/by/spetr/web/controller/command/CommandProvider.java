package by.spetr.web.controller.command;

import by.spetr.web.controller.command.redirect.DefaultCommand;
import by.spetr.web.controller.command.redirect.GoToMainPageCommand;
import by.spetr.web.controller.command.redirect.GoToSignUpPageCommand;
import by.spetr.web.controller.command.vehicle.ShowVehicleCreationPageCommand;
import by.spetr.web.controller.command.user.*;
import by.spetr.web.controller.command.vehicle.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.EnumMap;

import static by.spetr.web.controller.command.CommandType.*;

public class CommandProvider {
    private static final Logger logger = LogManager.getLogger();
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public CommandProvider() {
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.GET_MAKE_MODEL_LIST, new GetMakeModelListCommand());
        commands.put(CommandType.GO_TO_SIGN_UP_PAGE, new GoToSignUpPageCommand());
        commands.put(CommandType.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandType.SHOW_USER_LIST_ADMIN, new ShowUserListCommand());
        commands.put(CommandType.SHOW_VEHICLE_INFO, new ShowVehicleInfoCommand());
        commands.put(CommandType.SHOW_VEHICLE_LIST, new ShowVehicleListCommand());
        commands.put(CommandType.SHOW_VEHICLE_LIST_ADMIN, new ShowVehicleListAdmCommand());
        commands.put(CommandType.SHOW_VEHICLE_CREATION_PAGE, new ShowVehicleCreationPageCommand());
        commands.put(SHOW_MAKE_MODEL_CREATION_PAGE, new ShowMakeModelCreationPageCommand());
        commands.put(CommandType.UPDATE_VEHICLE_ADS, new UpdateVehicleAdsCommand());
        commands.put(CommandType.UPLOAD_VEHICLE_PHOTO, new UploadVehiclePhotoCommand());
        commands.put(CommandType.CREATE_USER, new CreateUserCommand());
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.LOG_OUT, new LogOutCommand());
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandType.CHANGE_USER_STATE, new ChangeUserStateCommand());
        commands.put(CommandType.CHANGE_USER_ROLE, new ChangeUserRoleCommand());
        commands.put(CommandType.CHANGE_VEHICLE_STATE, new ChangeVehicleStateCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            logger.error("Command is null");
            return commands.get(CommandType.DEFAULT);
        }

        CommandType commandType;

        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            logger.error("Command '{}' not found. Redirecting to default command", commandName);
            commandType = CommandType.DEFAULT;
        }

        return commands.get(commandType);
    }
}
