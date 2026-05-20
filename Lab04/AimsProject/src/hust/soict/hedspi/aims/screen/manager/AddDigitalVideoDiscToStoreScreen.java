package hust.soict.hedspi.aims.screen.manager;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add DVD");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.add(createCommonFields());

        JPanel extraPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        extraPanel.add(new JLabel("Director:"));
        tfDirector = new JTextField();
        extraPanel.add(tfDirector);
        
        extraPanel.add(new JLabel("Length:"));
        tfLength = new JTextField();
        extraPanel.add(tfLength);
        
        JButton addButton = new JButton("Add DVD");
        addButton.addActionListener(e -> {
            try {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                float cost = Float.parseFloat(tfCost.getText());
                String director = tfDirector.getText();
                int length = Integer.parseInt(tfLength.getText());
                
                DigitalVideoDisc dvd = new DigitalVideoDisc(title, category, director, length, cost);
                store.addMedia(dvd);
                
                JOptionPane.showMessageDialog(this, "DVD added successfully!");
                new StoreManagerScreen(store);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost or length!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(extraPanel);
        center.add(addButton);

        return center;
    }
}
