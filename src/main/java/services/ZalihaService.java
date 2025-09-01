package services;

import domain.entities.Zaliha;
import domain.entities.Artikal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static database.DatabaseConnection.getConnection;

public class ZalihaService extends BaseService<Zaliha> {

    public ZalihaService() {
        super(new Zaliha());
    }

    public int addZaliha(Zaliha z) throws SQLException {
        List<Object> params = List.of(
                z.getArtikalId(),
                z.getMagacinId(),
                z.getProsecnaNabavnaCena(),
                z.getRaspolozivaKolicina()
        );
        return add(z, params);
    }

    public int deleteZaliha(Zaliha z) throws SQLException {
        return delete(z);
    }

    public List<Zaliha> getAllZaliheWithArtikalNaziv() throws SQLException {
        List<Zaliha> lista = new ArrayList<>();

        String sql = "SELECT z.artikalId, z.magacinId, z.prosecnaNabavnaCena, z.raspolozivaKolicina, z.naziv, a.naziv AS nazivArtikla " +
                "FROM Zaliha z " +
                "LEFT JOIN Artikal a ON z.artikalId = a.artikalId " +
                "ORDER BY z.artikalId, z.magacinId";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Zaliha z = new Zaliha(
                        rs.getInt("artikalId"),
                        rs.getInt("magacinId"),
                        rs.getBigDecimal("prosecnaNabavnaCena"),
                        rs.getInt("raspolozivaKolicina"),
                        rs.getString("naziv"),
                        rs.getString("nazivArtikla")
                );
                lista.add(z);
            }
        }
        return lista;
    }

    public int updateZaliha(Zaliha z) throws SQLException {
        List<Object> updateParams = List.of(
                z.getNaziv()
        );
        List<Object> whereParams = List.of(
                z.getArtikalId(),
                z.getMagacinId()
        );
        return dbBroker.update(z, updateParams, whereParams);
    }

    public List<Zaliha> getAllZalihe() throws SQLException {
        return getAll();
    }
}
