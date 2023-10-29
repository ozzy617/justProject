import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BaseInput {

    /**
     * Reads user information from the database stored in the "baza.txt" file and validates
     * the input ID and password. If a matching ID is found with the correct password,
     * displays user information in a dialog box.
     *
     * @param inputId The input ID string to be validated.
     * @param inputPass The input password string to be validated.
     * @return {@code true} if the input ID and password are valid and match a record in the database,
     *         {@code false} otherwise.
     *
     * @throws RuntimeException if an IOException occurs while reading the database file.
     */
    public static boolean readUserInputFromDatabase(String inputId, String inputPass) {
        System.out.println("Reading from the database for input ID: " + inputId + " and password: " + inputPass);
        boolean success = false;
        try {
            BufferedReader baza = new BufferedReader(new FileReader("baza.txt"));
            String line = baza.readLine();
            String[] list;
            while (line != null) {
                list = line.split(" ");
                String readId = list[0];
                String readPassword = list[1];
                System.out.println("Reading the line where ID: " + readId + " and password: " + readPassword);

                if (readId.equals(inputId)) {
                    if (!readPassword.equals(inputPass)) {
                        JOptionPane.showMessageDialog(null, "Неверный пароль", "Ошибка",
                                JOptionPane.PLAIN_MESSAGE);
                    } else {
                        String userName = list[3];
                        String userBalance = list[2];
                        JOptionPane.showMessageDialog(null,
                                "Здравствуйте, " + userName + "!\nВаш ID: " + inputId + "\nВаш баланс: " + userBalance,
                                "", JOptionPane.PLAIN_MESSAGE);
                        success = true;
                    }
                    // database record was found - no need to continue the cycle
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