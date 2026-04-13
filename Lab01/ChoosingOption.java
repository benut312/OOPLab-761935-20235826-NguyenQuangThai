import javax.swing.JOptionPane;

public class ChoosingOption {
    public static void main(String[] args) {
        int option = JOptionPane.showConfirmDialog(
                null,
                "Do you want to change to the first class ticket?",
                "Confirmation",
                JOptionPane.YES_NO_CANCEL_OPTION
        );

        String result;
        if (option == JOptionPane.YES_OPTION) {
            result = "You chose: Yes";
        } else if (option == JOptionPane.NO_OPTION) {
            result = "You chose: No";
        } else if (option == JOptionPane.CANCEL_OPTION) {
            result = "You chose: Cancel";
        } else {
            result = "Dialog closed.";
        }

        JOptionPane.showMessageDialog(null, result);
        System.exit(0);
    }
}