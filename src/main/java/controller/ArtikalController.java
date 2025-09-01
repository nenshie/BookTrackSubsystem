package controller;

import domain.entities.Artikal;
import services.ArtikalService;

import java.sql.SQLException;
import java.util.List;

public class ArtikalController {
    private final ArtikalService service = new ArtikalService();

    public List<Artikal> getAllArtikli() throws SQLException {
        return service.getAll();
    }

    public boolean addArtikal(Artikal artikal) throws SQLException {
        return service.add(artikal) > 0;
    }

    public boolean updateArtikal(Artikal artikal) throws SQLException {
        return service.update(artikal) > 0;
    }

    public boolean deleteArtikal(Artikal artikal) throws SQLException {
        return service.delete(artikal) > 0;
    }

    public List<Artikal> searchArtikli(String name) throws SQLException {
        return service.searchByName(name);
    }
}

