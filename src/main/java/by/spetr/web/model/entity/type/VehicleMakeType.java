package by.spetr.web.model.entity.type;

public enum VehicleMakeType {
    AUDI(1),
    BMW(2),
    BENTLEY(3),
    DAEWOO(4),
    FORD(5),
    GEELY(6),
    HOVER(7);

    private final int makeId;

    VehicleMakeType(int makeId) {
        this.makeId = makeId;
    }

    public int getMakeId() {
        return makeId;
    }
}
