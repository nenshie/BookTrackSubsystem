package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Radnik extends DomainObject {
    private int sifraRadnika;
    private String imePrezime;

    public Radnik() {}

    public Radnik(int sifraRadnika, String imePrezime) {
        this.sifraRadnika = sifraRadnika;
        this.imePrezime = imePrezime;
    }

    public int getSifraRadnika() {
        return sifraRadnika;
    }

    public void setSifraRadnika(int sifraRadnika) {
        this.sifraRadnika = sifraRadnika;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public void setImePrezime(String imePrezime) {
        this.imePrezime = imePrezime;
    }

    @Override
    public String getTableName() {
        return "RADNIK";
    }

    @Override
    public String getAllColumnNames() {
        return "sifraRadnika, imePrezime";
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
        ps.setInt(startIndex, sifraRadnika);
        ps.setString(startIndex + 1, imePrezime);
    }

    @Override
    public String getUpdateSetClause() {
        return "imePrezime = ?";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setString(startIndex, imePrezime);
    }

    @Override
    public String getWhereClause() {
        return "sifraRadnika = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, sifraRadnika);
    }

    @Override
    public List<Radnik> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Radnik> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Radnik(
                    rs.getInt("sifraRadnika"),
                    rs.getString("imePrezime")
            ));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "sifraRadnika";
    }
}
