
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

public class Main {

    static int result = 0;
    static String operator = "";
    static String currentInput = "";

    public static void main(String[] args) {

        JFrame frame = new JFrame("Simple Calculator");

        JTextField display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setPreferredSize(new Dimension(350, 60));

        JPanel buttonpanel = new JPanel();
        buttonpanel.setLayout(new GridLayout(4, 4, 5, 5));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        for (String text : buttons) {

            JButton button = new JButton(text);

            button.addActionListener(e -> {

                if (text.equals("C")) {
                    result = 0;
                    operator = "";
                    currentInput = "";
                    display.setText("");
                }

                else if (text.equals("+") || text.equals("-")
                        || text.equals("*") || text.equals("/")) {

                    if (!currentInput.isEmpty()) {

                        int currentNumber = Integer.parseInt(currentInput);

                        if (operator.isEmpty()) {
                            result = currentNumber;
                        } else {
                            switch (operator) {
                                case "+":
                                    result = result + currentNumber;
                                    break;

                                case "-":
                                    result = result - currentNumber;
                                    break;

                                case "*":
                                    result = result * currentNumber;
                                    break;

                                case "/":
                                    if (currentNumber == 0) {
                                        JOptionPane.showMessageDialog(frame,
                                                "Cannot divide by zero!");
                                        currentInput = "";
                                        display.setText("");
                                        return;
                                    }
                                    result = result / currentNumber;
                                    break;
                            }
                        }

                        operator = text;
                        currentInput = "";
                        display.setText(result + " " + operator);
                    }
                }

                else if (text.equals("=")) {

                    if (!currentInput.isEmpty() && !operator.isEmpty()) {

                        int currentNumber = Integer.parseInt(currentInput);

                        switch (operator) {
                            case "+":
                                result = result + currentNumber;
                                break;

                            case "-":
                                result = result - currentNumber;
                                break;

                            case "*":
                                result = result * currentNumber;
                                break;

                            case "/":
                                if (currentNumber == 0) {
                                    JOptionPane.showMessageDialog(frame,
                                            "Cannot divide by zero!");
                                    currentInput = "";
                                    display.setText("");
                                    return;
                                }
                                result = result / currentNumber;
                                break;
                        }

                        display.setText(String.valueOf(result));
                        currentInput = String.valueOf(result);
                        operator = "";
                    }
                }

                else {
                    currentInput += text;

                    if (operator.isEmpty()) {
                        display.setText(currentInput);
                    } else {
                        display.setText(result + " " + operator
                                + " " + currentInput);
                    }
                }
            });

            buttonpanel.add(button);
        }

        frame.add(display, BorderLayout.NORTH);
        frame.add(buttonpanel, BorderLayout.CENTER);

        frame.setSize(350, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
