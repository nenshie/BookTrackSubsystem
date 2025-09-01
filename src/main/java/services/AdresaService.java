package services;

import domain.entities.Adresa;

import java.sql.SQLException;
import java.util.List;

public class AdresaService extends BaseService<Adresa> {

    public AdresaService() {
        super(new Adresa());
    }

    public int addAdresa(Adresa adresa) throws SQLException {
        List<Object> params = List.of(
                adresa.getMesto(),
                adresa.getUlica(),
                adresa.getBroj()
        );
        return add(adresa, params);
    }

//    public int updateAdresa(Adresa adresa) throws SQLException {
//        List<Object> params = List.of(
//                adresa.getBroj()
//        );
//        return update(adresa, params);
//    }

    public int deleteAdresa(Adresa adresa) throws SQLException {
        return delete(adresa);
    }

    public List<Adresa> getAllAdrese() throws SQLException {
        return getAll();
    }
}
