import javax.swing.JOptionPane;
public class HelloNameDialog {
public static void(String args[]{
  String result; 
  result = JOptionPane.showInputDialog("Please enter your name:");
  JOptionPane.showMessageDialog(null,"Hi" + result + "!");
  System.exit(0);
 }
 }
