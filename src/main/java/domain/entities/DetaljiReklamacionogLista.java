package domain.entities;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DetaljiReklamacionogLista {

    private String opis;
    private String napomena;
    private java.sql.Timestamp datumPrijema;

    public DetaljiReklamacionogLista() {}

    public DetaljiReklamacionogLista(String opis, String napomena, java.sql.Timestamp datumPrijema) {
        this.opis = opis;
        this.napomena = napomena;
        this.datumPrijema = datumPrijema;
    }


    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }

    public String getNapomena() { return napomena; }
    public void setNapomena(String napomena) { this.napomena = napomena; }

    public java.sql.Timestamp getDatumPrijema() { return datumPrijema; }
    public void setDatumPrijema(java.sql.Timestamp datumPrijema) { this.datumPrijema = datumPrijema; }
}
