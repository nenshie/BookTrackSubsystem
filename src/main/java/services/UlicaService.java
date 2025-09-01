package services;

import domain.entities.Ulica;

import java.sql.SQLException;
import java.util.List;

public class UlicaService extends BaseService<Ulica> {

    public UlicaService() {
        super(new Ulica());
    }

    public int addUlica(Ulica ulica) throws SQLException {
        List<Object> params = List.of(
                ulica.getMestoId(),
                ulica.getUlicaId(),
                ulica.getNaziv()
        );
        return add(ulica, params);
    }

//    public int updateUlica(Ulica ulica) throws SQLException {
//        List<Object> params = List.of(
//                ulica.getNaziv()
//        );
//        return update(ulica, params);
//    }

    public int deleteUlica(Ulica ulica) throws SQLException {
        return delete(ulica);
    }

    public List<Ulica> getAllUlica() throws SQLException {
        return getAll();
    }
}
