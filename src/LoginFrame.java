import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {

    private final JTextField idField;
    private final JTextField passField;
    private InputListener inputListener;

    public void setInputListener(InputListener inputListener) {
        this.inputListener = inputListener;
    }

    public LoginFrame() {
        super("Вход");
        super.setBounds(600,200,300,150);
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container containerForm = super.getContentPane();
        containerForm.setLayout(new GridLayout(3,2,2,15));

        JLabel id = new JLabel("Введите ID");
        JLabel pass = new JLabel("Введите пароль");
        idField = new JTextField();
        passField = new JPasswordField();
        containerForm.add(id);
        containerForm.add(idField);
        containerForm.add(pass);
        containerForm.add(passField);

        JButton sendButton = new JButton("Отправить");
        containerForm.add(sendButton);
        sendButton.addActionListener(new ButtonEvent());
    }

    private class ButtonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String id = idField.getText();
            String pass = passField.getText();
            if (IdChecker.validateId(id)) {
                BaseInput baseInput = new BaseInput();
                if (baseInput.readUserFromBase(id, pass)) {
                    JOptionPane.showMessageDialog(null, "Обработка...", "",
                            JOptionPane.PLAIN_MESSAGE);
                    String userName = baseInput.getUserName();
                    String userBalance = baseInput.getUserBalance();
                    inputListener.getInput(id, pass, userName, userBalance);
                    dispose();
                }
            }
        }
    }
}
