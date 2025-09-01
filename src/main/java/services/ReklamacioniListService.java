package services;

import database.DatabaseBroker;
import database.DatabaseConnection;
import domain.entities.ReklamacioniList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ReklamacioniListService {

    private final DatabaseBroker dbBroker = new DatabaseBroker();

    public List<ReklamacioniList> getAll() throws SQLException {
        return dbBroker.getAll(new ReklamacioniList());
    }

    public int add(ReklamacioniList list) throws SQLException {
        return dbBroker.insert(list, null);
    }

    public int update(ReklamacioniList list) throws SQLException {
        String sql = "UPDATE " + list.getTableName() +
                " SET " + list.getUpdateSetClause() +
                " WHERE " + list.getWhereClause();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            list.setUpdateParameters(ps, 1);

            int whereIndex = list.getUpdateSetClause().split(",").length + 1;
            list.setWhereParameters(ps, whereIndex);

            return ps.executeUpdate();
        }
    }

    public int delete(ReklamacioniList list) throws SQLException {
        return dbBroker.delete(list);
    }

    public String proveriDatum(int reklamacioniListId) throws SQLException {
        String sql = "SELECT proveri_datum_rl(?) FROM dual";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, reklamacioniListId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString(1);
                } else {
                    return "Ne postoji reklamacioni list sa tim ID";
                }
            }
        }
    }
}
