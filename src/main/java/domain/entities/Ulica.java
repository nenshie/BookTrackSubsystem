package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Ulica extends DomainObject {
    private int mestoId;
    private int ulicaId;
    private String naziv;

    public Ulica() {
    }

    public Ulica(int mestoId, int ulicaId, String naziv) {
        this.mestoId = mestoId;
        this.ulicaId = ulicaId;
        this.naziv = naziv;
    }

    public int getMestoId() {
        return mestoId;
    }

    public void setMestoId(int mestoId) {
        this.mestoId = mestoId;
    }

    public int getUlicaId() {
        return ulicaId;
    }

    public void setUlicaId(int ulicaId) {
        this.ulicaId = ulicaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String getTableName() {
        return "Ulica";
    }

    @Override
    public String getAllColumnNames() {
        return "mestoId, ulicaId, naziv";
    }

    @Override
    public String getInsertColumnNames() {
        return "mestoId, ulicaId, naziv";
    }

//    @Override
//    public String getColumnValues() {
//        return mestoId + ", " + ulicaId + ", '" + naziv + "'";
//    }
//
//    @Override
//    public String getUpdateClause() {
//        return "naziv = '" + naziv + "'";
//    }
//
//    @Override
//    public String getWhereIdClause() {
//        return "mestoId = " + mestoId + " AND ulicaId = " + ulicaId;
//    }
//
//    @Override
//    public String getUpdateWhereClause() {
//        return getUpdateClause() + " WHERE " + getWhereIdClause();
//    }
//
//    @Override
//    public String getDeleteWhereClause() {
//        return getWhereIdClause();
//    }

    @Override
    public String getInsertPlaceholders() {
        return "?, ?, ?";
    }

    @Override
    public void setInsertParameters(PreparedStatement ps, int startIndex) throws SQLException {

    }

    @Override
    public String getUpdateSetClause() {
        return "";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {

    }

    @Override
    public String getWhereClause() {
        return "";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {

    }

    @Override
    public List<Ulica> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Ulica> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Ulica(
                    rs.getInt("mestoId"),
                    rs.getInt("ulicaId"),
                    rs.getString("naziv")
            ));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "mestoId, ulicaId";
    }
}
