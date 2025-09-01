package services;

import domain.entities.Dostavnica;

import java.sql.SQLException;
import java.util.List;

public class DostavnicaService extends BaseService<Dostavnica> {

    public DostavnicaService() {
        super(new Dostavnica());
    }

    public int addDostavnica(Dostavnica d) throws SQLException {
        List<Object> params = List.of(
                d.getDostavnicaId(),
                d.getDatumIzdavanja(),
                d.getTekuciRacun(),
                d.getOpis(),
                d.getRadnikOdgovoran(),
                d.getUlica(),
                d.getMesto(),
                d.getBroj(),
                d.getDobavljac(),
                d.getPredracun(),
                d.getRadnik(),
                d.getValuta()
        );
        return add(d, params);
    }

    public int updateDostavnica(Dostavnica d) throws SQLException {
        List<Object> paramsForUpdate = List.of(
                d.getDatumIzdavanja(),
                d.getTekuciRacun(),
                d.getOpis(),
                d.getRadnikOdgovoran(),
                d.getUlica(),
                d.getMesto(),
                d.getBroj(),
                d.getDobavljac(),
                d.getPredracun(),
                d.getRadnik(),
                d.getValuta()
        );
        List<Object> paramsForWhere = List.of(d.getDostavnicaId());

        return update(d, paramsForUpdate, paramsForWhere);
    }

    public int deleteDostavnicaById(int dostavnicaId) throws SQLException {
        Dostavnica d = new Dostavnica();
        d.setDostavnicaId(dostavnicaId);
        return delete(d);
    }

    public List<Dostavnica> getAllDostavnice() throws SQLException {
        return getAll();
    }
}
