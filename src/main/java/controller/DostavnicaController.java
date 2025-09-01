package controller;

import domain.entities.Dostavnica;
import services.DostavnicaService;

import java.sql.SQLException;
import java.util.List;

public class DostavnicaController {
    private DostavnicaService service;

    public DostavnicaController() {
        this.service = new DostavnicaService();
    }

    public List<Dostavnica> getAllDostavnice() throws SQLException {
        return service.getAllDostavnice();
    }

    public boolean addDostavnica(Dostavnica d) throws SQLException {
            service.addDostavnica(d);
            return true;

    }

    public boolean updateDostavnica(Dostavnica d) throws SQLException {
            service.updateDostavnica(d);
            return true;
    }

    public boolean deleteDostavnicaById(int dostavnicaId) throws SQLException {
            service.deleteDostavnicaById(dostavnicaId);
            return true;
    }
}
