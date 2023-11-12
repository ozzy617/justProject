import javax.swing.*;
import java.io.*;

public interface IdCheker {
    int ID_LENGTH = 8;
    default boolean validateId(String id) {
        String some = id;

        boolean chek = true;
        try {
            Integer.parseInt(id);
            if (id.length() != ID_LENGTH) {
                JOptionPane.showMessageDialog(null, "ID должен состоять из 8 символов.", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                chek = false;
            } else if (!idExistance(id)) {
                JOptionPane.showMessageDialog(null, "ID не зарегистрирован.", "", JOptionPane.PLAIN_MESSAGE);
                chek = false;
            }
        } catch (NumberFormatException e) {
            chek = false;
            if (some.equals("")) {
                JOptionPane.showMessageDialog(null, "ID не был введен.", "Ошибка", JOptionPane.PLAIN_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(null, "ID не может содержать буквы и сторонних символов.", "Ошибка", JOptionPane.PLAIN_MESSAGE);

            }
        }
        return chek;
    }
    default boolean idExistance(String id) {
        boolean succes = false;
        try {
            BufferedReader baza = new BufferedReader(new FileReader("baza.txt"));
            String line = baza.readLine();
            String[] list;
            while (line != null) {
                list = line.split(" ");
                String readId = list[0];
                if (readId.equals(id)) {
                    succes = true;
                    break;
                }
                line = baza.readLine();
            }
            baza.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return succes;
    }
}
