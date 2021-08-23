package by.spetr.web.controller.command;

import by.spetr.web.controller.command.redirect.DefaultCommand;
import by.spetr.web.controller.command.redirect.GoToMainPageCommand;
import by.spetr.web.controller.command.user.ShowSignUpPageCommand;
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
    private final EnumMap<CommandType, Command> commands = new EnumMap<>(CommandType.class);

    CommandProvider() {
        commands.put(DEFAULT, new DefaultCommand());
        commands.put(ADD_NEW_COLOR, new AddNewColorCommand());
        commands.put(ADD_NEW_MAKE, new AddNewMakeCommand());
        commands.put(ADD_NEW_MODEL, new AddNewModelCommand());
        commands.put(ADD_NEW_VEHICLE, new AddNewVehicleCommand());
        commands.put(GO_TO_SIGN_UP_PAGE, new ShowSignUpPageCommand());
        commands.put(GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(GO_TO_RECOVERY_PASSWORD_PAGE, new ShowRecoveryPasswordPageCommand());
        commands.put(SHOW_USER_LIST_ADMIN, new ShowUserListAdminCommand());
        commands.put(SHOW_VEHICLE_INFO, new ShowVehicleInfoCommand());
        commands.put(SHOW_VEHICLE_LIST_ADMIN, new ShowVehicleListAdmCommand());
        commands.put(SHOW_VEHICLE_LIST_MODER, new ShowVehicleListModerCommand());
        commands.put(SHOW_VEHICLE_LIST_PERSONAL, new ShowVehicleListPersonalCommand());
        commands.put(SHOW_VEHICLE_LIST_PUBLIC, new ShowVehicleListPublicCommand());
        commands.put(SHOW_VEHICLE_CREATION_PAGE, new ShowVehicleCreationPageCommand());
        commands.put(SHOW_COLOR_CREATION_PAGE, new ShowColorCreationPageCommand());
        commands.put(SHOW_MAKE_CREATION_PAGE, new ShowMakeCreationPageCommand());
        commands.put(SHOW_MODEL_CREATION_PAGE, new ShowModelCreationPageCommand());
        commands.put(SHOW_CHANGE_PASSWORD_PAGE, new ShowChangePasswordPageCommand());
        commands.put(UPDATE_VEHICLE_ADS, new UpdateVehicleAdsCommand());
        commands.put(UPLOAD_VEHICLE_PHOTO, new UploadVehiclePhotoCommand());
        commands.put(CREATE_USER, new CreateUserCommand());
        commands.put(SIGN_IN, new SignInCommand());
        commands.put(LOG_OUT, new LogOutCommand());
        commands.put(PASSWORD_RESET, new PasswordResetCommand());
        commands.put(PASSWORD_CHANGE, new PasswordChangeCommand());
        commands.put(CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CHANGE_USER_STATE, new ChangeUserStateCommand());
        commands.put(CHANGE_USER_ROLE, new ChangeUserRoleCommand());
        commands.put(CHANGE_VEHICLE_STATE, new UpdateVehicleStateCommand());
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
