package by.spetr.web.model.entity.type;

public enum UserStateType {
    CONFIRM(1),
    ENABLED(2),
    BANNED(3),
    DISABLED(4);

    private final int stateId;

    UserStateType(int stateId) {
        this.stateId = stateId;
    }

    public int getStateId() {
        return stateId;
    }
}
