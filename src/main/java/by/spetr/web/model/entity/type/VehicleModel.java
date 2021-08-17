package by.spetr.web.model.entity.type;

import java.io.Serializable;

public class VehicleModel implements Serializable {
    private int modelId;
    private VehicleMake make;
    private String value;

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public VehicleMake getMake() {
        return make;
    }

    public void setMake(VehicleMake make) {
        this.make = make;
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
        if (!(o instanceof VehicleModel)) return false;

        VehicleModel vehicleModel = (VehicleModel) o;

        if (modelId != vehicleModel.modelId) return false;
        if (make != null ? !make.equals(vehicleModel.make) : vehicleModel.make != null) return false;
        return value != null ? value.equals(vehicleModel.value) : vehicleModel.value == null;
    }

    @Override
    public int hashCode() {
        int result = modelId;
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("VehicleModel{");
        sb.append("modelId=").append(modelId);
        sb.append(", make=").append(make);
        sb.append(", value='").append(value).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
