package by.spetr.web.model.form;

import java.io.Serializable;
import java.util.EnumMap;

public class UserRegistrationForm implements  Serializable {
    private final EnumMap<RegDataType, String> regData = new EnumMap<>(RegDataType.class);

    private enum RegDataType {
        LOGIN,
        PASSWORD,
        PASSWORD_REPEAT,
        EMAIL,
        PHONE,
        COMMENT
    }

    public UserRegistrationForm(String login, String password, String passwordRepeat, String email, String phone) {
        regData.put(RegDataType.LOGIN, login);
        regData.put(RegDataType.PASSWORD, password);
        regData.put(RegDataType.PASSWORD_REPEAT, passwordRepeat);
        regData.put(RegDataType.EMAIL, email);
        regData.put(RegDataType.PHONE, phone);
        regData.put(RegDataType.COMMENT, "");
    }

    public void appendComment(String text){
        String comment = regData.get(RegDataType.COMMENT);
        if (comment.isBlank()) {  // the first message on the same line
            comment = text;

        } else {                  // any other on a new line
            comment += "<br/>" + text;
        }
        regData.put(RegDataType.COMMENT, comment);
    }

    public String getLogin() {
        return regData.get(RegDataType.LOGIN);
    }

    public void setLogin(String login) {
        regData.put(RegDataType.LOGIN, login);
    }

    public String getPassword() {
        return regData.get(RegDataType.PASSWORD);
    }

    public void setPassword(String password) {
        regData.put(RegDataType.PASSWORD, password);
    }

    public String getPasswordRepeat() {
        return regData.get(RegDataType.PASSWORD_REPEAT);
    }

    public void setPasswordRepeat(String passwordRepeat) {
        regData.put(RegDataType.PASSWORD_REPEAT, passwordRepeat);
    }

    public String getEmail() {
        return regData.get(RegDataType.EMAIL);
    }

    public void setEmail(String email) {
        regData.put(RegDataType.EMAIL, email);
    }

    public String getPhone() {
        return regData.get(RegDataType.PHONE);
    }

    public void setPhone(String phone) {
        regData.put(RegDataType.PHONE, phone);
    }

    public String getComment() {
        return regData.get(RegDataType.COMMENT);
    }

    public void setComment(String comment) {
        regData.put(RegDataType.COMMENT, comment);
    }


}
