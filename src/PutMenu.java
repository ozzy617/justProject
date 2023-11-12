import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PutMenu extends JFrame {
    private JTextField sumInput;
    private String userLineToChek;
    private double userBalanceToChange;
    public PutMenu(String userLine, String userBalance){
        super("Пополнение");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(600,200,370,100);
        Container putMenuContainer = super.getContentPane();
        putMenuContainer.setLayout(new GridLayout(2,2,2,15));
        JLabel putQuestion = new JLabel("Введите сумму пополнения: ");
        sumInput = new JTextField();
        putMenuContainer.add(putQuestion);
        putMenuContainer.add(sumInput);
        JButton putButton = new JButton("Пополнить");
        putMenuContainer.add(putButton);
        putButton.addActionListener(new PutBottonEvent());
        userLineToChek = userLine;
        userBalanceToChange = Double.parseDouble(userBalance);
    }

    private class PutBottonEvent implements ActionListener, MoneyOperator {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sum = sumInput.getText();
            if (chekSum(sum)){
                double newUserBalance = userBalanceToChange + Double.parseDouble(sum);
                putMoney(newUserBalance,userLineToChek);
                JOptionPane.showMessageDialog(null, "Успешное пополнение! Теперь ваш баланс: " + String.valueOf(newUserBalance) + " .", "Операция выполнена", JOptionPane.PLAIN_MESSAGE);
                dispose();
            }
        }
    }
}
