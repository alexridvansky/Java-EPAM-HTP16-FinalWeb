package by.spetr.web.model.entity;

import java.io.Serializable;
import java.util.EnumMap;

public class SignUpData implements Cloneable, Serializable {
    private enum RegFields {
        LOGIN,
        PASSWORD,
        PASSWORD_REPEAT,
        EMAIL,
        PHONE,
        COMMENT
    }

    private final EnumMap<RegFields, String> regData = new EnumMap<RegFields, String>(RegFields.class);

    public SignUpData() {
    }

    public SignUpData(String login, String password, String passwordRepeat, String email, String phone) {
        regData.put(RegFields.LOGIN, login);
        regData.put(RegFields.PASSWORD, password);
        regData.put(RegFields.PASSWORD_REPEAT, passwordRepeat);
        regData.put(RegFields.EMAIL, email);
        regData.put(RegFields.PHONE, phone);
    }

    public String getLogin() {
        return regData.get(RegFields.LOGIN);
    }

    public void setLogin(String login) {
        regData.put(RegFields.LOGIN, login);
    }

    public String getPassword() {
        return regData.get(RegFields.PASSWORD);
    }

    public void setPassword(String password) {
        regData.put(RegFields.PASSWORD, password);
    }

    public String getPasswordRepeat() {
        return regData.get(RegFields.PASSWORD_REPEAT);
    }

    public void setPasswordRepeat(String passwordRepeat) {
        regData.put(RegFields.PASSWORD_REPEAT, passwordRepeat);
    }

    public String getEmail() {
        return regData.get(RegFields.EMAIL);
    }

    public void setEmail(String email) {
        regData.put(RegFields.EMAIL, email);
    }

    public String getPhone() {
        return regData.get(RegFields.PHONE);
    }

    public void setPhone(String phone) {
        regData.put(RegFields.PHONE, phone);
    }

    public String getComment() {
        return regData.get(RegFields.COMMENT);
    }

    public void setComment(String comment) {
        regData.put(RegFields.COMMENT, comment);
    }


}
