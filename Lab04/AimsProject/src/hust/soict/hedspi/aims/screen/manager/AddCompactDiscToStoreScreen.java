package hust.soict.hedspi.aims.screen.manager;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfArtist;
    private JTextField tfDirector;

    public AddCompactDiscToStoreScreen(Store store) {
        super(store);
        setTitle("Add CD");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.add(createCommonFields());

        JPanel extraPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        
        extraPanel.add(new JLabel("Artist:"));
        tfArtist = new JTextField();
        extraPanel.add(tfArtist);
        
        extraPanel.add(new JLabel("Director:"));
        tfDirector = new JTextField();
        extraPanel.add(tfDirector);
        
        JButton addButton = new JButton("Add CD");
        addButton.addActionListener(e -> {
            try {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                float cost = Float.parseFloat(tfCost.getText());
                String artist = tfArtist.getText();
                String director = tfDirector.getText();
                
                CompactDisc cd = new CompactDisc(title, category, artist, director, cost);
                store.addMedia(cd);
                
                JOptionPane.showMessageDialog(this, "CD added successfully!");
                new StoreManagerScreen(store);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(extraPanel);
        center.add(addButton);

        return center;
    }
}
