package ui;

import controller.PredracunController;
import domain.entities.Predracun;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class PredracunForm extends JFrame {

    private final PredracunController controller = new PredracunController();
    private final DefaultTableModel model;
    private final JTable table;
    private JComboBox<String> cbPartitions;
    private JButton btnOpenStavke;

    public PredracunForm() throws SQLException {
        setTitle("Pregled predračuna");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Panel sa filterom
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Filtriraj po particiji:"));
        cbPartitions = new JComboBox<>();
        cbPartitions.addItem("Sve"); // prva opcija, vrati sve
        for (String part : controller.getAllPartitions()) {
            cbPartitions.addItem(part);
        }
        topPanel.add(cbPartitions);

        JButton btnFilter = new JButton("Filtriraj");
        btnFilter.addActionListener(e -> {
            try {
                if (cbPartitions.getSelectedItem().equals("Sve")) {
                    loadAllPredracuni();
                } else {
                    loadPredracuniByPartition((String) cbPartitions.getSelectedItem());
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Greška pri učitavanju predračuna:\n" + ex.getMessage());
            }
        });
        topPanel.add(btnFilter);
        add(topPanel, BorderLayout.NORTH);

        // Tabela
        String[] columns = {"Broj", "Datum", "Odgovorni radnik", "Radnik kreirao", "Dobavljac", "Ulica", "Mesto", "Broj"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return false; // tabela nije editable
            }
        };
        table = new JTable(model);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Dugme za otvaranje stavki selektovanog predračuna
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnOpenStavke = new JButton("Otvori stavke selektovanog predračuna");
        btnOpenStavke.addActionListener(e -> openStavke());
        bottomPanel.add(btnOpenStavke);
        add(bottomPanel, BorderLayout.SOUTH);

        // Učitavanje svih predračuna po default
        loadAllPredracuni();
    }

    private void loadAllPredracuni() throws SQLException {
        model.setRowCount(0);
        List<Predracun> lista = controller.getAllPredracuni();
        for (Predracun p : lista) {
            model.addRow(new Object[]{
                    p.getBrojPredracuna(),
                    p.getDatumOd(),
                    p.getOdgovorniRadnik(),
                    p.getRadnikKreirao(),
                    p.getDobavljac(),
                    p.getUlica(),
                    p.getMesto(),
                    p.getBroj()
            });
        }
    }

    private void loadPredracuniByPartition(String partitionName) throws SQLException {
        model.setRowCount(0);
        List<Predracun> lista = controller.getAllByPartition(partitionName);
        for (Predracun p : lista) {
            model.addRow(new Object[]{
                    p.getBrojPredracuna(),
                    p.getDatumOd(),
                    p.getOdgovorniRadnik(),
                    p.getRadnikKreirao(),
                    p.getDobavljac(),
                    p.getUlica(),
                    p.getMesto(),
                    p.getBroj()
            });
        }
    }

    private void openStavke() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Izaberite predračun iz tabele!", "Greška", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int brojPredracuna = (int) model.getValueAt(selectedRow, 0);
        StavkaPredracunaForm stavkeForm = new StavkaPredracunaForm(brojPredracuna);
        stavkeForm.setVisible(true);
    }
}
