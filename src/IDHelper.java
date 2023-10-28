import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class IDHelper {


    public static String getID(String id){
        return setID(id);
    }
    private static String setID(String id){
        String cd = chekID(id);
        if (cd == "yes"){
            return "yes";
        }
        else{
            return "no";
        }
    }

    private static String chekID(String id){
        try{
            char[] symbolArray = id.toCharArray();//ВОПРОС
            int chek = Integer.parseInt(id);
            if (symbolArray.length != 8){
                JOptionPane.showMessageDialog(null, "Ошибка: ID должен состоять из 8 символов","",JOptionPane.PLAIN_MESSAGE);
                return "no";
            }
            else if (passReader(id) == "nope"){
                JOptionPane.showMessageDialog(null,"Ваш ID не зарегистрирован","",JOptionPane.PLAIN_MESSAGE);
                return "no";
            }
            else{
                return "yes";
            }
        } catch(NumberFormatException e){
            JOptionPane.showMessageDialog(null,"Ошибка: ID не может содержать букв и сторонних символов","",JOptionPane.PLAIN_MESSAGE);
            return "no";
        }
    }

    private static String passReader(String id) {
        boolean chek = false;
        try {
            BufferedReader baza = new BufferedReader(new FileReader("/Users/artemlaptev/Desktop/baza.txt"));
            String line = baza.readLine();
            String[] list;
            while (line != null) {
                list = line.split(" ");
                System.out.println(list[0] + list[1]);
                boolean flag = false;
                if (list[0].equals(id)) {
                    chek = true;
                    flag = true;
                }
                if (flag == true) {
                    break;
                }
                line = baza.readLine();
            }
            baza.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {         //ЧТО ЗА ИСКЛЮЧЕНИЯ???
            throw new RuntimeException(e);
        }
      if (chek == true){
          return "yep";
      }
      else{
          return "nope";

      }
    }
}
