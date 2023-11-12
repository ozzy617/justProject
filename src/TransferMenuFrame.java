import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class TransferMenuFrame extends JFrame {

    private final String userLineToCheck;
    private final double currentSenderBalance;
    private final JTextField sumInput;
    private final JTextField idInput;

    public TransferMenuFrame(String userLine, String userBalance) {
        super("Перевод");
        super.setBounds(600, 200, 400, 150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container transferMenuContainer = super.getContentPane();
        transferMenuContainer.setLayout(new GridLayout(4, 2));

        userLineToCheck = userLine;

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
        currentSenderBalance = Double.parseDouble(userBalance);
    }

    private class TransferButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String sumToTransfer = sumInput.getText();
            String idReceiver = idInput.getText();
            if (checkTransferSum(sumToTransfer)) {
                double sumToTransferNum = Double.parseDouble(sumToTransfer);
                if (IdChecker.validateId(idReceiver)) {
                    String receiverDbRecord = IdChecker.findDbRecordForId(idReceiver);
                    double currentReceiverBalance = getUserBalance(receiverDbRecord);

                    DecimalFormat decimalFormat = new DecimalFormat("#.##");
                    double newSenderBalance = currentSenderBalance - sumToTransferNum;
                    String newSenderBalanceStr = decimalFormat.format(newSenderBalance);
                    double newReceiverBalance = currentReceiverBalance + sumToTransferNum;
                    MoneyOperator.updateDbForUser(decimalFormat.format(newReceiverBalance), receiverDbRecord);
                    MoneyOperator.updateDbForUser(newSenderBalanceStr, userLineToCheck);

                    JOptionPane.showMessageDialog(null, "Успешный перевод!\nПереведено: " +
                            sumToTransfer + ".\nВаш баланс: " + newSenderBalanceStr, "Операция выполнена",
                            JOptionPane.PLAIN_MESSAGE);
                    dispose();
                }
            }
        }

        private boolean checkTransferSum(String sum) {
            try {
                double changeSum = Double.parseDouble(sum);
                if (changeSum > 0) {
                    if (changeSum <= currentSenderBalance) {
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "Недостаточной средств для перевода.",
                                "Операция отменена", JOptionPane.PLAIN_MESSAGE);
                        return false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Сумма для перевода должна быть положительной.",
                            "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    return false;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Сумма для перевода должна быть числом.",
                        "Ошибка", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        }

        private double getUserBalance(String dbRecord) {
            String[] dbRecordList = dbRecord.split(" ");
            String balance = dbRecordList[2];
            return Double.parseDouble(balance);
        }
    }
}