package by.spetr.web.model.service;

import by.spetr.web.model.dto.VehicleFullDto;
import by.spetr.web.model.dto.VehiclePreviewDto;
import by.spetr.web.model.entity.*;
import by.spetr.web.model.entity.type.*;
import by.spetr.web.model.exception.ServiceException;
import by.spetr.web.model.form.VehicleFullForm;
import by.spetr.web.model.form.VehicleShortForm;

import java.util.List;
import java.util.Optional;

/**
 * declare service methods for {@code Vehicle.class}
 */
public interface VehicleService {

    /**
     * Returns list of all vehicles or empty List<Vehicle> if there's no ads in the database.
     *
     * @return {@code List<Vehicle>}
     * @throws ServiceException if no data been received from DAO layer
     */
    List<VehicleFullDto> getFullDtoVehicleList() throws ServiceException;

    /**
     * Returns Public list of all vehicles or empty List<Vehicle> if there's no ads in the database.
     * @param pageSize number of entries on each page
     * @param pageNumber number of a page
     * @return {@code List<VehiclePreviewDto>}
     * @throws ServiceException if no data been received from DAO layer
     */
    List<VehiclePreviewDto> getPublicVehicleList(int pageSize, int pageNumber) throws ServiceException;

    /**
     * Returns Moderators' list of all vehicles or empty List<Vehicle> if there's no ads in the database.
     * @param pageSize number of entries on each page
     * @param pageNumber number of a page
     * @return {@code List<VehiclePreviewDto>}
     * @throws ServiceException if no data been received from DAO layer
     */
    List<VehiclePreviewDto> getModeratorVehicleList(int pageSize, int pageNumber) throws ServiceException;

    /**
     * Returns number of all vehicles to be publicly shown (active ads within active users)
     * (is used mostly for pagination)
     *
     * @return number of public ads
     * @throws ServiceException if no data been received from DAO layer
     */
    int getPublicVehicleListSize() throws ServiceException;

    /**
     * Returns number of all vehicles to be shown at moderators' page
     * (is used mostly for pagination)
     *
     * @return number of public ads
     * @throws ServiceException if no data been received from DAO layer
     */
    int getModeratorVehicleListSize() throws ServiceException;

    /**
     * Returns list of all vehicles belong to particular user by userId given or
     * empty List<Vehicle> if there's no such ads in the database.
     *
     * @param userId userId
     * @return {@code List<Vehicle>}
     * @throws ServiceException if no data been received from DAO layer
     */
    List<VehiclePreviewDto> getPersonalVehicleList(long userId) throws ServiceException;

    /**
     * Returns number of all vehicles belong to particular user (currently active and not) by userId given
     *
     * @param userId userId
     * @return int number of adverts
     * @throws ServiceException if no data been received from DAO layer
     */
    int getVehicleCountByUserId(long userId) throws ServiceException;

    /**
     * Returns Optional<vehicle> by vehicleId given.
     *
     * @param vehicleId vehicleId
     * @return {@code Optional<Vehicle>}
     * @throws ServiceException if no data been received from DAO layer
     */
    Optional<Vehicle> getVehicleById(long vehicleId) throws ServiceException;

    /**
     * is used to get the full list of Vehicle makes
     *
     * @return List<VehicleMake> of Vehicle makes
     * @throws ServiceException if no data been received from DAO layer
     */
    List<VehicleMake> getMakeList() throws ServiceException;

    /**
     * is used to insert new entry of Vehicle.make
     *
     * @param form VehicleShortForm contains new Vehicle.make name
     * @throws ServiceException in case of error on Dao layer
     */
    void addMake(VehicleShortForm form) throws ServiceException;

    /**
     * is used to get the full list of Vehicle models
     *
     * @return List<VehicleModel> of Vehicle models
     * @throws ServiceException if no data been received from DAO layer
     */
    List<VehicleModel> getModelList() throws ServiceException;

    /**
     * is used to insert new entry of Vehicle.model
     *
     * @param form VehicleShortForm contains new Vehicle.makeId and Vehicle.model name
     * @throws ServiceException in case of error on Dao layer
     */
    void addModel(VehicleShortForm form) throws ServiceException;

    /**
     * is used to insert new entry of Vehicle
     *
     * @param form VehicleFullForm contains new Vehicle
     * @return Vehicle created
     * @throws ServiceException in case of error on Dao layer
     */
    Vehicle addVehicle(VehicleFullForm form) throws ServiceException;

    /**
     * is used for updating user status by username.
     *
     * @param form VehicleFullForm contains new Vehicle.state and Vehicle.id
     * @return true if status been changed successfully
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean updateVehicleState(VehicleFullForm form) throws ServiceException;

    /**
     * is used to adding photos to vehicle album.
     *
     * @param form VehicleFullForm contains vehicleId and Set<filePath>
     * @return true if photo added successfully
     * @throws ServiceException in case of errors on DAO layer or Cloudinary service
     */
    boolean uploadVehiclePhoto(VehicleFullForm form) throws ServiceException;

    /**
     * is used to get from the database publicId of stored picture and getting the path to it
     *
     * @param vehicleId vehicleId
     * @return preview image path
     * @throws ServiceException in case of errors on DAO layer or Cloudinary service
     */
    Optional<String> getPreviewImageById(long vehicleId) throws ServiceException;

    /**
     * is used to get all fields vehicle description, including list of photos and Map of options with descriptions.
     *
     * @param vehicleId vehicleId
     * @return VehicleFullDto
     * @throws ServiceException in case of errors on DAO layer
     */
    Optional<VehicleFullDto> getFullDtoVehicleById(long vehicleId) throws ServiceException;

    /**
     * is used to obtain List of paths to gallery images
     *
     * @param vehicleId vehicleId
     * @return List<String> of paths to the album images
     * @throws ServiceException in case of errors on DAO layer
     */
    List<String> getAlbumById(long vehicleId) throws ServiceException;

    /**
     * is used to get the full List of possible Vehicle.Option
     *
     * @return List<VehicleOption>
     * @throws ServiceException in case of errors on DAO layer
     */
    List<VehicleOption> getOptionList() throws ServiceException;

    /**
     * is used to get the full List of possible values for Powertrain.type
     *
     * @return List<VehiclePowertrainType> of values Powertrain.type
     * @throws ServiceException in case of errors on DAO layer
     */
    List<VehiclePowertrainType> getAllPowertrainTypeList() throws ServiceException;

    /**
     * is used to get the full List of possible values for Transmission.type
     *
     * @return List<VehicleTransmissionType> of values Transmission.type
     * @throws ServiceException in case of errors on DAO layer
     */
    List<VehicleTransmissionType> getAllTransmissionTypeList() throws ServiceException;

    /**
     * is used to get the full List of possible values for Drive.type
     *
     * @return List<VehicleDriveType> of values Drive.type
     * @throws ServiceException in case of errors on DAO layer
     */
    List<VehicleDriveType> getAllDriveTypeList() throws ServiceException;

    /**
     * is used to get the full List of possible values for Vehicle.Color
     *
     * @return List<VehicleColor> of values Vehicle.Color
     * @throws ServiceException in case of errors on DAO layer
     */
    List<VehicleColor> getAllColorList() throws ServiceException;

    /**
     * is used to insert new entry of Vehicle.color
     *
     * @param form VehicleShortForm contains new Vehicle.color name
     * @return VehicleColor type of Vehicle.color just created
     * @throws ServiceException in case of error on Dao layer
     */
    VehicleColor addColor(VehicleShortForm form) throws ServiceException;

    static VehicleService getInstance() {
        return DefaultVehicleService.getInstance();
    }
}
