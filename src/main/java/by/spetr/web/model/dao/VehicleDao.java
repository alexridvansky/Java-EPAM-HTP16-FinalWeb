package by.spetr.web.model.dao;

import by.spetr.web.model.entity.Vehicle;
import by.spetr.web.model.exception.DaoException;

import java.util.List;

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
}