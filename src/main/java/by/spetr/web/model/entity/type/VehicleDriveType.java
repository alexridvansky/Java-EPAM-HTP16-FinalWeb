package by.spetr.web.model.entity.type;

public enum VehicleDriveType {
    FWD(1),
    RWD(2),
    AWD(3);

    private final int driveId;

    VehicleDriveType(int driveId) {
        this.driveId = driveId;
    }

    public int getDriveId() {
        return driveId;
    }

    public static VehicleDriveType getId(int driveId) {
        for (VehicleDriveType type : values()) {
            if (type.driveId == driveId) {
                return type;
            }
        }
        return null;
    }
}
