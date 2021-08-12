package by.spetr.web.model.form;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class VehicleFullForm extends DefaultForm {
    private long ownerId;
    private int makeId;
    private int modelId;
    private int powertrainId;
    private int transmissionId;
    private int driveId;
    private int colorId;
    private Year modelYear;
    private int displacement;
    private int mileage;
    private int power;
    private int price;
    private List<Long> optionSet = new ArrayList<>();
    private String comment;

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public int getMakeId() {
        return makeId;
    }

    public void setMakeId(int makeId) {
        this.makeId = makeId;
    }

    public int getModelId() {
        return modelId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public int getPowertrainId() {
        return powertrainId;
    }

    public void setPowertrainId(int powertrainId) {
        this.powertrainId = powertrainId;
    }

    public int getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(int transmissionId) {
        this.transmissionId = transmissionId;
    }

    public int getDriveId() {
        return driveId;
    }

    public void setDriveId(int driveId) {
        this.driveId = driveId;
    }

    public int getColorId() {
        return colorId;
    }

    public void setColorId(int colorId) {
        this.colorId = colorId;
    }

    public Year getModelYear() {
        return modelYear;
    }

    public void setModelYear(Year modelYear) {
        this.modelYear = modelYear;
    }

    public int getDisplacement() {
        return displacement;
    }

    public void setDisplacement(int displacement) {
        this.displacement = displacement;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Long> getOptionSet() {
        return optionSet;
    }

    public void setOptionSet(List<Long> optionSet) {
        this.optionSet = optionSet;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}