package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class StavkaPredracuna extends DomainObject {

    private int brojPredracuna;
    private int rb;
    private int kolicina;
    private String rabat;
    private int artikal;
    private int pdv;
    private String naziv;
    private String barkod;

    public StavkaPredracuna() {}

    public StavkaPredracuna(int brojPredracuna, int rb, int kolicina, String rabat, int artikal, int pdv, String naziv, String barkod) {
        this.brojPredracuna = brojPredracuna;
        this.rb = rb;
        this.kolicina = kolicina;
        this.rabat = rabat;
        this.artikal = artikal;
        this.pdv = pdv;
        this.naziv = naziv;
        this.barkod = barkod;
    }

    // Getters and setters for all fields
    public int getBrojPredracuna() { return brojPredracuna; }
    public void setBrojPredracuna(int brojPredracuna) { this.brojPredracuna = brojPredracuna; }

    public int getRb() { return rb; }
    public void setRb(int rb) { this.rb = rb; }

    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { this.kolicina = kolicina; }

    public String getRabat() { return rabat; }
    public void setRabat(String rabat) { this.rabat = rabat; }

    public int getArtikal() { return artikal; }
    public void setArtikal(int artikal) { this.artikal = artikal; }

    public int getPdv() { return pdv; }
    public void setPdv(int pdv) { this.pdv = pdv; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getBarkod() { return barkod; }
    public void setBarkod(String barkod) { this.barkod = barkod; }

    @Override
    public String getTableName() { return "STAVKAPREDRACUNA"; }

    @Override
    public String getAllColumnNames() {
        return "brojPredracuna, rb, kolicina, rabat, artikal, pdv, naziv, barkod";
    }

    @Override
    public String getInsertColumnNames() {
        return "brojPredracuna, rb, kolicina, rabat, artikal, pdv";
    }

    @Override
    public String getInsertPlaceholders() { return "?, ?, ?, ?, ?, ?"; }

    @Override
    public void setInsertParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, brojPredracuna);
        ps.setInt(startIndex + 1, rb);
        ps.setInt(startIndex + 2, kolicina);
        ps.setString(startIndex + 3, rabat);
        ps.setInt(startIndex + 4, artikal);
        ps.setInt(startIndex + 5, pdv);

    }
    @Override
    public String getUpdateSetClause() {
        return "naziv = ?, barkod = ?";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setString(startIndex, naziv);

        java.sql.Connection conn = ps.getConnection();
        java.sql.Struct barkodStruct = conn.createStruct("BARKOD", new Object[]{barkod});
        ps.setObject(startIndex + 1, barkodStruct);
    }

    @Override
    public String getWhereClause() {
        return "brojPredracuna = ? AND rb = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, brojPredracuna);
        ps.setInt(startIndex + 1, rb);
    }

    @Override
    public List<StavkaPredracuna> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<StavkaPredracuna> list = new ArrayList<>();
        while (rs.next()) {
            String barkodValue = null;
            Object obj = rs.getObject("barkod");
            if (obj != null) {
                Struct struct = (Struct) obj;
                Object[] attrs = struct.getAttributes();
                if (attrs != null && attrs.length > 0 && attrs[0] != null) {
                    barkodValue = attrs[0].toString();
                }
            }

            list.add(new StavkaPredracuna(
                    rs.getInt("brojPredracuna"),
                    rs.getInt("rb"),
                    rs.getInt("kolicina"),
                    rs.getString("rabat"),
                    rs.getInt("artikal"),
                    rs.getInt("pdv"),
                    rs.getString("naziv"),
                    barkodValue
            ));
        }
        return list;
    }


    @Override
    public String getOrderByColumn() { return "brojPredracuna, rb"; }
}
