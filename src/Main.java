public class Main {
    public static void main(String[] args) {
        LoginFrame loginFrame =  new LoginFrame();
        loginFrame.setVisible(true);
        IdPassCompilator compilator = new IdPassCompilator();// НА ОСНОВЕ ИНТЕРФЕЙСА ИЛИ КЛАССА
        loginFrame.setInputListener(compilator);
    }
    public static class IdPassCompilator implements InputListener{
        @Override
        public void getInput(String id, String pass, String userName, String userBalance) {
            System.out.println("Это проверка: pass, id = " + pass + ", " + id);
            MenuFrame menuFrame = new MenuFrame(id, pass, userName, userBalance);
            menuFrame.setVisible(true);
        }
    }
}
