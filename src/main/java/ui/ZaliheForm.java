package ui;

import controller.ArtikalController;
import controller.MagacinController;
import controller.ZalihaController;
import domain.entities.Artikal;
import domain.entities.Magacin;
import domain.entities.Zaliha;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class ZaliheForm extends JFrame {

    private final ZalihaController controller = new ZalihaController();
    private final ArtikalController artikalController = new ArtikalController();
    private final MagacinController magacinController = new MagacinController();
    private final DefaultTableModel model;
    private final JTable table;

    public ZaliheForm() {
        setTitle("Pregled zaliha");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columnNames = {
                "ArtikalID",
                "MagacinID",
                "Prosečna nabavna cena",
                "Raspoloživa količina",
                "Naziv",
                "Naziv artikla"
        };

        model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col == 4;
            }
        };

        table = new JTable(model);

        table.setPreferredScrollableViewportSize(new Dimension(800, 400));
        table.setFillsViewportHeight(true);

        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(180);
        table.getColumnModel().getColumn(4).setPreferredWidth(200);
        table.getColumnModel().getColumn(5).setPreferredWidth(250);

        JLabel naslovLabel = new JLabel("Pregled zaliha");
        naslovLabel.setFont(new Font("Arial", Font.BOLD, 18));
        naslovLabel.setHorizontalAlignment(SwingConstants.LEFT);
        naslovLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(naslovLabel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel tablePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        tablePanel.add(scrollPane);

        centerPanel.add(tablePanel, BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();

        JButton btnRefresh = new JButton("Osveži");
        btnRefresh.addActionListener(e -> loadZalihe());

        JButton btnAdd = new JButton("Dodaj");
        btnAdd.addActionListener(e -> dodajZalihu());


        btnPanel.add(btnRefresh);
        btnPanel.add(btnAdd);

        add(btnPanel, BorderLayout.SOUTH);

        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int col = e.getColumn();

                if (col == 4) {
                    try {
                        int artikalId = (int) model.getValueAt(row, 0);
                        int magacinId = (int) model.getValueAt(row, 1);
                        java.math.BigDecimal cena = (java.math.BigDecimal) model.getValueAt(row, 2);
                        int kolicina = (int) model.getValueAt(row, 3);
                        String naziv = (String) model.getValueAt(row, 4);
                        String nazivArtikla = (String) model.getValueAt(row, 5);

                        Zaliha updated = new Zaliha(
                                artikalId,
                                magacinId,
                                cena,
                                kolicina,
                                naziv,
                                nazivArtikla
                        );

                        controller.updateZaliha(updated);

                    } catch (SQLException ex) {
                        prikaziGresku("Greška pri izmeni naziva:\n" + ex.getMessage());
                        loadZalihe();
                    }
                }
            }
        });

        loadZalihe();
    }

    private void loadZalihe() {
        try {
            model.setRowCount(0);
            List<Zaliha> zalihe = controller.getAllZaliheWithArtikalNaziv();
            for (Zaliha z : zalihe) {
                model.addRow(new Object[]{
                        z.getArtikalId(),
                        z.getMagacinId(),
                        z.getProsecnaNabavnaCena(),
                        z.getRaspolozivaKolicina(),
                        z.getNaziv(),
                        z.getNazivArtikla()
                });
            }
        } catch (SQLException e) {
            prikaziGresku("Greška pri učitavanju zaliha:\n" + e.getMessage());
        }
    }

    private void dodajZalihu() {
        try {
            List<Artikal> artikli = artikalController.getAllArtikli();
            List<Magacin> magacini = magacinController.getAllMagacini();

            ZalihaDialog dialog = new ZalihaDialog(this, artikli, magacini);
            dialog.setVisible(true);

            if (dialog.isSucceeded()) {
                controller.addZaliha(dialog.getZaliha());
                loadZalihe();
            }
        } catch (SQLException e) {
            prikaziGresku("Greška pri učitavanju podataka za dodavanje:\n" + e.getMessage());
        }
    }


    private void prikaziGresku(String poruka) {
        JOptionPane.showMessageDialog(this, poruka, "Greška", JOptionPane.ERROR_MESSAGE);
    }
}
