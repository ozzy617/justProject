import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IDHelper {

    private final static Integer REQUIRED_ID_LENGTH = 8;

    /**
     * Validates the given ID string. The method checks if the ID is a valid integer,
     * has the required length, and exists in the database.
     *
     * @param id The ID string to be validated.
     * @return {@code true} if the ID is valid and exists in the database, {@code false} otherwise.
     *
     * @throws NumberFormatException if the ID cannot be parsed as an integer.
     */
    public static boolean validateId(String id) {
        try {
            // check that ID is a valid Integer
            Integer.parseInt(id);
            // check that ID has required length
            if (id.length() != REQUIRED_ID_LENGTH) {
                JOptionPane.showMessageDialog(null, "ID должен состоять из 8 символов",
                        "Ошибка", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
            // check that ID exists in the database
            else if (!checkIdExistence(id)) {
                JOptionPane.showMessageDialog(null,"Ваш ID не зарегистрирован",
                        "Ошибка", JOptionPane.PLAIN_MESSAGE);
                return false;
            }
            else {
                return true;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "ID не может содержать букв и сторонних символов",
                    "Ошибка", JOptionPane.PLAIN_MESSAGE);
            return false;
        }
    }

    /**
     * Checks if the given ID exists in the database stored in the "baza.txt" file.
     *
     * @param id The ID string to be checked for existence in the database.
     * @return {@code true} if the ID exists in the database, {@code false} otherwise.
     *
     * @throws RuntimeException if an IOException occurs while reading the database file.
     */
    private static boolean checkIdExistence(String id) {
        boolean success = false;
        try {
            BufferedReader baza = new BufferedReader(new FileReader("baza.txt"));
            String line = baza.readLine();
            String[] list;
            while (line != null) {
                list = line.split(" ");
                if (list[0].equals(id)) {
                    success = true;
                    // ID was found - no need to continue the cycle
                    break;
                }
                line = baza.readLine();
            }
            baza.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return success;
    }
}
