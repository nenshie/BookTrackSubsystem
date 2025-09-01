package ui;

import domain.entities.Zaliha;
import domain.entities.Artikal;
import domain.entities.Magacin;

import javax.swing.*;
import java.awt.*;
import java.math.BigDecimal;
import java.util.List;

public class ZalihaDialog extends JDialog {

    private JComboBox<Artikal> cmbArtikal = new JComboBox<>();
    private JComboBox<Magacin> cmbMagacin = new JComboBox<>();
    private JTextField txtCena = new JTextField(10);
    private JTextField txtKolicina = new JTextField(10);
    private JTextField txtNaziv = new JTextField(10);
    private JTextField txtNazivArtikla = new JTextField(10);

    private boolean succeeded;
    private Zaliha zaliha = new Zaliha();

    public ZalihaDialog(Frame parent, List<Artikal> artikli, List<Magacin> magacini) {
        super(parent, true);
        setTitle("Dodaj zalihu");
        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;

        // Magacin
        gc.gridx = 0; gc.gridy = 0;
        add(new JLabel("Magacin:"), gc);
        gc.gridx = 1;
        for (Magacin m : magacini) cmbMagacin.addItem(m);
        add(cmbMagacin, gc);

        // Artikal
        gc.gridx = 0; gc.gridy = 1;
        add(new JLabel("Artikal:"), gc);
        gc.gridx = 1;
        for (Artikal a : artikli) cmbArtikal.addItem(a);
        add(cmbArtikal, gc);

        // Cena
        gc.gridx = 0; gc.gridy = 2;
        add(new JLabel("Prosečna nabavna cena:"), gc);
        gc.gridx = 1;
        add(txtCena, gc);

        // Kolicina
        gc.gridx = 0; gc.gridy = 3;
        add(new JLabel("Raspoloživa količina:"), gc);
        gc.gridx = 1;
        add(txtKolicina, gc);

        // Naziv magacina (readonly)
        gc.gridx = 0; gc.gridy = 4;
        add(new JLabel("Naziv magacina:"), gc);
        gc.gridx = 1;
        txtNaziv.setEnabled(false);
        add(txtNaziv, gc);

        // Naziv artikla (readonly)
        gc.gridx = 0; gc.gridy = 5;
        add(new JLabel("Naziv artikla:"), gc);
        gc.gridx = 1;
        txtNazivArtikla.setEnabled(false);
        add(txtNazivArtikla, gc);

        // Fill name fields when selection changes
        cmbMagacin.addActionListener(e -> {
            Magacin m = (Magacin) cmbMagacin.getSelectedItem();
            if (m != null) txtNaziv.setText(m.getNaziv());
        });
        cmbArtikal.addActionListener(e -> {
            Artikal a = (Artikal) cmbArtikal.getSelectedItem();
            if (a != null) txtNazivArtikla.setText(a.getNaziv());
        });

        // Buttons
        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> {
            try {
                Magacin selectedMagacin = (Magacin) cmbMagacin.getSelectedItem();
                Artikal selectedArtikal = (Artikal) cmbArtikal.getSelectedItem();

                zaliha.setMagacinId(selectedMagacin.getMagacinId());
                zaliha.setArtikalId(selectedArtikal.getArtikalId());
                zaliha.setProsecnaNabavnaCena(new BigDecimal(txtCena.getText()));
                zaliha.setRaspolozivaKolicina(Integer.parseInt(txtKolicina.getText()));
                zaliha.setNaziv(selectedMagacin.getNaziv());
                zaliha.setNazivArtikla(selectedArtikal.getNaziv());

                succeeded = true;
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(ZalihaDialog.this, "Neispravni brojčani podaci.");
            }
        });

        JButton btnCancel = new JButton("Otkaži");
        btnCancel.addActionListener(e -> {
            succeeded = false;
            dispose();
        });

        JPanel buttons = new JPanel();
        buttons.add(btnOk);
        buttons.add(btnCancel);

        gc.gridx = 0; gc.gridy = 6; gc.gridwidth = 2;
        add(buttons, gc);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public Zaliha getZaliha() {
        return zaliha;
    }
}
