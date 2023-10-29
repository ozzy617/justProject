public class Main {

    public static void main(String[] args) {
        Form form =  new Form();
        form.setVisible(true);
        InputListener compilator = new IdPassCompilator();
        form.setInputListener(compilator);
    }

    public static class IdPassCompilator implements InputListener {
        @Override
        public void getInput(String id, String pass) {
            System.out.println("Проверка пройдена: pass = " + pass + ", ID = " + id);
        }
    }
}
