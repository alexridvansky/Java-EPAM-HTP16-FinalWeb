package by.spetr.web.model.entity.type;

public enum VehicleModelType {
    SERIES1(1,VehicleMakeType.BMW.getMakeId()),
    SERIES2(2,VehicleMakeType.BMW.getMakeId());

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
