package services;

import domain.entities.Mesto;

import java.sql.SQLException;
import java.util.List;

public class MestoService extends BaseService<Mesto> {

    public MestoService() {
        super(new Mesto());
    }

    public int addMesto(Mesto mesto) throws SQLException {
        List<Object> params = List.of(
                mesto.getMestoId(),
                mesto.getNaziv(),
                mesto.getPtt()
        );
        return add(mesto, params);
    }

//    public int updateMesto(Mesto mesto) throws SQLException {
//        List<Object> params = List.of(
//                mesto.getNaziv(),
//                mesto.getPtt()
//        );
//        return update(mesto, params);
//    }

    public int deleteMesto(Mesto mesto) throws SQLException {
        return delete(mesto);
    }

    public List<Mesto> getAllMesta() throws SQLException {
        return getAll();
    }
}
