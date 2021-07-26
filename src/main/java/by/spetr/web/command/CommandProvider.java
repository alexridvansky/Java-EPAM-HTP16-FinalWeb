package by.spetr.web.command;

import by.spetr.web.command.redirect.DefaultCommand;
import by.spetr.web.command.redirect.GoToMainPageCommand;
import by.spetr.web.command.redirect.GoToSignUpPageCommand;
import by.spetr.web.command.user.*;
import by.spetr.web.command.vehicle.ShowVehicleAdsCommand;
import by.spetr.web.command.vehicle.ShowVehicleInfoCommand;
import by.spetr.web.command.vehicle.UpdateVehicleAdsCommand;
import by.spetr.web.command.vehicle.UploadVehiclePhotoCommand;

import java.util.EnumMap;

public class CommandProvider {
    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public CommandProvider() {
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.GO_TO_SIGN_UP_PAGE, new GoToSignUpPageCommand());
        commands.put(CommandType.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandType.SHOW_USER_LIST, new ShowUserListCommand());
        commands.put(CommandType.SHOW_VEHICLE_INFO, new ShowVehicleInfoCommand());
        commands.put(CommandType.SHOW_VEHICLE_LIST, new ShowVehicleListCommand());
        commands.put(CommandType.SHOW_VEHICLE_ADS, new ShowVehicleAdsCommand());
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
