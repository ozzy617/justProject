import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BaseInput{
    private static String readName;
    private static String readBalance;
//    private static String baseUserId;
//    private static String baseUserPass;


    //    public static String getUserId(){
//        return baseUserId;
//    }
//    public static String getUserPass(){
//        return baseUserPass;
//    }
    public static String getUserName(){
        return readName;
    }
    public static String getUserBalance(){
        return readBalance;
    }
    public static boolean readUserFromBase(String inputId, String inputPass) {
        System.out.println("Reading from the database for input ID: " + inputId + " and password: " + inputPass);
        boolean succes = false;
        String baseInfoLine = userFinder(inputId);
        String[] infoList = baseInfoLine.split(" ");

            if (infoList[1].equals(inputPass)){
                succes = true;
            }
            else {
                JOptionPane.showMessageDialog(null, "Неверный пароль.", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }

        return succes;
    }
    public static String userFinder(String id){
        String userLine = "1 2 3 4";
        try{
            BufferedReader baza = new BufferedReader(new FileReader("baza.txt"));
            String line = baza.readLine();
            String[] list;
            while (line != null){
                list = line.split(" ");
                String readId = list[0];
                String readPass = list[1];
                if (readId.equals(id)){
                    readBalance = list[2];
                    readName = list[3];
                    userLine = readId + " " + readPass + " " + readBalance + " " + readName;
                    break;
                }
                line = baza.readLine();
            }
            baza.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return userLine;
    }
}