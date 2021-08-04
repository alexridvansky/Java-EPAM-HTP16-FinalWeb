package by.spetr.web.model.entity;

import by.spetr.web.model.entity.type.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public class Vehicle extends AbstractEntity implements Serializable {
    private long id;
    private VehicleStateType state;
    private String owner;
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
    private Set<Integer> optionSet = new HashSet<>();
    private LocalDate dateCreated;


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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
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

    public Set<Integer> getOptionSet() {
        return optionSet;
    }

    public void setOptionSet(Set<Integer> optionSet) {
        this.optionSet = optionSet;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle)) return false;

        Vehicle vehicle = (Vehicle) o;

        if (id != vehicle.id) return false;
        if (mileage != vehicle.mileage) return false;
        if (displacement != vehicle.displacement) return false;
        if (power != vehicle.power) return false;
        if (state != vehicle.state) return false;
        if (owner != null ? !owner.equals(vehicle.owner) : vehicle.owner != null) return false;
        if (make != null ? !make.equals(vehicle.make) : vehicle.make != null) return false;
        if (model != null ? !model.equals(vehicle.model) : vehicle.model != null) return false;
        if (modelYear != null ? !modelYear.equals(vehicle.modelYear) : vehicle.modelYear != null) return false;
        if (color != null ? !color.equals(vehicle.color) : vehicle.color != null) return false;
        if (price != null ? !price.equals(vehicle.price) : vehicle.price != null) return false;
        if (powertrain != vehicle.powertrain) return false;
        if (transmission != vehicle.transmission) return false;
        if (drive != vehicle.drive) return false;
        if (optionSet != null ? !optionSet.equals(vehicle.optionSet) : vehicle.optionSet != null) return false;
        return dateCreated != null ? dateCreated.equals(vehicle.dateCreated) : vehicle.dateCreated == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (make != null ? make.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        result = 31 * result + (modelYear != null ? modelYear.hashCode() : 0);
        result = 31 * result + mileage;
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (powertrain != null ? powertrain.hashCode() : 0);
        result = 31 * result + (transmission != null ? transmission.hashCode() : 0);
        result = 31 * result + (drive != null ? drive.hashCode() : 0);
        result = 31 * result + displacement;
        result = 31 * result + power;
        result = 31 * result + (optionSet != null ? optionSet.hashCode() : 0);
        result = 31 * result + (dateCreated != null ? dateCreated.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Vehicle{");
        sb.append("id=").append(id);
        sb.append(", state=").append(state);
        sb.append(", owner='").append(owner).append('\'');
        sb.append(", make='").append(make).append('\'');
        sb.append(", model='").append(model).append('\'');
        sb.append(", modelYear=").append(modelYear);
        sb.append(", mileage=").append(mileage);
        sb.append(", color='").append(color).append('\'');
        sb.append(", price=").append(price);
        sb.append(", powertrain=").append(powertrain);
        sb.append(", transmission=").append(transmission);
        sb.append(", drive=").append(drive);
        sb.append(", displacement=").append(displacement);
        sb.append(", power=").append(power);
        sb.append(", optionSet=").append(optionSet);
        sb.append(", dateCreated=").append(dateCreated);
        sb.append('}');
        return sb.toString();
    }
}
