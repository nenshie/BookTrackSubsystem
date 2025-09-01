package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Adresa extends DomainObject {
    private int mesto;
    private int ulica;
    private int broj;

    public Adresa() {
    }

    public Adresa(int mesto, int ulica, int broj) {
        this.mesto = mesto;
        this.ulica = ulica;
        this.broj = broj;
    }

    public int getMesto() {
        return mesto;
    }

    public void setMesto(int mesto) {
        this.mesto = mesto;
    }

    public int getUlica() {
        return ulica;
    }

    public void setUlica(int ulica) {
        this.ulica = ulica;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    @Override
    public String getTableName() {
        return "Adresa";
    }

    @Override
    public String getAllColumnNames() {
        return "mesto, ulica, broj";
    }

    @Override
    public String getInsertColumnNames() {
        return getAllColumnNames();
    }

//    @Override
//    public String getColumnValues() {
//        return mesto + ", " + ulica + ", " + broj;
//    }
//
//    @Override
//    public String getUpdateClause() {
//        // Adresa nema poseban update osim ključa, ali ako treba možeš dozvoliti update broja
//        return "broj = " + broj;
//    }
//
//    @Override
//    public String getWhereIdClause() {
//        return "mesto = " + mesto + " AND ulica = " + ulica + " AND broj = " + broj;
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
    public List<Adresa> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Adresa> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Adresa(rs.getInt("mesto"), rs.getInt("ulica"), rs.getInt("broj")));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "mesto, ulica, broj";
    }
}
