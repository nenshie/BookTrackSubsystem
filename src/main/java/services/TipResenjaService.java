package services;

import database.DatabaseBroker;
import domain.entities.TipResenja;

import java.sql.SQLException;
import java.util.List;

public class TipResenjaService {

    private final DatabaseBroker dbBroker = new DatabaseBroker();

    public List<TipResenja> getAll() throws SQLException {
        return dbBroker.getAll(new TipResenja());
    }

    public int add(TipResenja tip) throws SQLException {
        return dbBroker.insert(tip, null);
    }

    public int update(TipResenja tip) throws SQLException {
        List<Object> updateParams = List.of(tip.getNaziv());
        List<Object> whereParams = List.of(tip.getSifraTipaResenja());
        return dbBroker.update(tip, updateParams, whereParams);
    }

    public int delete(TipResenja tip) throws SQLException {
        return dbBroker.delete(tip);
    }
}
