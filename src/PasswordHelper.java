import javax.swing.*;

public class PasswordHelper{
    public static void requestPassword() {
        System.out.println("Введите пароль(4 цифры): ");
    }
    public static String getPass(String password){
        return setPass(password);
    }
    public static String setPass(String password){
        return chekPass(password);
    }
    private static String chekPass(String password){
        try{
            char[] symbolArray = password.toCharArray();//ВОПРОС
            int chek = Integer.parseInt(password);
            if (symbolArray.length != 4){
                JOptionPane.showMessageDialog(null, "Ошибка 1: ID должен состоять из 8 символов","",JOptionPane.PLAIN_MESSAGE);
                System.out.println("Ошибка: пароль должен быть 4-значным ЧИСЛОМ");
                return "no";
            }
            else{
                String str = String.valueOf(symbolArray);
                System.out.println("Пароль принят");
                return str;
            }
        } catch(NumberFormatException e){
            System.out.println("Ошибка: пароль должен быть числом");
            return "no";
        }
    }
}
