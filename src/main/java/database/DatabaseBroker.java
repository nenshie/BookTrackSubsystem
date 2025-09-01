package database;

import domain.DomainObject;

import java.sql.*;
import java.util.List;

public class DatabaseBroker {

    public int insert(DomainObject object, List<Object> params) throws SQLException {
        String sql = "INSERT INTO " + object.getTableName() +
                " (" + object.getInsertColumnNames() + ") VALUES (" + object.getInsertPlaceholders() + ")";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            object.setInsertParameters(ps, 1);
            return ps.executeUpdate();
        }
    }

    public <T extends DomainObject> List<T> getAll(T object) throws SQLException {
        String sql = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName() +
                " ORDER BY " + object.getOrderByColumn();

        try (Connection conn = DatabaseConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            return (List<T>) object.getObjectsFromResultSet(rs);
        }
    }

    public int update(DomainObject object, List<Object> paramsForUpdate, List<Object> paramsForWhere) throws SQLException {
        String sql = "UPDATE " + object.getTableName() + " SET " + object.getUpdateSetClause() + " WHERE " + object.getWhereClause();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            int index = 1;
            for (Object param : paramsForUpdate) {
                ps.setObject(index++, param);
            }
            for (Object param : paramsForWhere) {
                ps.setObject(index++, param);
            }
            return ps.executeUpdate();
        }
    }

    public int delete(DomainObject object) throws SQLException {
        String sql = "DELETE FROM " + object.getTableName() + " WHERE " + object.getWhereClause();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            object.setWhereParameters(ps, 1);
            return ps.executeUpdate();
        }
    }

    public <T extends DomainObject> List<T> getByColumn(T object, String columnName, String value) throws SQLException {
        String sql = "SELECT " + object.getAllColumnNames() + " FROM " + object.getTableName() +
                " WHERE " + columnName + " LIKE ? ORDER BY " + object.getOrderByColumn();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, value); // Use wildcards if needed, e.g., "%value%"
            try (ResultSet rs = ps.executeQuery()) {
                return (List<T>) object.getObjectsFromResultSet(rs);
            }
        }
    }

}
