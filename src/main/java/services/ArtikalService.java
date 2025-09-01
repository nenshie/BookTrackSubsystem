package services;

import database.DatabaseBroker;
import database.DatabaseConnection;
import domain.entities.Artikal;
import oracle.jdbc.OracleTypes;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArtikalService {

    private final DatabaseBroker dbBroker = new DatabaseBroker();

    public List<Artikal> getAll() throws SQLException {
        return dbBroker.getAll(new Artikal());
    }

    public int add(Artikal artikal) throws SQLException {
        return dbBroker.insert(artikal, null);
    }

    public int update(Artikal artikal) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            Object[] attrs = new Object[]{artikal.getBarkod()};
            java.sql.Struct barkodStruct = conn.createStruct("BARKOD", attrs);

            List<Object> updateParams = List.of(
                    artikal.getNaziv(),
                    barkodStruct,
                    artikal.getJedinicaMere()
            );

            List<Object> whereParams = List.of(artikal.getArtikalId());
            return dbBroker.update(artikal, updateParams, whereParams);
        }

    }

    public int delete(Artikal artikal) throws SQLException {
        return dbBroker.delete(artikal);
    }

    public List<Artikal> searchByName(String name) throws SQLException {
        return dbBroker.getByColumn(new Artikal(), "naziv", "%" + name + "%");
    }

    public List<String> getSveJediniceMere() throws SQLException {
        List<String> lista = new ArrayList<>();
        String sql = "{ ? = call get_jedinice_mere() }";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.registerOutParameter(1, OracleTypes.CURSOR);
            cs.execute();

            try (ResultSet rs = (ResultSet) cs.getObject(1)) {
                while (rs.next()) {
                    lista.add(rs.getString("JEDINICA"));
                }
            }
        }
        return lista;
    }


    public void dodajJedinicuMere(String novaJedinica) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection()) {
            CallableStatement cs = conn.prepareCall("{call dodaj_jedinicu_mere(?)}");
            cs.setString(1, novaJedinica);
            cs.execute();
        }
    }
}
