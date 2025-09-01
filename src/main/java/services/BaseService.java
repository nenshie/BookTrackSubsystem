package services;

import database.DatabaseBroker;
import domain.DomainObject;

import java.sql.SQLException;
import java.util.List;

public abstract class BaseService<T extends DomainObject> {
    protected DatabaseBroker dbBroker;
    protected T entityInstance;

    public BaseService(T entityInstance) {
        this.dbBroker = new DatabaseBroker();
        this.entityInstance = entityInstance;
    }

    public int add(T entity, List<Object> params) throws SQLException {
        return dbBroker.insert(entity, params);
    }

    public int update(T entity, List<Object> paramsForUpdate, List<Object> paramsForWhere) throws SQLException {
        return dbBroker.update(entity, paramsForUpdate, paramsForWhere);
    }

    public int delete(T entity) throws SQLException {
        return dbBroker.delete(entity);
    }

    public List<T> getAll() throws SQLException {
        return dbBroker.getAll(entityInstance);
    }
}

