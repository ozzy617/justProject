import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Form form =  new Form();
        form.setVisible(true);
        IdPassCompilator compilator = new IdPassCompilator();// НА ОСНОВЕ ИНТЕРФЕЙСА ИЛИ КЛАССА
        form.setInputListener(compilator);
    }
    public static class IdPassCompilator implements InputListener{
        @Override
        public void getInput(String id, String pass, String userName, String userBalance) {
            System.out.println("Это проверка: pass, id = " + pass + ", " + id);
            Menu menu = new Menu(id, pass, userName, userBalance);
            menu.setVisible(true);
        }
    }
}
