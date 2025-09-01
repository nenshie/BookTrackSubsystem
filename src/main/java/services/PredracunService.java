package services;

import database.DatabaseConnection;
import domain.entities.Predracun;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PredracunService extends BaseService<Predracun> {

    public PredracunService() {
        super(new Predracun());
    }

    public int addPredracun(Predracun predracun) throws SQLException {
        List<Object> params = List.of(
                predracun.getBrojPredracuna(),
                predracun.getDatumOd(),
                predracun.getOdgovorniRadnik(),
                predracun.getRadnikKreirao(),
                predracun.getDobavljac(),
                predracun.getUlica(),
                predracun.getMesto(),
                predracun.getBroj()
        );
        return add(predracun, params);
    }

    public int updatePredracun(Predracun predracun) throws SQLException {
        List<Object> paramsForUpdate = List.of(
                predracun.getDatumOd(),
                predracun.getOdgovorniRadnik(),
                predracun.getRadnikKreirao(),
                predracun.getDobavljac(),
                predracun.getUlica(),
                predracun.getMesto(),
                predracun.getBroj()
        );
        List<Object> paramsForWhere = List.of(
                predracun.getBrojPredracuna()
        );

        return update(predracun, paramsForUpdate, paramsForWhere);
    }

    public int deletePredracun(Predracun predracun) throws SQLException {
        return delete(predracun);
    }

    public List<Predracun> getAllPredracuni() throws SQLException {
        return getAll();
    }

    public List<Predracun> getAllByPartition(String partitionName) throws SQLException {
        String sql = "SELECT * FROM PREDRACUN PARTITION (" + partitionName + ")";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            return new Predracun().getObjectsFromResultSet(rs);
        }
    }

    public List<String> getAllPartitions() throws SQLException {
        List<String> partitions = new ArrayList<>();
        String sql = "SELECT PARTITION_NAME FROM USER_TAB_PARTITIONS WHERE TABLE_NAME = 'PREDRACUN' ORDER BY PARTITION_POSITION";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                partitions.add(rs.getString("PARTITION_NAME"));
            }
        }
        return partitions;
    }
}
