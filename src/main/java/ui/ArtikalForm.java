package ui;

import controller.ArtikalController;
import domain.entities.Artikal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ArtikalForm extends JFrame {

    private final ArtikalController controller = new ArtikalController();
    private final DefaultTableModel model;
    private final JTable table;
    private final JTextField txtSearch;

    public ArtikalForm() {
        setTitle("Artikli");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Top panel with search
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        JLabel lblSearch = new JLabel("Pretraga po nazivu:");
        txtSearch = new JTextField();
        txtSearch.addActionListener(e -> searchArtikli());
        topPanel.add(lblSearch, BorderLayout.WEST);
        topPanel.add(txtSearch, BorderLayout.CENTER);
        topPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        add(topPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new String[]{"ID", "Naziv", "Barkod", "Jedinica Mere"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(650, 250));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(scrollPane, BorderLayout.CENTER);

        model.addTableModelListener(e -> {
            if (e.getType() != javax.swing.event.TableModelEvent.UPDATE) return;

            int row = e.getFirstRow();
            int col = e.getColumn();
            if (row < 0 || col < 0) return;

            try {
                int artikalId = (int) model.getValueAt(row, 0);
                String naziv = (String) model.getValueAt(row, 1);
                String barkod = (String) model.getValueAt(row, 2);
                String jedinica = (String) model.getValueAt(row, 3);

                Artikal updated = new Artikal();
                updated.setArtikalId(artikalId);
                updated.setNaziv(naziv);
                updated.setBarkod(barkod);
                updated.setJedinicaMere(jedinica);

                controller.updateArtikal(updated);

                loadArtikli();

            } catch (SQLException ex) {
                prikaziGresku("Greška pri izmeni artikla:\n" + ex.getMessage());
                loadArtikli(); // reset row if error
            }
        });

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton btnRefresh = new JButton("Osveži");
        btnRefresh.addActionListener(e -> loadArtikli());
        JButton btnAdd = new JButton("Dodaj");
        btnAdd.addActionListener(e -> dodajArtikal());
        btnPanel.add(btnRefresh);
        btnPanel.add(btnAdd);
        add(btnPanel, BorderLayout.SOUTH);

        // Load table initially
        loadArtikli();
    }

    private void loadArtikli() {
        try {
            model.setRowCount(0);
            List<Artikal> artikli = controller.getAllArtikli();
            for (Artikal a : artikli) {
                model.addRow(new Object[]{a.getArtikalId(), a.getNaziv(), a.getBarkod(), a.getJedinicaMere()});
            }
        } catch (SQLException e) {
            prikaziGresku("Greška pri učitavanju podataka: " + e.getMessage());
        }
    }

    private void searchArtikli() {
        String query = txtSearch.getText().trim();
        try {
            model.setRowCount(0);
            List<Artikal> results = query.isEmpty()
                    ? controller.getAllArtikli()
                    : controller.searchArtikli(query);
            for (Artikal a : results) {
                model.addRow(new Object[]{a.getArtikalId(), a.getNaziv(), a.getBarkod(), a.getJedinicaMere()});
            }
        } catch (SQLException e) {
            prikaziGresku("Greška pri pretrazi: " + e.getMessage());
        }
    }

    private void dodajArtikal() {
        try {
            List<Artikal> artikli = controller.getAllArtikli();
            int nextId = artikli.stream()
                    .mapToInt(Artikal::getArtikalId)
                    .max()
                    .orElse(0) + 1;

            Artikal newArtikal = new Artikal();
            newArtikal.setArtikalId(nextId);

            ArtikalDialog dialog = new ArtikalDialog(this, newArtikal);
            dialog.setVisible(true);

            if (dialog.isSucceeded()) {
                controller.addArtikal(dialog.getArtikal());
                loadArtikli();
            }
        } catch (SQLException e) {
            prikaziGresku("Greška pri dodavanju artikla:\n" + e.getMessage());
        }
    }

    private void prikaziGresku(String poruka) {
        JOptionPane.showMessageDialog(this, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
