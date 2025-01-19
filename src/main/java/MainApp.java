import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class MainApp {
    public static void main(String[] args) {
        // Create main frame
        JFrame frame = new JFrame("GUI Project Option #2");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        Container contentPane = frame.getContentPane();
        contentPane.setBackground(Color.WHITE);
        contentPane.isOpaque();

        // Create text box
        JTextArea textArea = new JTextArea(10, 40);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.add(scrollPane, BorderLayout.NORTH);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menu: "Options"
        JMenu menu = new JMenu("Options");
        menuBar.add(menu);

        // Create menu items
        JMenuItem dateTimeItem = new JMenuItem("Show Date and Time");
        JMenuItem writeFileItem = new JMenuItem("Save to log.txt");
        JMenuItem changeColorItem = new JMenuItem("Change Background Color");
        JMenuItem exitItem = new JMenuItem("Exit");

        // Add menu items to menu
        menu.add(dateTimeItem);
        menu.add(writeFileItem);
        menu.add(changeColorItem);
        menu.add(exitItem);

        frame.setJMenuBar(menuBar);

        // Add action listeners for menu items
        dateTimeItem.addActionListener(_ -> {
            String currentDateTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        textArea.setText(currentDateTime);
        });

        // Write to File
        writeFileItem.addActionListener(_ -> {
            String content = textArea.getText().trim();
            if (!content.isEmpty()) {
                try (FileWriter writer = new FileWriter("log.txt", true)) {
                    writer.write(content + "\n");
                    JOptionPane.showMessageDialog(frame, "Saved to log.txt", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(frame, "Error writing to file.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Text area is empty.", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Change Background Color
        changeColorItem.addActionListener(_ -> {
            Random random = new Random();
            int green = random.nextInt(73) + 28;
            Color orange = new Color(255, green, 0);
            frame.getContentPane().setBackground(orange);
            JOptionPane.showMessageDialog(frame, "Background color changed to: " + orange, "Color changed", JOptionPane.INFORMATION_MESSAGE);
        });

        // Exit Program
        exitItem.addActionListener(_ -> System.exit(0));

        // Show Frame
        frame.setVisible(true);
    }
}
