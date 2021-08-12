package by.spetr.web.model.entity.type;

public enum VehiclePowertrainType {
    GASOLINE(1),
    DIESEL(2),
    ELECTRIC(3),
    HYBRID(4);

    private final int powertrainId;

    VehiclePowertrainType(int powertrainId) {
        this.powertrainId = powertrainId;
    }

    public int getPowertrainId() {
        return powertrainId;
    }

    public static VehiclePowertrainType getType(int powertrainId) {
        for (VehiclePowertrainType type : values()) {
            if (type.powertrainId == powertrainId) {
                return type;
            }
        }
        return null;
    }
}
