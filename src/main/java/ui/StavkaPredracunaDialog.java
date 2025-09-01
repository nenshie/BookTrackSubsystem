package ui;

import domain.entities.Artikal;
import domain.entities.StavkaPredracuna;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class StavkaPredracunaDialog extends JDialog {

    private boolean succeeded;
    private StavkaPredracuna stavka;

    private JTextField txtRb;
    private JComboBox<Artikal> cbArtikal;
    private JTextField txtKolicina;
    private JTextField txtRabat;
    private JTextField txtPDV;
    private JTextField txtNaziv;
    private JTextField txtBarkod;

    private final int brojPredracuna;

    public StavkaPredracunaDialog(Frame parent, int brojPredracuna, List<Artikal> artikli) {
        super(parent, "Dodaj stavku predračuna: " + brojPredracuna, true);
        this.brojPredracuna = brojPredracuna;

        setSize(450, 380);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtRb = new JTextField();
        cbArtikal = new JComboBox<>(artikli.toArray(new Artikal[0]));
        txtKolicina = new JTextField();
        txtRabat = new JTextField();
        txtPDV = new JTextField();
        txtNaziv = new JTextField();
        txtNaziv.setEnabled(false);
        txtBarkod = new JTextField();
        txtBarkod.setEnabled(false);

        formPanel.add(new JLabel("RB:"));
        formPanel.add(txtRb);
        formPanel.add(new JLabel("Artikal:"));
        formPanel.add(cbArtikal);
        formPanel.add(new JLabel("Količina:"));
        formPanel.add(txtKolicina);
        formPanel.add(new JLabel("Rabat:"));
        formPanel.add(txtRabat);
        formPanel.add(new JLabel("PDV:"));
        formPanel.add(txtPDV);
        formPanel.add(new JLabel("Naziv artikla:"));
        formPanel.add(txtNaziv);
        formPanel.add(new JLabel("Barkod:"));
        formPanel.add(txtBarkod);

        add(formPanel, BorderLayout.CENTER);

        // Popuni naziv i barkod kada se promeni artikal
        cbArtikal.addActionListener(e -> {
            Artikal a = (Artikal) cbArtikal.getSelectedItem();
            if (a != null) {
                txtNaziv.setText(a.getNaziv());
                txtBarkod.setText(a.getBarkod());
            }
        });

        // Buttons
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnOk = new JButton("Dodaj");
        JButton btnCancel = new JButton("Otkaži");

        btnOk.addActionListener(e -> {
            try {
                Artikal selectedArtikal = (Artikal) cbArtikal.getSelectedItem();
                if (selectedArtikal == null) {
                    JOptionPane.showMessageDialog(this, "Izaberite artikal.", "Greška", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                stavka = new StavkaPredracuna();
                stavka.setBrojPredracuna(brojPredracuna);
                stavka.setRb(Integer.parseInt(txtRb.getText()));
                stavka.setArtikal(selectedArtikal.getArtikalId());
                stavka.setKolicina(Integer.parseInt(txtKolicina.getText()));
                stavka.setRabat(txtRabat.getText());
                stavka.setPdv(Integer.parseInt(txtPDV.getText()));
                stavka.setNaziv(txtNaziv.getText());
                stavka.setBarkod(txtBarkod.getText());

                succeeded = true;
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "RB, Količina i PDV moraju biti brojevi.", "Greška", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancel.addActionListener(e -> {
            succeeded = false;
            dispose();
        });

        btnPanel.add(btnOk);
        btnPanel.add(btnCancel);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public StavkaPredracuna getStavka() {
        return stavka;
    }
}
