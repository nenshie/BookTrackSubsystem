package ui;

import domain.entities.Artikal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class ArtikalDialog extends JDialog {

    private final JTextField txtId;
    private final JTextField txtNaziv;
    private final JTextField txtBarkod;
    private final JTextField txtJedinicaMere;

    private boolean succeeded;
    private final Artikal artikal;

    public ArtikalDialog(Frame parent, Artikal artikal) {
        super(parent, true);
        setTitle("Dodaj artikal");
        this.artikal = artikal;

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(panel);

        GridBagConstraints gc = new GridBagConstraints();
        gc.insets = new Insets(5, 5, 5, 5);
        gc.fill = GridBagConstraints.HORIZONTAL;

        // ID field (non-editable)
        txtId = new JTextField(String.valueOf(artikal.getArtikalId()));
        txtId.setEditable(false);
        gc.gridx = 0; gc.gridy = 0;
        panel.add(new JLabel("ID:"), gc);
        gc.gridx = 1;
        panel.add(txtId, gc);

        // Naziv
        txtNaziv = new JTextField(20);
        gc.gridx = 0; gc.gridy = 1;
        panel.add(new JLabel("Naziv:"), gc);
        gc.gridx = 1;
        panel.add(txtNaziv, gc);

        // Barkod
        txtBarkod = new JTextField(20);
        gc.gridx = 0; gc.gridy = 2;
        panel.add(new JLabel("Barkod:"), gc);
        gc.gridx = 1;
        panel.add(txtBarkod, gc);

        // Jedinica mere
        txtJedinicaMere = new JTextField(20);
        gc.gridx = 0; gc.gridy = 3;
        panel.add(new JLabel("Jedinica mere:"), gc);
        gc.gridx = 1;
        panel.add(txtJedinicaMere, gc);

        // Buttons panel
        JButton btnOk = new JButton("Sačuvaj");
        btnOk.addActionListener(e -> onSave());
        JButton btnCancel = new JButton("Otkaži");
        btnCancel.addActionListener(e -> onCancel());

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        buttons.add(btnOk);
        buttons.add(btnCancel);

        gc.gridx = 0; gc.gridy = 4; gc.gridwidth = 2;
        panel.add(buttons, gc);

        // Keyboard shortcuts
        getRootPane().setDefaultButton(btnOk);
        panel.registerKeyboardAction(e -> onCancel(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        // Focus first field
        SwingUtilities.invokeLater(txtNaziv::requestFocus);

        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
    }

    private void onSave() {
        String naziv = txtNaziv.getText().trim();
        String barkod = txtBarkod.getText().trim();
        String jedinica = txtJedinicaMere.getText().trim();

        if (naziv.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Naziv ne može biti prazan.", "Greška", JOptionPane.ERROR_MESSAGE);
            txtNaziv.requestFocus();
            return;
        }

        if (jedinica.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Jedinica mere ne može biti prazna.", "Greška", JOptionPane.ERROR_MESSAGE);
            txtJedinicaMere.requestFocus();
            return;
        }

        artikal.setNaziv(naziv);
        artikal.setBarkod(barkod);
        artikal.setJedinicaMere(jedinica);

        succeeded = true;
        dispose();
    }

    private void onCancel() {
        succeeded = false;
        dispose();
    }

    public boolean isSucceeded() {
        return succeeded;
    }

    public Artikal getArtikal() {
        return artikal;
    }
}
