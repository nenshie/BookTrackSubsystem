package services;

import database.DatabaseBroker;
import database.DatabaseConnection;
import domain.entities.StavkaPredracuna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class StavkaPredracunaService {

    private final DatabaseBroker dbBroker = new DatabaseBroker();

    public List<StavkaPredracuna> getAll() throws SQLException {
        return dbBroker.getAll(new StavkaPredracuna());
    }

    public int add(StavkaPredracuna stavka) throws SQLException {
        return dbBroker.insert(stavka, null);
    }

    public int update(StavkaPredracuna stavka) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {

            Object[] attrs = new Object[]{stavka.getBarkod()};
            java.sql.Struct barkodStruct = conn.createStruct("BARKOD", attrs);


            List<Object> updateParams = java.util.Arrays.asList(
                    stavka.getNaziv(),
                    barkodStruct
            );

            List<Object> whereParams = List.of(stavka.getBrojPredracuna(), stavka.getRb());

            return dbBroker.update(stavka, updateParams, whereParams);
        }
    }

    public int delete(StavkaPredracuna stavka) throws SQLException {
        return dbBroker.delete(stavka);
    }

    public List<StavkaPredracuna> getAllByPredracun(int brojPredracuna) throws SQLException {
        String sql = "SELECT * FROM StavkaPredracuna WHERE brojPredracuna = ? ORDER BY rb";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, brojPredracuna);

            try (ResultSet rs = ps.executeQuery()) {
                StavkaPredracuna s = new StavkaPredracuna();
                return s.getObjectsFromResultSet(rs);
            }
        }
    }
}
