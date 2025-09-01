package services;

import database.DatabaseBroker;
import database.DatabaseConnection;
import domain.entities.Artikal;

import java.sql.Connection;
import java.sql.SQLException;
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
}
