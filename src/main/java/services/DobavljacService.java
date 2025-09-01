package services;

import database.DatabaseBroker;
import domain.entities.Dobavljac;

import java.sql.SQLException;
import java.util.List;

public class DobavljacService extends BaseService<Dobavljac> {

    public DobavljacService() {
        super(new Dobavljac());
    }

    public int addDobavljac(Dobavljac dobavljac) throws SQLException {
        List<Object> params = List.of(
                dobavljac.getSifraDobavljaca(),
                dobavljac.getNaziv(),
                dobavljac.getMb(),
                dobavljac.getUlica(),
                dobavljac.getMesto(),
                dobavljac.getBroj()
        );
        return add(dobavljac, params);
    }

//    public int updateDobavljac(Dobavljac dobavljac) throws SQLException {
//        List<Object> params = List.of(
//                dobavljac.getNaziv(),
//                dobavljac.getMb(),
//                dobavljac.getUlica(),
//                dobavljac.getMesto(),
//                dobavljac.getBroj()
//        );
//        return update(dobavljac, params);
//    }

    public int deleteDobavljac(Dobavljac dobavljac) throws SQLException {
        return delete(dobavljac);
    }

    public List<Dobavljac> getAllDobavljaci() throws SQLException {
        return getAll();
    }
}
