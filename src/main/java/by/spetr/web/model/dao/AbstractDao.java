package by.spetr.web.model.dao;

import by.spetr.web.model.entity.AbstractEntity;
import by.spetr.web.model.exception.DaoException;

import java.util.List;
import java.util.Optional;


public abstract class AbstractDao <T extends AbstractEntity> {
    abstract List<T> findAll() throws DaoException;
    abstract Optional<T> findById(long id) throws DaoException;
}
