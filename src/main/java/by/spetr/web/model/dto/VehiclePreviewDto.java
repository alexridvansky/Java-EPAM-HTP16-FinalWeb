package by.spetr.web.model.dto;

import by.spetr.web.model.entity.type.VehicleDriveType;
import by.spetr.web.model.entity.type.VehiclePowertrainType;
import by.spetr.web.model.entity.type.VehicleTransmissionType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Year;

public class VehiclePreviewDto implements Serializable {
    private long id;
    private String make;
    private String model;
    private Year modelYear;
    private BigDecimal price;
    private VehiclePowertrainType powertrain;
    private VehicleTransmissionType transmission;
    private VehicleDriveType drive;
    private int displacement;
    private String previewImagePath;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Year getModelYear() {
        return modelYear;
    }

    public void setModelYear(Year modelYear) {
        this.modelYear = modelYear;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public VehiclePowertrainType getPowertrain() {
        return powertrain;
    }

    public void setPowertrain(VehiclePowertrainType powertrain) {
        this.powertrain = powertrain;
    }

    public VehicleTransmissionType getTransmission() {
        return transmission;
    }

    public void setTransmission(VehicleTransmissionType transmission) {
        this.transmission = transmission;
    }

    public VehicleDriveType getDrive() {
        return drive;
    }

    public void setDrive(VehicleDriveType drive) {
        this.drive = drive;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public String getPreviewImagePath() {
        return previewImagePath;
    }

    public void setPreviewImagePath(String previewImagePath) {
        this.previewImagePath = previewImagePath;
    }
}
