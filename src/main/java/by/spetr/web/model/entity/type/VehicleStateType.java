package by.spetr.web.model.entity.type;

public enum VehicleStateType {
    MODERATION(1),
    ENABLED(2),
    DISABLED(3),
    ARCHIVED(4);

    private final int stateId;

    VehicleStateType (int stateId) {
        this.stateId = stateId;
    }

    public int getStateId() {
        return stateId;
    }
}
