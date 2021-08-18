package by.spetr.web.model.dao;

import by.spetr.web.model.entity.AbstractEntity;
import by.spetr.web.model.exception.DaoException;

import java.util.List;
import java.util.Optional;


public abstract class AbstractDao <T extends AbstractEntity> {
    abstract List<T> findAll() throws DaoException;

    abstract Optional<T> findById(long id) throws DaoException;

    String buildPageableQuery(String mainQuery, int pageSize, int pageNumber) {
        int offset = (pageSize * pageNumber) - pageSize;
        StringBuilder queryBuilder = new StringBuilder(mainQuery);
        queryBuilder.append(" LIMIT ");
        if (offset > 0) {
            queryBuilder.append(offset).append(", ");
        }
        queryBuilder.append(pageSize);
        return queryBuilder.toString();
    }
}
