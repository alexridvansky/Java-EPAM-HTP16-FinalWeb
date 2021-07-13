package by.spetr.web.model.entity;

import by.spetr.web.model.entity.type.UserRoleType;
import by.spetr.web.model.entity.type.UserStateType;

import java.io.Serializable;
import java.time.LocalDate;

public class User extends AbstractEntity implements Cloneable, Serializable {
    private long userId;
    private String login;
    private UserRoleType role;
    private UserStateType state;
    private String email;
    private String phone;
    private LocalDate regDate;


    public User(String login, UserRoleType role, UserStateType state, String email, String phone, LocalDate regDate) {
        this.login = login;
        this.role = role;
        this.state = state;
        this.email = email;
        this.phone = phone;
        this.regDate = regDate;
    }

    public User(long userId, String login, UserRoleType role, UserStateType state, String email, String phone, LocalDate regDate) {
        this.userId = userId;
        this.login = login;
        this.role = role;
        this.state = state;
        this.email = email;
        this.phone = phone;
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "id: " + userId
                + ", login: " + login
                + ", role: " + role
                + ", state: " + state
                + ", email: " + email
                + ", phone: " + phone
                + ", regdate: " + regDate;
    }

    public long getUserId() {
        return userId;
    }

    public String getLogin() {
        return login;
    }

    public UserRoleType getRole() {
        return role;
    }

    public UserStateType getState() {
        return state;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getRegDate() {
        return regDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (login != null ? !login.equals(user.login) : user.login != null) return false;
        if (role != user.role) return false;
        if (state != user.state) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return regDate != null ? regDate.equals(user.regDate) : user.regDate == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (userId ^ (userId >>> 32));
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (regDate != null ? regDate.hashCode() : 0);
        return result;
    }
}
