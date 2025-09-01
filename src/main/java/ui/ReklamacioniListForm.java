package ui;

import controller.ReklamacioniListController;
import domain.entities.DetaljiReklamacionogLista;
import domain.entities.ReklamacioniList;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ReklamacioniListForm extends JFrame {

    private List<ReklamacioniList> reklamacioniListList;
    private ReklamacioniListController controller;

    private JTable tableReklamacioniList;
    private JButton btnIzmeni;
    private JButton btnObrisi;
    private JLabel lblNaslov;

    public ReklamacioniListForm() {
        reklamacioniListList = new ArrayList<>();
        controller = new ReklamacioniListController();

        initComponents();
        setTitle("Reklamacioni list");
        setSize(1100, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        lblNaslov = new JLabel("Pregled reklamacionih listova");
        lblNaslov.setFont(new Font("Arial", Font.BOLD, 18));
        lblNaslov.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(lblNaslov, BorderLayout.NORTH);

        tableReklamacioniList = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableReklamacioniList);
        scrollPane.setPreferredSize(new Dimension(1000, 250));

        JPanel pnlTabela = new JPanel(new BorderLayout());
        pnlTabela.add(scrollPane, BorderLayout.CENTER);
        pnlTabela.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel pnlDugmici = new JPanel();
        pnlDugmici.setLayout(new BoxLayout(pnlDugmici, BoxLayout.Y_AXIS));

        btnIzmeni = new JButton("Izmeni");
        btnObrisi = new JButton("Obriši");

        btnIzmeni.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnObrisi.setAlignmentX(Component.CENTER_ALIGNMENT);

        pnlDugmici.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlDugmici.add(btnIzmeni);
        pnlDugmici.add(Box.createRigidArea(new Dimension(0, 10)));
        pnlDugmici.add(btnObrisi);

        JPanel pnlCenter = new JPanel(new BorderLayout());
        pnlCenter.add(pnlTabela, BorderLayout.CENTER);
        pnlCenter.add(pnlDugmici, BorderLayout.EAST);

        add(pnlCenter, BorderLayout.CENTER);

        try {
            ucitajReklamacioneListove();
            popuniTabelu();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Greška pri učitavanju: " + e.getMessage());
        }

        btnObrisi.addActionListener(e -> obrisiSelektovaniRed());
    }

    private void ucitajReklamacioneListove() throws Exception {
        reklamacioniListList = controller.getAllReklamacioniListovi();
    }

    private void popuniTabelu() {
        String[] kolone = {"ReklamacioniListId", "PrijemniListId", "Radnik", "Artikal",
                "Tip rešenja", "Opis", "Napomena", "Datum prijema", "Status"};

        DefaultTableModel model = new DefaultTableModel(kolone, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0 && column != 8;
            }
        };

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        for (ReklamacioniList r : reklamacioniListList) {
            DetaljiReklamacionogLista d = r.getDetalji();
            String status = "";
            if (r.getReklamacioniListId() != 0) {
                try {
                    status = controller.proveriDatum(r.getReklamacioniListId());
                } catch (Exception ex) {
                    status = "Greška: " + ex.getMessage();
                }
            }

            Object datumPrijema = null;
            if (d != null && d.getDatumPrijema() != null) {
                datumPrijema = dateFormat.format(d.getDatumPrijema());
            }

            Object[] red = {
                    r.getReklamacioniListId(),
                    r.getPrijemniList(),
                    r.getRadnik(),
                    r.getArtikal(),
                    r.getTipResenja(),
                    d != null ? d.getOpis() : "",
                    d != null ? d.getNapomena() : "",
                    datumPrijema,
                    status
            };
            model.addRow(red);
        }

        tableReklamacioniList.setModel(model);

        tableReklamacioniList.getColumnModel().getColumn(5).setPreferredWidth(200);
        tableReklamacioniList.getColumnModel().getColumn(6).setPreferredWidth(150);
        tableReklamacioniList.getColumnModel().getColumn(7).setPreferredWidth(100);
        tableReklamacioniList.getColumnModel().getColumn(8).setPreferredWidth(200);

        tableReklamacioniList.getColumnModel().getColumn(8).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                           boolean isSelected, boolean hasFocus,
                                                           int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (value != null) {
                    String status = value.toString();
                    if (status.contains("Nije")) {
                        c.setForeground(new Color(0, 128, 0));
                    } else if (status.contains(" više od 30")) {
                        c.setForeground(Color.RED);
                    } else {
                        c.setForeground(Color.BLACK);
                    }
                } else {
                    c.setForeground(Color.BLACK);
                }
                return c;
            }
        });

        model.addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                ReklamacioniList updated = getReklamacioniListFromRow(model, row);
                try {
                    controller.updateReklamacioniList(updated);
                    JOptionPane.showMessageDialog(this, "Uspešno ažuriranje!");

                    try {
                        String noviStatus = controller.proveriDatum(updated.getReklamacioniListId());
                        model.setValueAt(noviStatus, row, 8);
                    } catch (Exception ex) {
                        model.setValueAt("Greška: " + ex.getMessage(), row, 8);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Greška pri ažuriranju: " + ex.getMessage());
                }
            }
        });
    }

    private ReklamacioniList getReklamacioniListFromRow(DefaultTableModel model, int row) {
        int id = parseInt(model.getValueAt(row, 0));
        int prijemniList = parseInt(model.getValueAt(row, 1));
        int radnik = parseInt(model.getValueAt(row, 2));
        int artikal = parseInt(model.getValueAt(row, 3));
        int tipResenja = parseInt(model.getValueAt(row, 4));
        String opis = String.valueOf(model.getValueAt(row, 5));
        String napomena = String.valueOf(model.getValueAt(row, 6));

        Object datumObj = model.getValueAt(row, 7);
        Timestamp datum = null;
        if (datumObj != null) {
            try {
                datum = Timestamp.valueOf(datumObj.toString() + " 00:00:00");
            } catch (Exception ignored) {
            }
        }

        DetaljiReklamacionogLista detalji = new DetaljiReklamacionogLista(opis, napomena, datum);

        return new ReklamacioniList(id, detalji, prijemniList, radnik, artikal, tipResenja);
    }

    private void obrisiSelektovaniRed() {
        int selectedRow = tableReklamacioniList.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Izaberite red za brisanje!");
            return;
        }

        int id = (int) tableReklamacioniList.getValueAt(selectedRow, 0);
        ReklamacioniList rl = new ReklamacioniList();
        rl.setReklamacioniListId(id);

        try {
            controller.deleteReklamacioniList(rl);
            ((DefaultTableModel) tableReklamacioniList.getModel()).removeRow(selectedRow);
            JOptionPane.showMessageDialog(this, "Uspešno obrisano!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Greška pri brisanju: " + ex.getMessage());
        }
    }

    private int parseInt(Object value) {
        if (value instanceof Integer) return (Integer) value;
        if (value instanceof String) return Integer.parseInt((String) value);
        throw new IllegalArgumentException("Ne može da se parsira u int: " + value);
    }
}
