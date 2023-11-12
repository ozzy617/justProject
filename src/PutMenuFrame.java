import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class PutMenuFrame extends JFrame {

    private final JTextField sumInput;
    private final String userLineToCheck;
    private final double currentUserBalance;

    public PutMenuFrame(String userLine, String userBalance) {
        super("Пополнение");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(600,200,370,100);

        Container putMenuContainer = super.getContentPane();
        putMenuContainer.setLayout(new GridLayout(2,2,2,15));

        userLineToCheck = userLine;
        currentUserBalance = Double.parseDouble(userBalance);

        JLabel putQuestion = new JLabel("Введите сумму пополнения: ");
        sumInput = new JTextField();
        putMenuContainer.add(putQuestion);
        putMenuContainer.add(sumInput);

        JButton putButton = new JButton("Пополнить");
        putMenuContainer.add(putButton);
        putButton.addActionListener(new PutButtonEvent());
    }

    private class PutButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sum = sumInput.getText();
            if (checkPutSum(sum)) {
                double newUserBalance = currentUserBalance + Double.parseDouble(sum);
                DecimalFormat decimalFormat = new DecimalFormat("#.##");
                String newUserBalanceStr = decimalFormat.format(newUserBalance);
                MoneyOperator.updateDbForUser(newUserBalanceStr, userLineToCheck);
                JOptionPane.showMessageDialog(null, "Успешное пополнение! Теперь ваш баланс: " +
                        newUserBalanceStr + " .", "Операция выполнена", JOptionPane.PLAIN_MESSAGE);
                dispose();
            }
        }
    }

    private static boolean checkPutSum(String sum) {
        try {
            double changeSum = Double.parseDouble(sum);
            if (changeSum > 0) {
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Вы можете пополнить баланс только на положительную сумму.",
                        "Ошибка", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Сумма для пополения должна быть числом.",
                    "Ошибка", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
    }
}
