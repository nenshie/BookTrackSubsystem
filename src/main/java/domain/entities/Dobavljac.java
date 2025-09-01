package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dobavljac extends DomainObject {
    private int sifraDobavljaca;
    private String naziv;
    private String mb;
    private int ulica;
    private int mesto;
    private int broj;

    public Dobavljac() {
    }

    public Dobavljac(int sifraDobavljaca, String naziv, String mb, int ulica, int mesto, int broj) {
        this.sifraDobavljaca = sifraDobavljaca;
        this.naziv = naziv;
        this.mb = mb;
        this.ulica = ulica;
        this.mesto = mesto;
        this.broj = broj;
    }

    public int getSifraDobavljaca() {
        return sifraDobavljaca;
    }

    public void setSifraDobavljaca(int sifraDobavljaca) {
        this.sifraDobavljaca = sifraDobavljaca;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public int getUlica() {
        return ulica;
    }

    public void setUlica(int ulica) {
        this.ulica = ulica;
    }

    public int getMesto() {
        return mesto;
    }

    public void setMesto(int mesto) {
        this.mesto = mesto;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    @Override
    public String getTableName() {
        return "Dobavljac";
    }

    @Override
    public String getAllColumnNames() {
        return "sifraDobavljaca, naziv, mb, ulica, mesto, broj";
    }

    @Override
    public String getInsertColumnNames() {
        return getAllColumnNames();
    }

//    @Override
//    public String getColumnValues() {
//        return sifraDobavljaca + ", '" + naziv + "', '" + mb + "', " + ulica + ", " + mesto + ", " + broj;
//    }
//
//    @Override
//    public String getUpdateClause() {
//        return "naziv = '" + naziv + "', mb = '" + mb + "', ulica = " + ulica + ", mesto = " + mesto + ", broj = " + broj;
//    }
//
//    @Override
//    public String getWhereIdClause() {
//        return "sifraDobavljaca = " + sifraDobavljaca;
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
        return "?, ?, ?, ?, ?, ?";
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
    public List<Dobavljac> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Dobavljac> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Dobavljac(
                    rs.getInt("sifraDobavljaca"),
                    rs.getString("naziv"),
                    rs.getString("mb"),
                    rs.getInt("ulica"),
                    rs.getInt("mesto"),
                    rs.getInt("broj")
            ));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "sifraDobavljaca";
    }
}
