import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BaseInput {

    public static String BaseReader(String id, String pass) {
        System.out.println(id + pass);
        boolean flag = false;
        try{
            BufferedReader baza = new BufferedReader(new FileReader("/Users/artemlaptev/Desktop/baza.txt"));
            String line = baza.readLine();
            String[] list;
            boolean chek = false;
            while (line != null){
                list = line.split(" ");
                System.out.println(list[0] + list[1]);

                if ((list[0].equals(id)) & (list[1].equals(pass))){
                    JOptionPane.showMessageDialog(null,"Здравствуйте, " + list[3]+"!\nВаш ID: " + id +"\nВаш баланс: " + list[2],"",JOptionPane.PLAIN_MESSAGE);
                    flag = true;
                    chek = true;
                }
                line = baza.readLine();
            }
            if (chek != true){
                JOptionPane.showMessageDialog(null,"Неверный пароль", " ",JOptionPane.PLAIN_MESSAGE);
            }
            baza.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {         //ЧТО ЗА ИСКЛЮЧЕНИЯ???
            throw new RuntimeException(e);
        }
    if (flag == true){
        return "yes";
    }
    else{
        return "no";
    }
    }
}
