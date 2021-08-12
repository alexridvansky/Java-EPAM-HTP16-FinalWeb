package by.spetr.web.model.entity;

import by.spetr.web.model.entity.type.VehicleDriveType;
import by.spetr.web.model.entity.type.VehiclePowertrainType;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.entity.type.VehicleTransmissionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public final class VehicleBuilder {
    private long id;
    private VehicleStateType state;
    private long ownerId;
    private VehicleModel model;
    private Year modelYear;
    private int mileage;
    private VehicleColor color;
    private BigDecimal price;
    private VehiclePowertrainType powertrain;
    private VehicleTransmissionType transmission;
    private VehicleDriveType drive;
    private int displacement;
    private int power;
    private String comment = "";
    private LocalDate dateCreated;
    private List<VehicleOption> optionList = new ArrayList<>();

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

    public VehicleBuilder ownerId(long ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public VehicleBuilder model(VehicleModel model) {
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

    public VehicleBuilder color(VehicleColor color) {
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

    public VehicleBuilder optionList(List<VehicleOption> optionList) {
        this.optionList = optionList;
        return this;
    }

    public Vehicle build() {
        Vehicle vehicle = new Vehicle();
        vehicle.setId(id);
        vehicle.setState(state);
        vehicle.setOwnerId(ownerId);
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
        vehicle.setOptionList(optionList);
        return vehicle;
    }
}
