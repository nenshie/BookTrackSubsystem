package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Predracun extends DomainObject {

    private int brojPredracuna;
    private Date datumOd;
    private String odgovorniRadnik;
    private String radnikKreirao;
    private int dobavljac;
    private int ulica;
    private int mesto;
    private int broj;

    public Predracun() {}

    public Predracun(int brojPredracuna, Date datumOd, String odgovorniRadnik, String radnikKreirao,
                     int dobavljac, int ulica, int mesto, int broj) {
        this.brojPredracuna = brojPredracuna;
        this.datumOd = datumOd;
        this.odgovorniRadnik = odgovorniRadnik;
        this.radnikKreirao = radnikKreirao;
        this.dobavljac = dobavljac;
        this.ulica = ulica;
        this.mesto = mesto;
        this.broj = broj;
    }

    // Getteri i setteri
    public int getBrojPredracuna() { return brojPredracuna; }
    public void setBrojPredracuna(int brojPredracuna) { this.brojPredracuna = brojPredracuna; }

    public Date getDatumOd() { return datumOd; }
    public void setDatumOd(Date datumOd) { this.datumOd = datumOd; }

    public String getOdgovorniRadnik() { return odgovorniRadnik; }
    public void setOdgovorniRadnik(String odgovorniRadnik) { this.odgovorniRadnik = odgovorniRadnik; }

    public String getRadnikKreirao() { return radnikKreirao; }
    public void setRadnikKreirao(String radnikKreirao) { this.radnikKreirao = radnikKreirao; }

    public int getDobavljac() { return dobavljac; }
    public void setDobavljac(int dobavljac) { this.dobavljac = dobavljac; }

    public int getUlica() { return ulica; }
    public void setUlica(int ulica) { this.ulica = ulica; }

    public int getMesto() { return mesto; }
    public void setMesto(int mesto) { this.mesto = mesto; }

    public int getBroj() { return broj; }
    public void setBroj(int broj) { this.broj = broj; }

    @Override
    public String getTableName() {
        return "PREDRACUN";
    }

    @Override
    public String getAllColumnNames() {
        return "brojPredracuna, datumOd, odgovorniRadnik, radnikKreirao, dobavljac, ulica, mesto, broj";
    }

    @Override
    public String getInsertColumnNames() {
        return getAllColumnNames();
    }

    @Override
    public String getInsertPlaceholders() {
        return "?, ?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    public void setInsertParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, brojPredracuna);
        ps.setDate(startIndex + 1, datumOd);
        ps.setString(startIndex + 2, odgovorniRadnik);
        ps.setString(startIndex + 3, radnikKreirao);
        ps.setInt(startIndex + 4, dobavljac);
        ps.setInt(startIndex + 5, ulica);
        ps.setInt(startIndex + 6, mesto);
        ps.setInt(startIndex + 7, broj);
    }

    @Override
    public String getUpdateSetClause() {
        return "datumOd = ?, odgovorniRadnik = ?, radnikKreirao = ?, dobavljac = ?, ulica = ?, mesto = ?, broj = ?";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setDate(startIndex, datumOd);
        ps.setString(startIndex + 1, odgovorniRadnik);
        ps.setString(startIndex + 2, radnikKreirao);
        ps.setInt(startIndex + 3, dobavljac);
        ps.setInt(startIndex + 4, ulica);
        ps.setInt(startIndex + 5, mesto);
        ps.setInt(startIndex + 6, broj);
    }

    @Override
    public String getWhereClause() {
        return "brojPredracuna = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, brojPredracuna);
    }

    @Override
    public List<Predracun> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Predracun> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Predracun(
                    rs.getInt("brojPredracuna"),
                    rs.getDate("datumOd"),
                    rs.getString("odgovorniRadnik"),
                    rs.getString("radnikKreirao"),
                    rs.getInt("dobavljac"),
                    rs.getInt("ulica"),
                    rs.getInt("mesto"),
                    rs.getInt("broj")
            ));
        }
        return lista;
    }

    @Override
    public String getOrderByColumn() {
        return "brojPredracuna";
    }

    @Override
    public String toString() {

        return brojPredracuna + " | " + datumOd + " | " + odgovorniRadnik;
    }
}
