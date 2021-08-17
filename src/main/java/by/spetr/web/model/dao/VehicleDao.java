package by.spetr.web.model.dao;

import by.spetr.web.model.entity.*;
import by.spetr.web.model.entity.type.VehicleStateType;
import by.spetr.web.model.exception.DaoException;

import java.util.*;

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
     * Returns {@code Optional<Vehicle>} if such was found by vehicleId given.
     *
     * @param id is vehicleId
     * @return {@code Optional<Vehicle>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<Vehicle> findById(long id) throws DaoException;

    /**
     * Returns list of all public vehicles, ordered by vehicleId.
     *  (i.e. allowed to be shown publicly, it means that user and vehicle states should be ENABLED)
     *
     * @return {@code List<Vehicle>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<Vehicle> findAllPublic() throws DaoException;

    /**
     * Returns list of all vehicles, belong to user given ordered by vehicleId.
     *
     * @param userId userId
     * @return {@code List<Vehicle>}
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<Vehicle> findByUserId(long userId) throws DaoException;

    /**
     * Returns the number of all vehicles, belong to user given (currently active and not) ordered by vehicleId.
     *
     * @param userId id of the User
     * @return number of adverts for the whole time
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    int findAdNumberByUserId(long userId) throws DaoException;

    /**
     * is used to check it out whether given make entry already in the db
     *
     * @param make Vehicle.make name
     * @return true if make name given already in the db
     */
    boolean isMakeExist(String make) throws DaoException;

    /**
     * is used to check it out whether given makeId entry already in the db
     *
     * @param makeId Vehicle.makeId name
     * @return true if makeId given already in the db
     */
    boolean isMakeExist(int makeId) throws DaoException;

    /**
     * is used to get the full list of Vehicle makes
     *
     * @return List<VehicleMake> of Vehicle makes
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<VehicleMake> findMakeList() throws DaoException;

    /**
     * is used to insert new entry of Vehicle.make
     *
     * @param make new Vehicle.make to be added to
     * @return true in case of successful adding new value
     * @throws DaoException in case of error adding new entry
     */
    boolean insertMake(String make) throws DaoException;

    /**
     * is used to check it out whether given model entry already in the db
     *
     * @param model Vehicle.model name
     * @return true if make name given already in the db
     */
    boolean isModelExist(String model) throws DaoException;

    /**
     * is used to check it out whether given modelId presented in the db
     *
     * @param modelId Vehicle.modelId
     * @return true if makeId given already in the db
     */
    boolean isModelExist(int modelId) throws DaoException;

    /**
     * is used to check it out whether given powertrainId presented in the db
     *
     * @param powertrainId Vehicle.powertrainId
     * @return true if powertrainId given already in the db
     */
    boolean isPowertrainExist(int powertrainId) throws DaoException;

    /**
     * is used to check it out whether given transmissionId presented in the db
     *
     * @param transmissionId Vehicle.transmissionId
     * @return true if powertrainId given already in the db
     */
    boolean isTransmissionExist(int transmissionId) throws DaoException;

    /**
     * is used to check it out whether given driveId presented in the db
     *
     * @param driveId Vehicle.driveId
     * @return true if powertrainId given already in the db
     */
    boolean isDriveExist(int driveId) throws DaoException;

    /**
     * is used to check it out whether color name with colorId given is presented in the db
     *
     * @param colorId Vehicle.colorId
     * @return true if powertrainId given already in the db
     */
    boolean isColorExist(int colorId) throws DaoException;

    /**
     * is used to check it out whether color name is presented in the db
     *
     * @param color Vehicle.colorId
     * @return true if powertrainId given already in the db
     */
    boolean isColorExist(String color) throws DaoException;

    /**
     * is used to get Vehicle.color by colorId given
     *
     * @param colorId Vehicle.colorId
     * @return true if powertrainId given already in the db
     */
    Optional<VehicleColor> findColorById(int colorId) throws DaoException;

    /**
     * is used to insert new entry of Vehicle.make
     *
     * @param color new Vehicle.color type to be added to
     * @return VehicleColor of new Vehicle.color type just created
     * @throws DaoException in case of error adding new entry
     */
    VehicleColor insertColor(String color) throws DaoException;

    /**
     * is used to get the full list of Vehicle models
     *
     * @return List<VehicleModel> of Vehicle models
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<VehicleModel> findModelList() throws DaoException;

    /**
     * is used to insert new entry of Vehicle.model
     *
     * @param model new Vehicle.model to be added to
     * @param makeId makeId Vehicle.model belongs to
     * @return true in case of successful adding new value
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    boolean insertModel(String model, int makeId) throws DaoException;

    /**
     * is used to insert new entry of Vehicle into db
     *
     * @param vehicle instance of Vehicle class
     * @return Vehicle created with Vehicle.id
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Vehicle insertVehicle(Vehicle vehicle) throws DaoException;

    /**
     * is used for changing status of given vehicle
     *
     * @param vehicleId userId
     * @param vehicleState new Vehicle.state
     * @return true if vehicle status has been changed successfully
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    boolean updateState(long vehicleId, VehicleStateType vehicleState) throws DaoException;

    /**
     * is used for storing Cloudinary public ids in the database
     *
     * @param vehicleId vehicleId
     * @param cloudinaryPublicIds Cloudinary publicId to the file
     * @return boolean if ids were stored successfully
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    boolean createPhoto(long vehicleId, Set<String> cloudinaryPublicIds) throws DaoException;

    /**
     * is used to get from the database stored publicId for the preview image
     *
     * @param vehicleId vehicleId
     * @return Cloudinary publicId to preview image
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    Optional<String> findPreviewById(long vehicleId) throws DaoException;

    /**
     *  is used to obtain List of images' paths (in this case links to images to the Cloudinary service)
     *
     * @param vehicleId vehicle id
     * @return List<String> list of paths to the images
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<String> findAllPhotoById(long vehicleId) throws DaoException;

    /**
     * is used to get full List of possible Vehicle.Option
     *
     * @return List<VehicleOption>
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<VehicleOption> findAllOption() throws DaoException;

    /**
     * method is used to renew options set for a specific vehicle
     *
     * @param vehicleId id of the affected vehicle
     * @return List of options
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<VehicleOption> reSetOptionList(long vehicleId) throws DaoException;

    /**
     * is used to get the full List of possible values for Vehicle.Color
     *
     * @return List<VehicleColor> of values Vehicle.Color
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<VehicleColor> findAllColors() throws DaoException;

    /**
     *  is used to get the list of options with vehicleId given
     *
     * @param vehicleId vehicleId
     * @return List<VehicleOption> of options for vehicle given
     * @throws DaoException if connection can't be obtained or no access to the DataBase
     */
    List<VehicleOption> findOptionByVehicleId(long vehicleId) throws DaoException;
}