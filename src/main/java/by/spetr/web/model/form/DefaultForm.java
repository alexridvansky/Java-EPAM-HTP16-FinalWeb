package by.spetr.web.model.form;

import by.spetr.web.controller.command.CommandType;
import by.spetr.web.model.dto.UserDto;

public class DefaultForm {
    private boolean success;
    private String feedbackMsg;
    private UserDto executor;
    private CommandType requestedCommand;

    public String getFeedbackMsg() {
        return feedbackMsg;
    }

    public void setFeedbackMsg(String feedbackMsg) {
        this.feedbackMsg = feedbackMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public UserDto getExecutor() {
        return executor;
    }

    public void setExecutor(UserDto executor) {
        this.executor = executor;
    }

    public CommandType getRequestedCommand() {
        return requestedCommand;
    }

    public void setRequestedCommand(CommandType requestedCommand) {
        this.requestedCommand = requestedCommand;
    }
}
