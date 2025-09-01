package ui;

import controller.ArtikalController;
import controller.StavkaPredracunaController;
import domain.entities.Artikal;
import domain.entities.StavkaPredracuna;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class StavkaPredracunaForm extends JFrame {

    private final StavkaPredracunaController controller = new StavkaPredracunaController();
    private final DefaultTableModel model;
    private final JTable table;
    private final int brojPredracuna;

    public StavkaPredracunaForm(int brojPredracuna) {
        this.brojPredracuna = brojPredracuna;

        setTitle("Stavke predračuna: " + brojPredracuna);
        setSize(800, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNaslov = new JLabel("Stavke predračuna broj: " + brojPredracuna);
        lblNaslov.setFont(new Font("Arial", Font.BOLD, 16));
        lblNaslov.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        headerPanel.add(lblNaslov);
        add(headerPanel, BorderLayout.NORTH);

        String[] columnNames = {"RB", "Količina", "Rabat", "ArtikalID", "PDV", "Naziv", "Barkod"};
        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 5 || col == 6;
            }
        };

        table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.setPreferredScrollableViewportSize(new Dimension(690, 250));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        table.getColumnModel().getColumn(0).setPreferredWidth(40);
        table.getColumnModel().getColumn(1).setPreferredWidth(60);
        table.getColumnModel().getColumn(2).setPreferredWidth(60);
        table.getColumnModel().getColumn(3).setPreferredWidth(60);
        table.getColumnModel().getColumn(4).setPreferredWidth(50);
        table.getColumnModel().getColumn(5).setPreferredWidth(200);
        table.getColumnModel().getColumn(6).setPreferredWidth(120);

        JScrollPane scrollPane = new JScrollPane(table);
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        add(tablePanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnRefresh = new JButton("Osveži");
        btnRefresh.addActionListener(e -> loadStavke());

        JButton btnAdd = new JButton("Dodaj");
        btnAdd.addActionListener(e -> dodajStavku());

        btnPanel.add(btnRefresh);
        btnPanel.add(btnAdd);
        btnPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 10, 10));
        add(btnPanel, BorderLayout.SOUTH);

        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                try {
                    StavkaPredracuna updated = new StavkaPredracuna();
                    updated.setBrojPredracuna(brojPredracuna);
                    updated.setRb((int) model.getValueAt(row, 0));
                    updated.setKolicina((int) model.getValueAt(row, 1));
                    updated.setRabat((String) model.getValueAt(row, 2));
                    updated.setArtikal((int) model.getValueAt(row, 3));
                    updated.setPdv((int) model.getValueAt(row, 4));
                    updated.setNaziv((String) model.getValueAt(row, 5));
                    updated.setBarkod((String) model.getValueAt(row, 6));

                    controller.updateStavka(updated);
                    loadStavke();
                } catch (SQLException ex) {
                    prikaziGresku("Greška pri izmeni stavke:\n" + ex.getMessage());
                    loadStavke();
                }
            }
        });

        loadStavke();
    }

    private void loadStavke() {
        try {
            model.setRowCount(0);
            List<StavkaPredracuna> stavke = controller.getStavkeByPredracun(brojPredracuna);
            for (StavkaPredracuna s : stavke) {
                model.addRow(new Object[]{
                        s.getRb(),
                        s.getKolicina(),
                        s.getRabat(),
                        s.getArtikal(),
                        s.getPdv(),
                        s.getNaziv(),
                        s.getBarkod()
                });
            }
        } catch (SQLException e) {
            prikaziGresku("Greška pri učitavanju stavki:\n" + e.getMessage());
        }
    }

    private void dodajStavku() {
        try {
            List<Artikal> artikli = new ArtikalController().getAllArtikli();
            StavkaPredracunaDialog dialog = new StavkaPredracunaDialog(this, brojPredracuna, artikli);
            dialog.setVisible(true);

            if (dialog.isSucceeded()) {
                StavkaPredracuna novaStavka = dialog.getStavka();
                try {
                    controller.addStavka(novaStavka);
                    JOptionPane.showMessageDialog(this, "Stavka uspešno dodata!", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
                    loadStavke();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Greška pri dodavanju stavke:\n" + ex.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju artikala:\n" + e.getMessage(), "Greška", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void prikaziGresku(String poruka) {
        JOptionPane.showMessageDialog(this, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
