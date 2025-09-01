package services;

import domain.entities.PDV;

import java.sql.SQLException;
import java.util.List;

public class PDVService extends BaseService<PDV> {

    public PDVService() {
        super(new PDV());
    }

    public int addPDV(PDV pdv) throws SQLException {
        List<Object> params = List.of(pdv.getSifraPDV(), pdv.getIznos());
        return add(pdv, params);
    }
//
//    public int updatePDV(PDV pdv) throws SQLException {
//        List<Object> params = List.of(pdv.getIznos());
//        return update(pdv, params);
//    }

    public int deletePDV(PDV pdv) throws SQLException {
        return delete(pdv);
    }

    public List<PDV> getAllPDV() throws SQLException {
        return getAll();
    }
}
