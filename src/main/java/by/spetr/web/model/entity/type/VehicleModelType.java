package by.spetr.web.model.entity.type;

public enum VehicleModelType {
    A1(1, VehicleMakeType.AUDI.getMakeId()),
    SERIES1(2,VehicleMakeType.BMW.getMakeId()),
    SERIES2(3,VehicleMakeType.BMW.getMakeId());

    private final int modelId;
    private final int makeId;

    VehicleModelType(int modelId, int makeId) {
        this.modelId = modelId;
        this.makeId = makeId;
    }

    public int getModelId() {
        return modelId;
    }

    public int getMakeId() {
        return makeId;
    }
}
