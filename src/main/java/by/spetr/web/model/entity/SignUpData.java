package by.spetr.web.model.entity;

import java.io.Serializable;
import java.util.EnumMap;

public class SignUpData implements Cloneable, Serializable {
    private enum SignUpFields {
        LOGIN,
        PASSWORD,
        PASSWORD_REPEAT,
        EMAIL,
        PHONE,
        COMMENT
    }

    private final EnumMap<SignUpFields, String> regData = new EnumMap<SignUpFields, String>(SignUpFields.class);

    public SignUpData() {
    }

    public SignUpData(String login, String password, String passwordRepeat, String email, String phone) {
        regData.put(SignUpFields.LOGIN, login);
        regData.put(SignUpFields.PASSWORD, password);
        regData.put(SignUpFields.PASSWORD_REPEAT, passwordRepeat);
        regData.put(SignUpFields.EMAIL, email);
        regData.put(SignUpFields.PHONE, phone);
    }

    public String getLogin() {
        return regData.get(SignUpFields.LOGIN);
    }

    public void setLogin(String login) {
        regData.put(SignUpFields.LOGIN, login);
    }

    public String getPassword() {
        return regData.get(SignUpFields.PASSWORD);
    }

    public void setPassword(String password) {
        regData.put(SignUpFields.PASSWORD, password);
    }

    public String getPasswordRepeat() {
        return regData.get(SignUpFields.PASSWORD_REPEAT);
    }

    public void setPasswordRepeat(String passwordRepeat) {
        regData.put(SignUpFields.PASSWORD_REPEAT, passwordRepeat);
    }

    public String getEmail() {
        return regData.get(SignUpFields.EMAIL);
    }

    public void setEmail(String email) {
        regData.put(SignUpFields.EMAIL, email);
    }

    public String getPhone() {
        return regData.get(SignUpFields.PHONE);
    }

    public void setPhone(String phone) {
        regData.put(SignUpFields.PHONE, phone);
    }

    public String getComment() {
        return regData.get(SignUpFields.COMMENT);
    }

    public void setComment(String comment) {
        regData.put(SignUpFields.COMMENT, comment);
    }


}
