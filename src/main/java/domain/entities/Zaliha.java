package domain.entities;

import domain.DomainObject;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Zaliha extends DomainObject {

    private int artikalId;
    private int magacinId;
    private BigDecimal prosecnaNabavnaCena;
    private int raspolozivaKolicina;
    private String naziv;
    private String nazivArtikla;

    public Zaliha() {
    }

    public Zaliha(int artikalId, int magacinId, BigDecimal prosecnaNabavnaCena, int raspolozivaKolicina, String naziv, String nazivArtikla) {
        this.artikalId = artikalId;
        this.magacinId = magacinId;
        this.prosecnaNabavnaCena = prosecnaNabavnaCena;
        this.raspolozivaKolicina = raspolozivaKolicina;
        this.naziv = naziv;
        this.nazivArtikla = nazivArtikla;
    }

    public int getArtikalId() {
        return artikalId;
    }

    public void setArtikalId(int artikalId) {
        this.artikalId = artikalId;
    }

    public int getMagacinId() {
        return magacinId;
    }

    public void setMagacinId(int magacinId) {
        this.magacinId = magacinId;
    }

    public BigDecimal getProsecnaNabavnaCena() {
        return prosecnaNabavnaCena;
    }

    public void setProsecnaNabavnaCena(BigDecimal prosecnaNabavnaCena) {
        this.prosecnaNabavnaCena = prosecnaNabavnaCena;
    }

    public int getRaspolozivaKolicina() {
        return raspolozivaKolicina;
    }

    public void setRaspolozivaKolicina(int raspolozivaKolicina) {
        this.raspolozivaKolicina = raspolozivaKolicina;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNazivArtikla() {
        return nazivArtikla;
    }

    public void setNazivArtikla(String nazivArtikla) {
        this.nazivArtikla = nazivArtikla;
    }

    @Override
    public String getTableName() {
        return "Zaliha";
    }

    @Override
    public String getAllColumnNames() {
        return "artikalId, magacinId, prosecnaNabavnaCena, raspolozivaKolicina, naziv";
    }

    @Override
    public String getInsertColumnNames() {
        return getAllColumnNames();
    }

    @Override
    public String getInsertPlaceholders() {
        return "?, ?, ?, ?, ?";
    }

    @Override
    public void setInsertParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, artikalId);
        ps.setInt(startIndex + 1, magacinId);
        ps.setBigDecimal(startIndex + 2, prosecnaNabavnaCena);
        ps.setInt(startIndex + 3, raspolozivaKolicina);
        ps.setString(startIndex + 4, naziv);
    }

    @Override
    public String getUpdateSetClause() {
        return "naziv = ?";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setString(startIndex, naziv);
    }

    @Override
    public String getWhereClause() {
        return "artikalId = ? AND magacinId = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, artikalId);
        ps.setInt(startIndex + 1, magacinId);
    }

    @Override
    public List<Zaliha> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Zaliha> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new Zaliha(
                    rs.getInt("artikalId"),
                    rs.getInt("magacinId"),
                    rs.getBigDecimal("prosecnaNabavnaCena"),
                    rs.getInt("raspolozivaKolicina"),
                    rs.getString("naziv"),
                    rs.getString("nazivArtikla")
            ));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "artikalId, magacinId";
    }
}
