public class Main {
    public static void main(String[] args) {
        Form form =  new Form();
        form.setVisible(true);
        InputListener Compilator = new IdPassCompilator();
        form.setInputListener(Compilator);
    }
    public static class IdPassCompilator implements InputListener{


        @Override
        public void getInput(String id, String pass) {
//            System.out.println("DADA");
            System.out.println("Это проверка: pass, id = " + pass + ", " + id);


        }



    }
}
