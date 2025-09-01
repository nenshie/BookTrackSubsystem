package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Magacin extends DomainObject {

    private int magacinId;
    private String naziv;

    public Magacin() {}

    public Magacin(int magacinId, String naziv) {
        this.magacinId = magacinId;
        this.naziv = naziv;
    }

    public int getMagacinId() {
        return magacinId;
    }

    public void setMagacinId(int magacinId) {
        this.magacinId = magacinId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String getTableName() {
        return "MAGACIN";
    }

    @Override
    public String getAllColumnNames() {
        return "magacinId, naziv";
    }

    @Override
    public String getInsertColumnNames() {
        return getAllColumnNames();
    }

    @Override
    public String getInsertPlaceholders() {
        return "?, ?";
    }

    @Override
    public void setInsertParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, magacinId);
        ps.setString(startIndex + 1, naziv);
    }

    @Override
    public String getUpdateSetClause() {
        return "naziv = ?";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setString(startIndex, naziv);
    }

    @Override
    public String getWhereClause() {
        return "magacinId = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, magacinId);
    }

    @Override
    public List<Magacin> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Magacin> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Magacin(
                    rs.getInt("magacinId"),
                    rs.getString("naziv")
            ));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "magacinId";
    }

    @Override
    public String toString() {
        return naziv;
    }
}
