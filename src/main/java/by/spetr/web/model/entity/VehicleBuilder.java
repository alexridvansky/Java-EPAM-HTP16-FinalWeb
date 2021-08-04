package by.spetr.web.model.entity;

import by.spetr.web.model.entity.type.VehicleDriveType;
import by.spetr.web.model.entity.type.VehiclePowertrainType;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.entity.type.VehicleTransmissionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

public final class VehicleBuilder {
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
    private String comment = "";
    private LocalDate dateCreated;
    private Set<Integer> optionSet = new HashSet<>();

    private VehicleBuilder() {
    }

    public static VehicleBuilder getInstance() {
        return new VehicleBuilder();
    }

    public VehicleBuilder id(long id) {
        this.id = id;
        return this;
    }

    public VehicleBuilder state(VehicleStateType state) {
        this.state = state;
        return this;
    }

    public VehicleBuilder owner(String owner) {
        this.owner = owner;
        return this;
    }

    public VehicleBuilder make(String make) {
        this.make = make;
        return this;
    }

    public VehicleBuilder model(String model) {
        this.model = model;
        return this;
    }

    public VehicleBuilder modelYear(Year modelYear) {
        this.modelYear = modelYear;
        return this;
    }

    public VehicleBuilder mileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public VehicleBuilder color(String color) {
        this.color = color;
        return this;
    }

    public VehicleBuilder price(BigDecimal price) {
        this.price = price;
        return this;
    }

    public VehicleBuilder powertrain(VehiclePowertrainType powertrain) {
        this.powertrain = powertrain;
        return this;
    }

    public VehicleBuilder transmission(VehicleTransmissionType transmission) {
        this.transmission = transmission;
        return this;
    }

    public VehicleBuilder drive(VehicleDriveType drive) {
        this.drive = drive;
        return this;
    }

    public VehicleBuilder displacement(int displacement) {
        this.displacement = displacement;
        return this;
    }

    public VehicleBuilder power(int power) {
        this.power = power;
        return this;
    }

    public VehicleBuilder comment(String comment) {
        this.comment = comment;
        return this;
    }

    public VehicleBuilder dateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public VehicleBuilder optionSet(Set<Integer> optionSet) {
        this.optionSet = optionSet;
        return this;
    }

    public Vehicle build() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setState(state);
        vehicle.setOwner(owner);
        vehicle.setMake(make);
        vehicle.setModel(model);
        vehicle.setModelYear(modelYear);
        vehicle.setMileage(mileage);
        vehicle.setColor(color);
        vehicle.setPrice(price);
        vehicle.setPowertrain(powertrain);
        vehicle.setTransmission(transmission);
        vehicle.setDrive(drive);
        vehicle.setDisplacement(displacement);
        vehicle.setPower(power);
        vehicle.setComment(comment);
        vehicle.setDateCreated(dateCreated);
        vehicle.setOptionSet(optionSet);
        return vehicle;
    }
}
