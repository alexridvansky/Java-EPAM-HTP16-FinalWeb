package by.spetr.web.model.entity;

public class VehicleMake {
    int makeId;
    String value;

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

        VehicleMake that = (VehicleMake) o;

        if (makeId != that.makeId) return false;
        return value != null ? value.equals(that.value) : that.value == null;
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
