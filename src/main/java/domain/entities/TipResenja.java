package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TipResenja extends DomainObject {
    private int sifraTipaResenja;
    private String naziv;

    public TipResenja() {}

    public TipResenja(int sifraTipaResenja, String naziv) {
        this.sifraTipaResenja = sifraTipaResenja;
        this.naziv = naziv;
    }

    public int getSifraTipaResenja() {
        return sifraTipaResenja;
    }

    public void setSifraTipaResenja(int sifraTipaResenja) {
        this.sifraTipaResenja = sifraTipaResenja;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String getTableName() {
        return "TIPRESENJA";
    }

    @Override
    public String getAllColumnNames() {
        return "sifraTipaResenja, naziv";
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
        ps.setInt(startIndex, sifraTipaResenja);
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
        return "sifraTipaResenja = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, sifraTipaResenja);
    }

    @Override
    public List<TipResenja> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<TipResenja> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new TipResenja(
                    rs.getInt("sifraTipaResenja"),
                    rs.getString("naziv")
            ));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "sifraTipaResenja";
    }
}
