import javax.swing.*;

public class BaseInput {

    private String readName;
    private String readBalance;

    public String getUserName() {
        return readName;
    }

    public String getUserBalance() {
        return readBalance;
    }

    public boolean readUserFromBase(String inputId, String inputPass) {
        System.out.println("Reading from the database for input ID: " + inputId + " and password: " + inputPass);
        String dbRecord = IdChecker.findDbRecordForId(inputId);
        if (dbRecord == null) {
            JOptionPane.showMessageDialog(null, "Не удалось обработать запрос.", "Ошибка",
                    JOptionPane.PLAIN_MESSAGE);
            return false;
        }
        String[] dbRecordList = dbRecord.split(" ");
        String dbRecordPass = dbRecordList[1];
        if (dbRecordPass.equals(inputPass)) {
            readBalance = dbRecordList[2];
            readName = dbRecordList[3];
            return true;
        } else {
            if (inputPass.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Пароль не был введен.", "Ошибка",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Неверный пароль.", "Ошибка",
                        JOptionPane.PLAIN_MESSAGE);
            }
            return false;
        }
    }
}