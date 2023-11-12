import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Form extends JFrame{
    private String id;
    private String pass;
    private String userName;
    private String userBalance;
    private JTextField idField;
    private JTextField passField;
    private InputListener inputs;
    public void setInputListener(InputListener inputListener) {
        inputs = inputListener;
    }



    public Form(){
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

    private class ButtonEvent implements ActionListener, IdCheker {
        @Override
        public void actionPerformed(ActionEvent e) {
              id = idField.getText();
              pass = passField.getText();
            if (validateId(id)) {
                if (BaseInput.readUserFromBase(id,pass)){
                    JOptionPane.showMessageDialog(null, "Обработка...", "", JOptionPane.PLAIN_MESSAGE);
                    userName = BaseInput.getUserName();
                    userBalance = BaseInput.getUserBalance();
                    inputs.getInput(id, pass, userName,userBalance);
                    dispose();
                }
            }
        }
    }
}
