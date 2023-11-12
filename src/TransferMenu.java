import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferMenu extends JFrame {
    private String userLineToChek;
    private double senderBalanceToChange;
    private double receiverBalanceToChange;
    private String transferId;
    private JTextField sumInput;
    private JTextField idInput;
    private double changeSum;

    public TransferMenu(String userLine, String userBalance) {
        super("Перевод");
        super.setBounds(600, 200, 400, 150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container transferMenuContainer = super.getContentPane();
        transferMenuContainer.setLayout(new GridLayout(4, 2));
        userLineToChek = userLine;
        ;
        JLabel balanceInfo = new JLabel("Ваш баланс: " + userBalance + ".");
        JLabel emptyElement = new JLabel(" ");
        transferMenuContainer.add(balanceInfo);
        transferMenuContainer.add(emptyElement);
        JLabel sumRequest = new JLabel("Укажите сумму перевода: ");
        transferMenuContainer.add(sumRequest);
        sumInput = new JTextField();
        transferMenuContainer.add(sumInput);
        JLabel idRequest = new JLabel("Укажите ID получателя: ");
        idInput = new JTextField();
        transferMenuContainer.add(idRequest);
        transferMenuContainer.add(idInput);
        JButton transferButton = new JButton("Отправить");
        transferMenuContainer.add(transferButton);
        transferButton.addActionListener(new TransferButtonEvent());
        senderBalanceToChange = Double.parseDouble(userBalance);
    }

    private class TransferButtonEvent implements ActionListener, MoneyOperator, IdCheker {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sumToTransfer = sumInput.getText();
            String idReceiver = idInput.getText();
            if (chekSum(sumToTransfer)) {
                if (validateId(idReceiver)) {
                    String receiverLine = BaseInput.userFinder(idReceiver);
                    receiverBalanceToChange = Double.parseDouble(BaseInput.getUserBalance());
                    double newSenderBalance = senderBalanceToChange - Double.parseDouble(sumToTransfer);
                    double newReceiverBalance = receiverBalanceToChange + Double.parseDouble(sumToTransfer);
                    putMoney(newReceiverBalance, receiverLine);
                    putMoney(newSenderBalance,userLineToChek);
                    JOptionPane.showMessageDialog(null, "Успешный перевод!\nПереведено: " +sumToTransfer + ".\nВаш баланс: " + String.valueOf(newSenderBalance), "Операция выполнена", JOptionPane.PLAIN_MESSAGE);
                    dispose();
                }
            }
        }
        @Override
        public boolean chekSum(String sum) {
            boolean succes = false;
            try {
                changeSum = Double.parseDouble(sum);
                if (changeSum > 0) {
                    if (changeSum <= senderBalanceToChange) {
                        succes = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Недостаточной средств для перевода.", "Операция отменена", JOptionPane.PLAIN_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Сумма для перевода должна быть положительной.", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Сумма для перевода должна быть числом.", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                throw new RuntimeException(ex);
            }
            return succes;
        }
    }
}