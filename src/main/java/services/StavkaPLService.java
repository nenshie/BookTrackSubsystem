package services;

import database.DatabaseBroker;
import domain.entities.StavkaPL;

import java.sql.SQLException;
import java.util.List;

public class StavkaPLService {
    private DatabaseBroker dbBroker = new DatabaseBroker();

    public List<StavkaPL> getAll() throws SQLException{
        return dbBroker.getAll(new StavkaPL());
    }

    public int add(StavkaPL stavka) throws SQLException {
        return dbBroker.insert(stavka, null);
    }

    public int update(StavkaPL stavka) throws SQLException {
        List<Object> updateParams = List.of(stavka.getKolicina(), stavka.getSifraPDV(), stavka.getArtikalId());
        List<Object> whereParams = List.of(stavka.getPrijemniListId(), stavka.getRb());
        return dbBroker.update(stavka, updateParams, whereParams);
    }

    public int delete(StavkaPL stavka) throws SQLException {
        return dbBroker.delete(stavka);
    }
}
