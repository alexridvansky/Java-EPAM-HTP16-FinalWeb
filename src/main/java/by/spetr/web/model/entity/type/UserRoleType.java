package by.spetr.web.model.entity.type;

public enum UserRoleType {
    ROOT(1),
    MODERATOR(2),
    USER(3),
    GUEST(4);

    private final int roleId;

    UserRoleType(int roleId) {
        this.roleId = roleId;
    }

    public int getRoleId() {
        return roleId;
    }
}
