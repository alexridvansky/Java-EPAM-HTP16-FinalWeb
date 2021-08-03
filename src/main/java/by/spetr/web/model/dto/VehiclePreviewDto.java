package by.spetr.web.model.dto;

import java.io.Serializable;

public class VehiclePreviewDto implements Serializable {
    private VehicleDto vehicleDto;
    private String previewImagePath;

    public VehicleDto getVehicleDto() {
        return vehicleDto;
    }

    public void setVehicleDto(VehicleDto vehicleDto) {
        this.vehicleDto = vehicleDto;
    }

    public String getPreviewImagePath() {
        return previewImagePath;
    }

    public void setPreviewImagePath(String previewImagePath) {
        this.previewImagePath = previewImagePath;
    }
}
