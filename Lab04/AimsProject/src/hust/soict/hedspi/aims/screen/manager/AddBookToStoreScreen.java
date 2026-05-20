package hust.soict.hedspi.aims.screen.manager;

import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    private JTextField tfAuthor;

    public AddBookToStoreScreen(Store store) {
        super(store);
        setTitle("Add Book");
    }

    @Override
    protected JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));

        center.add(createCommonFields());

        JPanel authorPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        authorPanel.add(new JLabel("Author:"));
        tfAuthor = new JTextField();
        authorPanel.add(tfAuthor);
        
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> {
            try {
                String title = tfTitle.getText();
                String category = tfCategory.getText();
                float cost = Float.parseFloat(tfCost.getText());
                String author = tfAuthor.getText();
                
                Book book = new Book(title, category, cost);
                if (author != null && !author.trim().isEmpty()) {
                    book.addAuthor(author);
                }
                
                store.addMedia(book);
                
                JOptionPane.showMessageDialog(this, "Book added successfully!");
                new StoreManagerScreen(store);
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid cost!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        center.add(authorPanel);
        center.add(addButton);

        return center;
    }
}
