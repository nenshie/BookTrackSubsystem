package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.List;

public class Artikal extends DomainObject {
    private int artikalId;
    private String naziv;
    private String barkod;
    private String jedinicaMere;

    public Artikal() {
    }

    public Artikal(int artikalId, String naziv, String barkod, String jedinicaMere) {
        this.artikalId = artikalId;
        this.naziv = naziv;
        this.barkod = barkod;
        this.jedinicaMere = jedinicaMere;
    }

    public int getArtikalId() {
        return artikalId;
    }

    public void setArtikalId(int artikalId) {
        this.artikalId = artikalId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public String getJedinicaMere() {
        return jedinicaMere;
    }

    public void setJedinicaMere(String jedinicaMere) {
        this.jedinicaMere = jedinicaMere;
    }

    @Override
    public String getTableName() {
        return "ARTIKAL";
    }

    @Override
    public String getAllColumnNames() {
        return "artikalId, naziv, barkod, jedinica_mere";
    }

    @Override
    public String getInsertColumnNames() {
        return "artikalId, naziv, barkod, jedinica_mere";
    }

    @Override
    public String getInsertPlaceholders() {
        return "?, ?, ?, ?";
    }

    @Override
    public void setInsertParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, artikalId);
        ps.setString(startIndex + 1, naziv);
        java.sql.Connection conn = ps.getConnection();
        java.sql.Struct barkodStruct = conn.createStruct("BARKOD", new Object[]{barkod});
        ps.setObject(startIndex + 2, barkodStruct);
        ps.setString(startIndex + 3, jedinicaMere);
    }


    @Override
    public String getUpdateSetClause() {
        return "naziv = ?, barkod = ?, jedinica_mere = ?";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setString(startIndex, naziv);

        java.sql.Connection conn = ps.getConnection();
        java.sql.Struct barkodStruct = conn.createStruct("BARKOD", new Object[]{barkod});
        ps.setObject(startIndex + 1, barkodStruct);

        ps.setString(startIndex + 2, jedinicaMere);
    }

    @Override
    public String getWhereClause() {
        return "artikalId = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, artikalId);
    }

    @Override
    public List<Artikal> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<Artikal> list = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("artikalId");
            String naziv = rs.getString("naziv");

            String barkodValue = null;
            Object obj = rs.getObject("barkod");
            if (obj != null) {
                Struct struct = (Struct) obj;
                Object[] attrs = struct.getAttributes();
                if (attrs != null && attrs.length > 0 && attrs[0] != null) {
                    barkodValue = attrs[0].toString();
                }
            }

            String jedinica = rs.getString("jedinica_mere");
            list.add(new Artikal(id, naziv, barkodValue, jedinica));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "artikalId";
    }

    @Override
    public String toString() {
        return naziv;
    }
}
