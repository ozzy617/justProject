import javax.swing.*;
import java.io.*;

public class IdChecker {

    private static final int ID_LENGTH = 8;

    public static boolean validateId(String id) {
        try {
            Integer.parseInt(id);
            if (id.length() != ID_LENGTH) {
                JOptionPane.showMessageDialog(null, "ID должен состоять из 8 символов.",
                        "Ошибка", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
            if (findDbRecordForId(id) == null) {
                JOptionPane.showMessageDialog(null, "ID не зарегистрирован.", "",
                        JOptionPane.PLAIN_MESSAGE);
                return false;
            }
        } catch (NumberFormatException e) {
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(null, "ID не был введен.", "Ошибка",
                        JOptionPane.PLAIN_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "ID не может содержать буквы и сторонних символов.",
                        "Ошибка", JOptionPane.PLAIN_MESSAGE);

            }
            return false;
        }
        return true;
    }

    public static String findDbRecordForId(String id) {
        String dbRecord = null;
        try {
            BufferedReader dbReader = new BufferedReader(new FileReader("baza.txt"));
            String line = dbReader.readLine();
            while (line != null) {
                String[] list = line.split(" ");
                String readId = list[0];
                if (readId.equals(id)) {
                    dbRecord = line;
                    break;
                } else {
                    line = dbReader.readLine();
                }
            }
            dbReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return dbRecord;
    }
}
