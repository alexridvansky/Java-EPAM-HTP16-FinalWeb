package by.spetr.web.model.dto;

import by.spetr.web.model.entity.type.VehicleOption;
import by.spetr.web.model.entity.type.VehicleDriveType;
import by.spetr.web.model.entity.type.VehiclePowertrainType;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.entity.type.VehicleTransmissionType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.List;

public class VehicleFullDto implements Serializable {
    private List<String> album;
    private List<VehicleOption> optionList;
    private long id;
    private VehicleStateType state;
    private long ownerId;
    private String owner;
    private String ownerPhone;
    private String make;
    private String model;
    private Year modelYear;
    private int mileage;
    private String color;
    private BigDecimal price;
    private VehiclePowertrainType powertrain;
    private VehicleTransmissionType transmission;
    private VehicleDriveType drive;
    private int displacement;
    private int power;
    private String comment = "";
    private LocalDate dateCreated;

    public List<String> getAlbum() {
        return album;
    }

    public void setAlbum(List<String> album) {
        this.album = album;
    }

    public List<VehicleOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<VehicleOption> optionList) {
        this.optionList = optionList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VehicleStateType getState() {
        return state;
    }

    public void setState(VehicleStateType state) {
        this.state = state;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
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

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }
}
