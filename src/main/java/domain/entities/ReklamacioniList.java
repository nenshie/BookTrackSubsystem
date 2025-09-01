package domain.entities;

import domain.DomainObject;
import domain.entities.DetaljiReklamacionogLista;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReklamacioniList extends DomainObject {

    private int reklamacioniListId;
    private DetaljiReklamacionogLista detalji;
    private int prijemniList;
    private int radnik;
    private int artikal;
    private int tipResenja;

    public ReklamacioniList() {}

    public ReklamacioniList(int reklamacioniListId, DetaljiReklamacionogLista detalji, int prijemniList, int radnik, int artikal, int tipResenja) {
        this.reklamacioniListId = reklamacioniListId;
        this.detalji = detalji;
        this.prijemniList = prijemniList;
        this.radnik = radnik;
        this.artikal = artikal;
        this.tipResenja = tipResenja;
    }

    // Getteri i setteri

    public int getReklamacioniListId() { return reklamacioniListId; }
    public void setReklamacioniListId(int reklamacioniListId) { this.reklamacioniListId = reklamacioniListId; }

    public DetaljiReklamacionogLista getDetalji() { return detalji; }
    public void setDetalji(DetaljiReklamacionogLista detalji) { this.detalji = detalji; }

    public int getPrijemniList() { return prijemniList; }
    public void setPrijemniList(int prijemniList) { this.prijemniList = prijemniList; }

    public int getRadnik() { return radnik; }
    public void setRadnik(int radnik) { this.radnik = radnik; }

    public int getArtikal() { return artikal; }
    public void setArtikal(int artikal) { this.artikal = artikal; }

    public int getTipResenja() { return tipResenja; }
    public void setTipResenja(int tipResenja) { this.tipResenja = tipResenja; }

    @Override
    public String getTableName() {
        return "REKLAMACIONILIST";
    }

    @Override
    public String getAllColumnNames() {
        return "reklamacioniListId, detalji, prijemniList, radnik, artikal, tipResenja";
    }

    @Override
    public String getInsertColumnNames() {
        return getAllColumnNames();
    }

    @Override
    public String getInsertPlaceholders() {
        return "?, ?, ?, ?, ?, ?";
    }

    @Override
    public void setInsertParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, reklamacioniListId);
        // Za KDT detalji, koristi STRUCT ili tekstualnu konverziju u zavisnosti od JDBC podrške:
        // Ovo primer koristi setObject, a u Oracle treba podesiti STRUCT tip
        ps.setObject(startIndex + 1, mapDetaljiToStruct(ps.getConnection(), detalji));
        ps.setInt(startIndex + 2, prijemniList);
        ps.setInt(startIndex + 3, radnik);
        ps.setInt(startIndex + 4, artikal);
        ps.setInt(startIndex + 5, tipResenja);
    }

    @Override
    public String getUpdateSetClause() {
        return "detalji = ?, prijemniList = ?, radnik = ?, artikal = ?, tipResenja = ?";
    }

    @Override
    public void setUpdateParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setObject(startIndex, mapDetaljiToStruct(ps.getConnection(), detalji));
        ps.setInt(startIndex + 1, prijemniList);
        ps.setInt(startIndex + 2, radnik);
        ps.setInt(startIndex + 3, artikal);
        ps.setInt(startIndex + 4, tipResenja);
    }

    @Override
    public String getWhereClause() {
        return "reklamacioniListId = ?";
    }

    @Override
    public void setWhereParameters(PreparedStatement ps, int startIndex) throws SQLException {
        ps.setInt(startIndex, reklamacioniListId);
    }

    @Override
    public List<ReklamacioniList> getObjectsFromResultSet(ResultSet rs) throws SQLException {
        List<ReklamacioniList> list = new ArrayList<>();
        while (rs.next()) {
            DetaljiReklamacionogLista det = mapStructToDetalji(rs.getObject("detalji"));
            list.add(new ReklamacioniList(
                    rs.getInt("reklamacioniListId"),
                    det,
                    rs.getInt("prijemniList"),
                    rs.getInt("radnik"),
                    rs.getInt("artikal"),
                    rs.getInt("tipResenja")
            ));
        }
        return list;
    }

    @Override
    public String getOrderByColumn() {
        return "reklamacioniListId";
    }

    // Helper metode za mapiranje između STRUCT i objekta

    private java.sql.Struct mapDetaljiToStruct(Connection conn, DetaljiReklamacionogLista d) throws SQLException {
        if (d == null) return null;
        Object[] attrs = new Object[] {
                d.getOpis(),
                d.getNapomena(),
                d.getDatumPrijema()
        };
        return conn.createStruct("DETALJI_REKLAMACIONOG_LISTA", attrs);
    }

    private DetaljiReklamacionogLista mapStructToDetalji(Object structObj) throws SQLException {
        if (structObj == null) return null;
        java.sql.Struct struct = (java.sql.Struct) structObj;
        Object[] attrs = struct.getAttributes();
        String opis = (String) attrs[0];
        String napomena = (String) attrs[1];
        java.sql.Timestamp datum = (java.sql.Timestamp) attrs[2];
        return new DetaljiReklamacionogLista(opis, napomena, datum);
    }
}
