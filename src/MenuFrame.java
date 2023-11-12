import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuFrame extends JFrame {

    private final String userLine;
    private final String userBalance;

    public MenuFrame(String userId, String userPass, String userName, String userBalance) {
        super("Menu");
        super.setBounds(600,200,300,150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container containerMenu = super.getContentPane();
        containerMenu.setLayout(new GridLayout(4,2,2,10));

        this.userLine = userId + " " + userPass + " " + userBalance + " " + userName;
        this.userBalance = userBalance;

        JLabel userInfo = new JLabel("Здравствуйте, " + userName+ "! Ваш баланс: " + userBalance);
        JLabel userQuestion = new JLabel("Что бы в хотели сделать?");
        containerMenu.add(userInfo);
        containerMenu.add(userQuestion);

        JButton transferMoneyButton = new JButton("Перевести");
        JButton putMoneyButton = new JButton("Пополнить");
        containerMenu.add(transferMoneyButton);
        containerMenu.add(putMoneyButton);
        transferMoneyButton.addActionListener(new TransferButtonEvent());
        putMoneyButton.addActionListener(new PutMoneyButtonEvent());
    }

    private class TransferButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            TransferMenuFrame transferMenuFrame = new TransferMenuFrame(userLine, userBalance);
            transferMenuFrame.setVisible(true);
        }
    }

    private class PutMoneyButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
            PutMenuFrame putMenuFrame = new PutMenuFrame(userLine, userBalance);
            putMenuFrame.setVisible(true);
        }
    }
}
