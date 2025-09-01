package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Mesto extends DomainObject {
    private int mestoId;
    private String naziv;
    private String ptt;

    public Mesto() {
    }

    public Mesto(int mestoId, String naziv, String ptt) {
        this.mestoId = mestoId;
        this.naziv = naziv;
        this.ptt = ptt;
    }

    public int getMestoId() {
        return mestoId;
    }

    public void setMestoId(int mestoId) {
        this.mestoId = mestoId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getPtt() {
        return ptt;
    }

    public void setPtt(String ptt) {
        this.ptt = ptt;
    }

    @Override
    public String getTableName() {
        return "Mesto";
    }

    @Override
    public String getAllColumnNames() {
        return "mestoId, naziv, ptt";
    }

    @Override
    public String getInsertColumnNames() {
        return "mestoId, naziv, ptt";
    }

//    @Override
//    public String getColumnValues() {
//        return mestoId + ", '" + naziv + "', '" + ptt + "'";
//    }
//
//    @Override
//    public String getUpdateClause() {
//        return "naziv = '" + naziv + "', ptt = '" + ptt + "'";
//    }
//
//    @Override
//    public String getWhereIdClause() {
//        return "mestoId = " + mestoId;
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
    public List<Mesto> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Mesto> mesta = new ArrayList<>();
        while (rs.next()) {
            mesta.add(new Mesto(
                    rs.getInt("mestoId"),
                    rs.getString("naziv"),
                    rs.getString("ptt")
            ));
        }
        return mesta;
    }

    @Override
    public String getOrderByColumn() {
        return "mestoId";
    }
}
