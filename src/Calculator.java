import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField tfield;
    JButton[] numberButton = new JButton[10];
    JButton[] funcButton = new JButton[9];
    JButton addButton, subButton, diffButton, multButton;
    JButton decButton, eqButton, delButton, clrButton, negButton;
    JPanel panel;
    Font myFont = new Font("Arial", Font.PLAIN, 20);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 550);
        frame.setLayout(null);
        frame.setBackground(Color.BLACK);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        tfield = new JTextField();
        tfield.setBounds(50, 25, 300, 50);
        tfield.setFont(myFont);
        tfield.setEditable(false);

        addButton = new JButton("+");
        subButton = new JButton("-");
        multButton = new JButton("*");
        diffButton = new JButton("/");
        decButton = new JButton(".");
        eqButton = new JButton("=");
        delButton = new JButton("DEL");
        clrButton = new JButton("CLR");
        negButton = new JButton("(-)");

        funcButton[0] = addButton;
        funcButton[1] = subButton;
        funcButton[2] = multButton;
        funcButton[3] = diffButton;
        funcButton[4] = decButton;
        funcButton[5] = eqButton;
        funcButton[6] = delButton;
        funcButton[7] = clrButton;
        funcButton[8] = negButton;

        for (int i = 0; i < 9; i++) {
            funcButton[i].addActionListener(this);
            funcButton[i].setFont(myFont);
            funcButton[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButton[i] = new JButton(String.valueOf(i));
            numberButton[i].addActionListener(this);
            numberButton[i].setFont(myFont);
            numberButton[i].setFocusable(false);
        }
        negButton.setBounds(50, 430, 90, 50);
        delButton.setBounds(155, 430, 90, 50);
        clrButton.setBounds(260, 430, 90, 50);

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        panel.add(numberButton[1]);
        panel.add(numberButton[2]);
        panel.add(numberButton[3]);
        panel.add(addButton);
        panel.add(numberButton[4]);
        panel.add(numberButton[5]);
        panel.add(numberButton[6]);
        panel.add(subButton);
        panel.add(numberButton[7]);
        panel.add(numberButton[8]);
        panel.add(numberButton[9]);
        panel.add(multButton);
        panel.add(decButton);
        panel.add(numberButton[0]);
        panel.add(eqButton);
        panel.add(diffButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(tfield);
        frame.add(negButton);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //SwingUtilities.invokeLater(Calc::new);
        Calculator calc = new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButton[i]) {
                tfield.setText(tfield.getText().concat(String.valueOf(i)));
            }
        }

        if (e.getSource() == decButton) {
            tfield.setText(tfield.getText().concat("."));
        }

        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(tfield.getText());
            operator = '+';
            tfield.setText("");
        }

        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(tfield.getText());
            operator = '-';
            tfield.setText("");
        }

        if (e.getSource() == multButton) {
            num1 = Double.parseDouble(tfield.getText());
            operator = '*';
            tfield.setText("");
        }

        if (e.getSource() == diffButton) {
            num1 = Double.parseDouble(tfield.getText());
            operator = '/';
            tfield.setText("");
        }

        if (e.getSource() == eqButton && isTextFieldNoAsResult(tfield.getText(), result)) {
            if (tfield.getText().isEmpty()) {
                num2 = 0;
            } else {
                num2 = Double.parseDouble(tfield.getText());
            }
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    result = num1 / num2;
                    break;
            }
            if (result == Math.floor(result)) {
                tfield.setText(String.valueOf((int) result));
            } else {
                tfield.setText(String.valueOf(result));
            }
            num1 = result;
        }

        if (e.getSource() == clrButton) {
            tfield.setText("");
            num1 = 0;
        }

        if (e.getSource() == delButton) {
            String string = tfield.getText();
            tfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                tfield.setText(tfield.getText() + string.charAt(i));
            }
        }

        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(tfield.getText());
            temp *= -1;
            tfield.setText(String.valueOf(temp));
        }
    }

    public static boolean isTextFieldNoAsResult(String textField, double result) {
        if (textField.contains(".")) {
            return !textField.equals(String.valueOf(result));
        } else {
            return !textField.equals(String.valueOf((int)result));
        }

    }
}
