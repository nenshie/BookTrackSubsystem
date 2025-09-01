package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DomainObject {

    public abstract String getTableName();

    public abstract String getAllColumnNames();

    public abstract String getInsertColumnNames();

    public abstract String getInsertPlaceholders();

    public abstract void setInsertParameters(java.sql.PreparedStatement ps, int startIndex) throws SQLException;

    public abstract String getUpdateSetClause();

    public abstract void setUpdateParameters(java.sql.PreparedStatement ps, int startIndex) throws SQLException;

    public abstract String getWhereClause();

    public abstract void setWhereParameters(java.sql.PreparedStatement ps, int startIndex) throws SQLException;

    public abstract List<? extends DomainObject> getObjectsFromResultSet(ResultSet rs) throws SQLException;

    public abstract String getOrderByColumn();
}
