package by.spetr.web.model.form;

import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;

public class UserForm extends DefaultForm {
    private String userName;
    private long userId;
    private UserRoleType role;
    private UserStateType state;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
