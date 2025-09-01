package services;

import database.DatabaseBroker;
import domain.entities.PrijemniList;

import java.sql.SQLException;
import java.util.List;

public class PrijemniListService {

    private final DatabaseBroker dbBroker = new DatabaseBroker();

    public List<PrijemniList> getAll() throws SQLException {
        return dbBroker.getAll(new PrijemniList());
    }

    public int add(PrijemniList pl) throws SQLException {
        return dbBroker.insert(pl, null);
    }

    public int update(PrijemniList pl) throws SQLException {
        List<Object> updateParams = List.of(pl.getDatumOd(), pl.getOsnov(), pl.getKomentar(), pl.getDostavnica(), pl.getRadnik(), pl.getUkupnoStavki());
        List<Object> whereParams = List.of(pl.getPrijemniListId());
        return dbBroker.update(pl, updateParams, whereParams);
    }

    public int delete(PrijemniList pl) throws SQLException {
        return dbBroker.delete(pl);
    }
}
