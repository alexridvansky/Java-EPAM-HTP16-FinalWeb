package by.spetr.web.model.dto;

import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;

public class UserDto {
    private long userId;
    private String login;
    private UserRoleType role;
    private UserStateType state;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserRoleType getRole() {
        return role;
    }

    public void setRole(UserRoleType role) {
        this.role = role;
    }

    public UserStateType getState() {
        return state;
    }

    public void setState(UserStateType state) {
        this.state = state;
    }
}
