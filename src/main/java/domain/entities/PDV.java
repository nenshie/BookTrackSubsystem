package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PDV extends DomainObject {
    private int sifraPDV;
    private double iznos;

    public PDV() {
    }

    public PDV(int sifraPDV, double iznos) {
        this.sifraPDV = sifraPDV;
        this.iznos = iznos;
    }

    public int getSifraPDV() {
        return sifraPDV;
    }

    public void setSifraPDV(int sifraPDV) {
        this.sifraPDV = sifraPDV;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    @Override
    public String getTableName() {
        return "PDV";
    }

    @Override
    public String getAllColumnNames() {
        return "sifraPDV, iznos";
    }

    @Override
    public String getInsertColumnNames() {
        return "sifraPDV, iznos";
    }

//    @Override
//    public String getColumnValues() {
//        return sifraPDV + ", " + iznos;
//    }
//
//    @Override
//    public String getUpdateClause() {
//        return "iznos = " + iznos;
//    }
//
//    @Override
//    public String getWhereIdClause() {
//        return "sifraPDV = " + sifraPDV;
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
    public List<PDV> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<PDV> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PDV(rs.getInt("sifraPDV"), rs.getDouble("iznos")));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "sifraPDV";
    }
}
