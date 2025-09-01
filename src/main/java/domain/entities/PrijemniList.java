package domain.entities;

import domain.DomainObject;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrijemniList extends DomainObject {
    private int prijemniListId;
    private Date datumOd;
    private String osnov;
    private String komentar;
    private int dostavnica;
    private int radnik;
    private int ukupnoStavki;

    public PrijemniList() {}

    public PrijemniList(int prijemniListId, Date datumOd, String osnov, String komentar, int dostavnica, int radnik, int ukupnoStavki) {
        this.prijemniListId = prijemniListId;
        this.datumOd = datumOd;
        this.osnov = osnov;
        this.komentar = komentar;
        this.dostavnica = dostavnica;
        this.radnik = radnik;
        this.ukupnoStavki = ukupnoStavki;
    }

    // Getteri i setteri

    public int getPrijemniListId() { return prijemniListId; }
    public void setPrijemniListId(int prijemniListId) { this.prijemniListId = prijemniListId; }
    public Date getDatumOd() { return datumOd; }
    public void setDatumOd(Date datumOd) { this.datumOd = datumOd; }
    public String getOsnov() { return osnov; }
    public void setOsnov(String osnov) { this.osnov = osnov; }
    public String getKomentar() { return komentar; }
    public void setKomentar(String komentar) { this.komentar = komentar; }
    public int getDostavnica() { return dostavnica; }
    public void setDostavnica(int dostavnica) { this.dostavnica = dostavnica; }
    public int getRadnik() { return radnik; }
    public void setRadnik(int radnik) { this.radnik = radnik; }
    public int getUkupnoStavki() { return ukupnoStavki; }
    public void setUkupnoStavki(int ukupnoStavki) { this.ukupnoStavki = ukupnoStavki; }

    @Override
    public String getTableName() {
        return "PRIJEMNILIST";
    }

    @Override
    public String getAllColumnNames() {
        return "prijemniListId, datumOd, osnov, komentar, dostavnica, radnik, ukupnoStavki";
    }

    @Override
    public String getInsertColumnNames() {
        return getAllColumnNames();
    }

    @Override
    public String getInsertPlaceholders() {
        return "?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    public void setInsertParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, prijemniListId);
        ps.setDate(startIndex + 1, datumOd);
        ps.setString(startIndex + 2, osnov);
        ps.setString(startIndex + 3, komentar);
        ps.setInt(startIndex + 4, dostavnica);
        ps.setInt(startIndex + 5, radnik);
        ps.setInt(startIndex + 6, ukupnoStavki);
    }

    @Override
    public String getUpdateSetClause() {
        return "datumOd = ?, osnov = ?, komentar = ?, dostavnica = ?, radnik = ?, ukupnoStavki = ?";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setDate(startIndex, datumOd);
        ps.setString(startIndex + 1, osnov);
        ps.setString(startIndex + 2, komentar);
        ps.setInt(startIndex + 3, dostavnica);
        ps.setInt(startIndex + 4, radnik);
        ps.setInt(startIndex + 5, ukupnoStavki);
    }

    @Override
    public String getWhereClause() {
        return "prijemniListId = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, prijemniListId);
    }

    @Override
    public List<PrijemniList> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<PrijemniList> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new PrijemniList(
                    rs.getInt("prijemniListId"),
                    rs.getDate("datumOd"),
                    rs.getString("osnov"),
                    rs.getString("komentar"),
                    rs.getInt("dostavnica"),
                    rs.getInt("radnik"),
                    rs.getInt("ukupnoStavki")
            ));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "prijemniListId";
    }
}
