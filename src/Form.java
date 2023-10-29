import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame {

    private String id;
    private String pass;
    private JTextField idField;
    private JTextField passField;
    private InputListener inputs; //почему на основе интерфейса

    public void setInputListener(InputListener inputListener) {
        inputs = inputListener;
    }

    public Form() {
        super("Вход");
        super.setBounds(600,200,300,150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = super.getContentPane();
        container.setLayout(new GridLayout(3,2,2,15));
        JLabel id = new JLabel("Введите ID");
        JLabel pass = new JLabel("Введите пароль");
        idField = new JTextField();
        passField = new JPasswordField();
        container.add(id);
        container.add(idField);
        container.add(pass);
        container.add(passField);
        JButton sendButton = new JButton("Отправить");
        container.add(sendButton);
        sendButton.addActionListener(new ButtonEvent());
    }

    class ButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            id = idField.getText();
            pass = passField.getText();
            if (IDHelper.validateId(id) & BaseInput.readUserInputFromDatabase(id, pass)) {
                inputs.getInput(id, pass);
                dispose();
            }
        }
    }
}
