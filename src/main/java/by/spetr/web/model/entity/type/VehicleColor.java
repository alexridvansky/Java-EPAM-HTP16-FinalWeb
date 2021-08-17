package by.spetr.web.model.entity.type;

import java.io.Serializable;

public class VehicleColor implements Serializable {
    private int colorId;
    private String value;

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
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
        if (!(o instanceof VehicleColor)) return false;

        VehicleColor vehicleColor = (VehicleColor) o;

        if (colorId != vehicleColor.colorId) return false;
        return value != null ? value.equals(vehicleColor.value) : vehicleColor.value == null;
    }

    @Override
    public int hashCode() {
        int result = colorId;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VehicleColor{");
        sb.append("colorId=").append(colorId);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
