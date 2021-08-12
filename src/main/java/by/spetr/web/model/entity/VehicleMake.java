package by.spetr.web.model.entity;

import java.io.Serializable;

public class VehicleMake extends AbstractEntity implements Serializable {
    private int makeId;
    private String value;

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleMake)) return false;

        VehicleMake vehicleMake = (VehicleMake) o;

        if (makeId != vehicleMake.makeId) return false;
        return value != null ? value.equals(vehicleMake.value) : vehicleMake.value == null;
    }

    @Override
    public int hashCode() {
        int result = makeId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VehicleMake{");
        sb.append("id=").append(makeId);
        sb.append(", make='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
