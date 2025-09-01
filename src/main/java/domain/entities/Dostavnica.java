package domain.entities;

import domain.DomainObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Dostavnica extends DomainObject {

    private int dostavnicaId;
    private Date datumIzdavanja;
    private String tekuciRacun;
    private String opis;
    private String radnikOdgovoran;
    private int ulica;
    private int mesto;
    private int broj;
    private int dobavljac;
    private int predracun;
    private int radnik;
    private int valuta;

    public Dostavnica() {}

    public Dostavnica(int dostavnicaId, Date datumIzdavanja, String tekuciRacun, String opis,
                      String radnikOdgovoran, int ulica, int mesto, int broj,
                      int dobavljac, int predracun, int radnik, int valuta) {
        this.dostavnicaId = dostavnicaId;
        this.datumIzdavanja = datumIzdavanja;
        this.tekuciRacun = tekuciRacun;
        this.opis = opis;
        this.radnikOdgovoran = radnikOdgovoran;
        this.ulica = ulica;
        this.mesto = mesto;
        this.broj = broj;
        this.dobavljac = dobavljac;
        this.predracun = predracun;
        this.radnik = radnik;
        this.valuta = valuta;
    }

    public int getDostavnicaId() { return dostavnicaId; }
    public void setDostavnicaId(int dostavnicaId) { this.dostavnicaId = dostavnicaId; }

    public Date getDatumIzdavanja() { return datumIzdavanja; }
    public void setDatumIzdavanja(Date datumIzdavanja) { this.datumIzdavanja = datumIzdavanja; }

    public String getTekuciRacun() { return tekuciRacun; }
    public void setTekuciRacun(String tekuciRacun) { this.tekuciRacun = tekuciRacun; }

    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public String getRadnikOdgovoran() { return radnikOdgovoran; }
    public void setRadnikOdgovoran(String radnikOdgovoran) { this.radnikOdgovoran = radnikOdgovoran; }

    public int getUlica() { return ulica; }
    public void setUlica(int ulica) { this.ulica = ulica; }

    public int getMesto() { return mesto; }
    public void setMesto(int mesto) { this.mesto = mesto; }

    public int getBroj() { return broj; }
    public void setBroj(int broj) { this.broj = broj; }

    public int getDobavljac() { return dobavljac; }
    public void setDobavljac(int dobavljac) { this.dobavljac = dobavljac; }

    public int getPredracun() { return predracun; }
    public void setPredracun(int predracun) { this.predracun = predracun; }

    public int getRadnik() { return radnik; }
    public void setRadnik(int radnik) { this.radnik = radnik; }

    public int getValuta() { return valuta; }
    public void setValuta(int valuta) { this.valuta = valuta; }

    // ================= DOMAIN OBJECT =================

    @Override
    public String getTableName() {
        return "DOSTAVNICAPOGLED";
    }

    @Override
    public String getAllColumnNames() {
        return "dostavnicaId, datumIzdavanja, tekuciRacun, opis, radnikOdgovoran, " +
                "ulica, mesto, broj, dobavljac, predracun, radnik, valuta";
    }

    @Override
    public String getInsertColumnNames() {
        return getAllColumnNames();
    }

    @Override
    public String getInsertPlaceholders() {
        return "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?";
    }

    @Override
    public void setInsertParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, dostavnicaId);
        ps.setDate(startIndex + 1, datumIzdavanja);
        ps.setString(startIndex + 2, tekuciRacun);
        ps.setString(startIndex + 3, opis);
        ps.setString(startIndex + 4, radnikOdgovoran);
        ps.setInt(startIndex + 5, ulica);
        ps.setInt(startIndex + 6, mesto);
        ps.setInt(startIndex + 7, broj);
        ps.setInt(startIndex + 8, dobavljac);
        ps.setInt(startIndex + 9, predracun);
        ps.setInt(startIndex + 10, radnik);
        ps.setInt(startIndex + 11, valuta);
    }

    @Override
    public String getUpdateSetClause() {
        return "datumIzdavanja = ?, tekuciRacun = ?, opis = ?, radnikOdgovoran = ?, " +
                "ulica = ?, mesto = ?, broj = ?, dobavljac = ?, predracun = ?, radnik = ?, valuta = ?";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setDate(startIndex, datumIzdavanja);
        ps.setString(startIndex + 1, tekuciRacun);
        ps.setString(startIndex + 2, opis);
        ps.setString(startIndex + 3, radnikOdgovoran);
        ps.setInt(startIndex + 4, ulica);
        ps.setInt(startIndex + 5, mesto);
        ps.setInt(startIndex + 6, broj);
        ps.setInt(startIndex + 7, dobavljac);
        ps.setInt(startIndex + 8, predracun);
        ps.setInt(startIndex + 9, radnik);
        ps.setInt(startIndex + 10, valuta);
    }

    @Override
    public String getWhereClause() {
        return "dostavnicaId = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, dostavnicaId);
    }

    @Override
    public List<Dostavnica> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Dostavnica> lista = new ArrayList<>();
        while (rs.next()) {
            lista.add(new Dostavnica(
                    rs.getInt("dostavnicaId"),
                    rs.getDate("datumIzdavanja"),
                    rs.getString("tekuciRacun"),
                    rs.getString("opis"),
                    rs.getString("radnikOdgovoran"),
                    rs.getInt("ulica"),
                    rs.getInt("mesto"),
                    rs.getInt("broj"),
                    rs.getInt("dobavljac"),
                    rs.getInt("predracun"),
                    rs.getInt("radnik"),
                    rs.getInt("valuta")
            ));
        }
        return lista;
    }

    @Override
    public String getOrderByColumn() {
        return "dostavnicaId";
    }
}
