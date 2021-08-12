package by.spetr.web.model.entity.type;

public enum VehicleTransmissionType {
    MANUAL(1),
    AUTOMATIC(2);

    private final int transmissionId;

    VehicleTransmissionType(int transmissionId) {
        this.transmissionId = transmissionId;
    }

    public int getTransmissionId() {
        return transmissionId;
    }

    public static VehicleTransmissionType getType(int transmissionId) {
        for (VehicleTransmissionType type : values()) {
            if (type.transmissionId == transmissionId) {
                return type;
            }
        }
        return null;
    }
}
