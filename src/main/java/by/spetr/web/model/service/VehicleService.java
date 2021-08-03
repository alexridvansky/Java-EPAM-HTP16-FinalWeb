package by.spetr.web.model.service;

import by.spetr.web.model.dto.VehiclePreviewDto;
import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.exception.ServiceException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    List<Vehicle> getAdminVehicleList() throws ServiceException;

    /**
     * Returns list of all vehicles with preview image paths or empty List<Vehicle> if there's no ads in the database.
     *
     * @return {@code List<VehiclePreviewDto>}
     * @throws ServiceException if no data been received from DAO layer
     */
    List<VehiclePreviewDto> getPublicVehicleList() throws ServiceException;

    /**
     * Returns list of all vehicles belong to particular user by userId given or
     * empty List<Vehicle> if there's no such ads in the database.
     *
     * @param userId userId
     * @return {@code List<Vehicle>}
     * @throws ServiceException if no data been received from DAO layer
     */
    List<Vehicle> getVehicleListByUserId(long userId) throws ServiceException;

    /**
     * Returns Optional<vehicle> by vehicleId given.
     *
     * @param vehicleId vehicleId
     * @return {@code Optional<Vehicle>}
     * @throws ServiceException if no data been received from DAO layer
     */
    Optional<Vehicle> getVehicleById(long vehicleId) throws ServiceException;

    /**
     * is used for updating user status by username.
     *
     * @param vehicleId id of the vehicle
     * @param vehicleState new vehicle state to be changed to
     * @return true if status been changed successfully
     * @throws ServiceException when error occurred on DAO layer
     */
    boolean updateVehicleState(long vehicleId, VehicleStateType vehicleState) throws ServiceException;

    /**
     * is used to adding photos to vehicle album.
     *
     * @param vehicleId vehicleId
     * @param filenames file names
     * @return true if photo added successfully
     * @throws ServiceException in case of errors on DAO layer or Cloudinary service
     */
    boolean uploadVehiclePhoto(long vehicleId, Set<String> filenames) throws ServiceException;

    /**
     * is used to get from the database publicId of stored picture and getting the path to it
     *
     * @param vehicleId vehicleId
     * @return preview image path
     * @throws ServiceException in case of errors on DAO layer or Cloudinary service
     */
    Optional<String> getPreviewImageById(long vehicleId) throws ServiceException;

    /**
     *  is used to obtain List of paths to gallery images
     *
     * @param vehicleId vehicleId
     * @return List<String> of paths
     * @throws ServiceException in case of errors on DAO layer or Cloudinary service
     */
    List<String> getAlbumById(long vehicleId) throws ServiceException;
}
