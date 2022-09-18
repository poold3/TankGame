import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Demo program for JFrame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(emptyLabel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
