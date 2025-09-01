package services;

import domain.entities.Radnik;

import java.sql.SQLException;
import java.util.List;

public class RadnikService extends BaseService<Radnik> {
    public RadnikService() {
        super(new Radnik());
    }

    public int addRadnik(Radnik radnik) throws SQLException {
        List<Object> params = List.of(radnik.getSifraRadnika(), radnik.getImePrezime());
        return add(radnik, params);
    }

//    public int updateRadnik(Radnik radnik) throws SQLException {
//        List<Object> params = List.of(radnik.getImePrezime());
//        return update(radnik, params);
//    }

    public int deleteRadnik(Radnik radnik) throws SQLException {
        return delete(radnik);
    }

    public List<Radnik> getAllRadnici() throws SQLException {
        return getAll();
    }
}
