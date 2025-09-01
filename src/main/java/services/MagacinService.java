package services;

import domain.entities.Magacin;

import java.sql.SQLException;
import java.util.List;

public class MagacinService extends BaseService<Magacin> {

    public MagacinService() {
        super(new Magacin());
    }

    public int addMagacin(Magacin m) throws SQLException {
        List<Object> params = List.of(
                m.getMagacinId(),
                m.getNaziv()
        );
        return add(m, params);
    }

    public int updateMagacin(Magacin m) throws SQLException {
        List<Object> paramsForUpdate = List.of(
                m.getNaziv()
        );
        List<Object> paramsForWhere = List.of(
                m.getMagacinId()
        );
        return update(m, paramsForUpdate, paramsForWhere);
    }

    public int deleteMagacinById(int magacinId) throws SQLException {
        Magacin m = new Magacin();
        m.setMagacinId(magacinId);
        return delete(m);
    }

    public List<Magacin> getAllMagacini() throws SQLException {
        return getAll();
    }

//    public Magacin getMagacinById(int magacinId) throws SQLException {
//        return getById(magacinId);
//    }
}
