import javax.swing.*;
import java.awt.*;

public class Lab2 {
    private final JTextField display;
    private double firstValue = 0;
    private String operator = "";
    private boolean startNewNumber = true;

    public Lab2() {
        JFrame frame = new JFrame("Kitty Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(new Color(255, 235, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel title = new JLabel("🐱 Kitty Calculator 💖", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setForeground(new Color(190, 70, 120));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(10));

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createLineBorder(new Color(240, 140, 180), 2));
        display.setPreferredSize(new Dimension(320, 55));
        display.setMaximumSize(new Dimension(320, 55));
        mainPanel.add(display);
        mainPanel.add(Box.createVerticalStrut(12));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 8, 8));
        buttonPanel.setBackground(new Color(255, 235, 245));

        String[] labels = {"7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", "C", "=", "+"};

        for (String label : labels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setFocusPainted(false);
            button.setBackground(label.equals("C") ? new Color(255, 182, 193) : new Color(255, 240, 245));
            button.setForeground(new Color(120, 40, 80));
            button.setBorder(BorderFactory.createLineBorder(new Color(240, 140, 180), 1));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));

            button.addActionListener(e -> handleButton(label));
            buttonPanel.add(button);
        }

        mainPanel.add(buttonPanel);
        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void handleButton(String label) {
        if (label.matches("[0-9]")) {
            if (startNewNumber) {
                display.setText(label);
                startNewNumber = false;
            } else {
                display.setText(display.getText() + label);
            }
        } else if (label.equals("C")) {
            display.setText("0");
            firstValue = 0;
            operator = "";
            startNewNumber = true;
        } else if (label.equals("=")) {
            if (!operator.isEmpty()) {
                double secondValue = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        firstValue += secondValue;
                        break;
                    case "-":
                        firstValue -= secondValue;
                        break;
                    case "*":
                        firstValue *= secondValue;
                        break;
                    case "/":
                        if (secondValue == 0) {
                            display.setText("Error");
                            operator = "";
                            startNewNumber = true;
                            return;
                        }
                        firstValue /= secondValue;
                        break;
                }
                display.setText(formatValue(firstValue));
                operator = "";
                startNewNumber = true;
            }
        } else {
            if (!operator.isEmpty() && !startNewNumber) {
                double secondValue = Double.parseDouble(display.getText());
                switch (operator) {
                    case "+":
                        firstValue += secondValue;
                        break;
                    case "-":
                        firstValue -= secondValue;
                        break;
                    case "*":
                        firstValue *= secondValue;
                        break;
                    case "/":
                        if (secondValue == 0) {
                            display.setText("Error");
                            operator = "";
                            startNewNumber = true;
                            return;
                        }
                        firstValue /= secondValue;
                        break;
                }
                display.setText(formatValue(firstValue));
            } else {
                firstValue = Double.parseDouble(display.getText());
            }
            operator = label;
            startNewNumber = true;
        }
    }

    private String formatValue(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        }
        return String.format("%.2f", value).replaceAll("0+$", "").replaceAll("\\.$", "");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Lab2::new);
    }
}
