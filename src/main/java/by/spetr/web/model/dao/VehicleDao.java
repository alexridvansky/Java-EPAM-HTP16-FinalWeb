package by.spetr.web.model.dao;

import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.exception.DaoException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * VehicleDao Interface defines service methods for {@code Vehicle} type objects.
 */
public interface VehicleDao {

    /**
     * Returns list of all vehicles, ordered by vehicleId.
     *
     * @return {@code List<Vehicle>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<Vehicle> findAll() throws DaoException;

    /**
     * Returns list of all vehicles, belong to user given ordered by vehicleId.
     *
     * @param userId userId
     * @return {@code List<Vehicle>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<Vehicle> findByUserId(long userId) throws DaoException;

    /**
     * Returns {@code Optional<Vehicle>} if such was found by vehicleId given.
     *
     * @param id is vehicleId
     * @return {@code Optional<Vehicle>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<Vehicle> findById(long id) throws DaoException;

    /**
     * is used for changing status of given vehicle
     *
     * @param vehicleId userId
     * @param vehicleState new Vehicle.state
     * @return true if vehicle status has been changed successfully
     * @throws DaoException if error occurred on DAO layer
     */
    boolean updateState(long vehicleId, VehicleStateType vehicleState) throws DaoException;

    /**
     * is used for storing Cloudinary public ids in the database
     *
     * @param vehicleId vehicleId
     * @param cloudinaryPublicIds Cloudinary publicId to the file
     * @return boolean if ids were stored successfully
     * @throws DaoException if error occurred on DAO layer
     */
    boolean createPhoto(long vehicleId, Set<String> cloudinaryPublicIds) throws DaoException;

    /**
     * is used to get from the database stored publicId for the preview image
     *
     * @param vehicleId vehicleId
     * @return Cloudinary publicId to preview image
     * @throws DaoException in case of error on DAO layer
     */
    Optional<String> findPreviewById(long vehicleId) throws DaoException;

    /**
     *  is used to obtain List of images' paths (in this case links to images to the Cloudinary service)
     *
     * @param vehicleId vehicle id
     * @return List<String> list of paths to the images
     * @throws DaoException if error occurred on DAO layer
     */
    List<String> findAllPhotoById(long vehicleId) throws DaoException;
}