package domain.entities;

import domain.DomainObject;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StavkaPL extends DomainObject {

    private int prijemniListId;
    private int rb;
    private int kolicina;
    private int sifraPDV;
    private int artikalId;

    public StavkaPL() {}

    public StavkaPL(int prijemniListId, int rb, int kolicina, int sifraPDV, int artikalId) {
        this.prijemniListId = prijemniListId;
        this.rb = rb;
        this.kolicina = kolicina;
        this.sifraPDV = sifraPDV;
        this.artikalId = artikalId;
    }

    public int getPrijemniListId() { return prijemniListId; }
    public void setPrijemniListId(int prijemniListId) { this.prijemniListId = prijemniListId; }
    public int getRb() { return rb; }
    public void setRb(int rb) { this.rb = rb; }
    public int getKolicina() { return kolicina; }
    public void setKolicina(int kolicina) { this.kolicina = kolicina; }
    public int getSifraPDV() { return sifraPDV; }
    public void setSifraPDV(int sifraPDV) { this.sifraPDV = sifraPDV; }
    public int getArtikalId() { return artikalId; }
    public void setArtikalId(int artikalId) { this.artikalId = artikalId; }

    @Override
    public String getTableName() {
        return "STAVKAPL";
    }
    @Override
    public String getAllColumnNames() {
        return "prijemniListId, rb, kolicina, sifraPDV, artikalId";
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
        ps.setInt(startIndex, prijemniListId);
        ps.setInt(startIndex + 1, rb);
        ps.setInt(startIndex + 2, kolicina);
        ps.setInt(startIndex + 3, sifraPDV);
        ps.setInt(startIndex + 4, artikalId);
    }
    @Override
    public String getUpdateSetClause() {
        return "kolicina = ?, sifraPDV = ?, artikalId = ?";
    }
    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, kolicina);
        ps.setInt(startIndex + 1, sifraPDV);
        ps.setInt(startIndex + 2, artikalId);
    }
    @Override
    public String getWhereClause() {
        return "prijemniListId = ? AND rb = ?";
    }
    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, prijemniListId);
        ps.setInt(startIndex + 1, rb);
    }
    @Override
    public List<StavkaPL> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<StavkaPL> list = new ArrayList<>();
        while (rs.next()) {
            list.add(new StavkaPL(
                    rs.getInt("prijemniListId"),
                    rs.getInt("rb"),
                    rs.getInt("kolicina"),
                    rs.getInt("sifraPDV"),
                    rs.getInt("artikalId")
            ));
        }
        return list;
    }
    @Override
    public String getOrderByColumn() {
        return "prijemniListId, rb";
    }
}
