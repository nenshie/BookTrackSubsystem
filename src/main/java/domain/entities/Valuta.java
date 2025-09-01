package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Valuta extends DomainObject {
    private int valutaId;
    private String naziv;

    public Valuta() {
    }

    public Valuta(int valutaId, String naziv) {
        this.valutaId = valutaId;
        this.naziv = naziv;
    }

    public int getValutaId() {
        return valutaId;
    }

    public void setValutaId(int valutaId) {
        this.valutaId = valutaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String getTableName() {
        return "Valuta";
    }

    @Override
    public String getAllColumnNames() {
        return "valutaId, naziv";
    }

    @Override
    public String getInsertColumnNames() {
        return "valutaId, naziv";
    }

//    @Override
//    public String getColumnValues() {
//        return valutaId + ", '" + naziv + "'";
//    }
//
//    @Override
//    public String getUpdateClause() {
//        return "naziv = '" + naziv + "'";
//    }
//
//    @Override
//    public String getWhereIdClause() {
//        return "valutaId = " + valutaId;
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
        return "?, ?";
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
    public List<Valuta> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Valuta> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Valuta(rs.getInt("valutaId"), rs.getString("naziv")));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "valutaId";
    }
}
