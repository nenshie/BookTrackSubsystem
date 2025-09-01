package services;

import domain.entities.Valuta;

import java.sql.SQLException;
import java.util.List;

public class ValutaService extends BaseService<Valuta> {

    public ValutaService() {
        super(new Valuta());
    }

    public int addValuta(Valuta valuta) throws SQLException {
        List<Object> params = List.of(valuta.getValutaId(), valuta.getNaziv());
        return add(valuta, params);
    }

//    public int updateValuta(Valuta valuta) throws SQLException {
//        List<Object> params = List.of(valuta.getNaziv());
//        return update(valuta, params);
//    }

    public int deleteValuta(Valuta valuta) throws SQLException {
        return delete(valuta);
    }

    public List<Valuta> getAllValuta() throws SQLException {
        return getAll();
    }
}
