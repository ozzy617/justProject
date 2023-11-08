import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BaseInput {
    private static String baseUserBalance;
    private static String baseUserName;
    public static String getUserName(){
        return baseUserName;
    }
    public static String getUserBalance(){
        return baseUserBalance;
    }
    public static boolean readUserFromBase(String inputId, String inputPass) {
        System.out.println("Reading from the database for input ID: " + inputId + " and password: " + inputPass);
        boolean succes = false;
        try{
            BufferedReader baza = new BufferedReader(new FileReader("baza.txt"));
            String line = baza.readLine();
            String[] list;
            while (line != null) {
                list = line.split(" ");
                String readId = list[0];
                String readPass = list[1];
                System.out.println("Reading the line where ID: " + readId + " and password: " + readPass);


                if (readId.equals(inputId)) {
                    if (readPass.equals(inputPass)) {
                          baseUserBalance = list[2];
                          baseUserName = list[3];
                        //JOptionPane.showMessageDialog(null, "Здравствуйте, " + userName + "\nВаш ID: " + inputId + "\nВаш баланс: " + userBalance,"", JOptionPane.PLAIN_MESSAGE);
                        succes = true;
                        break;
                    } else {
                        JOptionPane.showMessageDialog(null, "Неверный пароль.", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                    }
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
