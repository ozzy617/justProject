import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private String userLine;
    private String userBalance;

    public Menu(String id,  String pass, String userName, String userBalance){
        super("Menu");
        super.setBounds(600,200,300,150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container containerMenu = super.getContentPane();
        containerMenu.setLayout(new GridLayout(4,2,2,10));
        JLabel userInfo = new JLabel("Здравствуйте, " + userName+ "! Ваш баланс: " + userBalance);
        JLabel userQuestion = new JLabel("Что бы в хотели сделать?");containerMenu.add(userInfo);
        containerMenu.add(userQuestion);
        JButton transferMoney = new JButton("Перевести");
        JButton putMoney = new JButton("Пополнить");
        containerMenu.add(transferMoney);
        containerMenu.add(putMoney);
        transferMoney.addActionListener(new transferButtonEvent());
        putMoney.addActionListener(new putMoneyButtonEvent());
        userLine = id + " " + pass + " " + userBalance + " " + userName;
        this.userBalance = userBalance;
    }

    private class transferButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            dispose();


        }
    }
    private class putMoneyButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e){
            dispose();
            PutMenu putMenu = new PutMenu(userLine, userBalance);
            putMenu.setVisible(true);
        }
    }
}
