package ui;

import controller.PrijemniListController;
import domain.entities.PrijemniList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PrijemniListForm extends JFrame {

    private List<PrijemniList> prijemniListList;
    private PrijemniListController controller;

    private JTable tablePrijemniList;
    private JLabel lblNaslov;

    public PrijemniListForm() {
        prijemniListList = new ArrayList<>();
        controller = new PrijemniListController();

        initComponents();
        setTitle("Prijemni list");
        setSize(950, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        lblNaslov = new JLabel("Pregled prijemnih listova");
        lblNaslov.setFont(new Font("Arial", Font.BOLD, 18));
        lblNaslov.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        lblNaslov.setHorizontalAlignment(SwingConstants.LEFT);
        add(lblNaslov, BorderLayout.NORTH);

        tablePrijemniList = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablePrijemniList);
        scrollPane.setPreferredSize(new Dimension(850, 200));
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlTabela = new JPanel();
        pnlTabela.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        pnlTabela.add(scrollPane);
        pnlTabela.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(pnlTabela, BorderLayout.WEST);

        try {
            ucitajPrijemneListove();
            popuniTabelu();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju prijemnih listova: " + e.getMessage());
        }

        tablePrijemniList.getModel().addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE && e.getColumn() == 6) {
                int red = e.getFirstRow();
                try {
                    DefaultTableModel model = (DefaultTableModel) tablePrijemniList.getModel();
                    int novaVrednost = Integer.parseInt(model.getValueAt(red, 6).toString());

                    PrijemniList pl = prijemniListList.get(red);
                    pl.setUkupnoStavki(novaVrednost);

                    controller.updatePrijemniList(pl);

                    ucitajPrijemneListove();
                    popuniTabelu();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Greška: " + ex.getMessage());
                    try {
                        ucitajPrijemneListove();
                        popuniTabelu();
                    } catch (Exception ignored) {}
                }
            }
        });
    }

    public void ucitajPrijemneListove() throws Exception {
        prijemniListList = controller.getAllPrijemniListovi();
    }

    public void popuniTabelu() {
        String[] kolone = {"Prijemni list Id", "Datum od", "Osnov", "Komentar", "Dostavnica", "Radnik", "Ukupno Stavki"};
        DefaultTableModel model = new DefaultTableModel(kolone, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        for (PrijemniList pl : prijemniListList) {
            Object[] red = {
                    pl.getPrijemniListId(),
                    pl.getDatumOd(),
                    pl.getOsnov(),
                    pl.getKomentar(),
                    pl.getDostavnica(),
                    pl.getRadnik(),
                    pl.getUkupnoStavki()
            };
            model.addRow(red);
        }

        tablePrijemniList.setModel(model);
    }
}
