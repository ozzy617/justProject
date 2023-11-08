import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PutMenu extends JFrame {
    private JTextField putSum;
    private String userLineToChek;
    private String readId;
    private String readPass;
    private String changedBalance;
    private String readName;
    private double userBalanceToChange;
    public PutMenu(String userLine, String userBalance){
        super("Пополнение");
        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        super.setBounds(600,200,370,100);
        Container putMenuContainer = super.getContentPane();
        putMenuContainer.setLayout(new GridLayout(2,2,2,15));
        JLabel putQuestion = new JLabel("Введите сумму пополнения: ");
        putSum = new JTextField();
        putMenuContainer.add(putQuestion);
        putMenuContainer.add(putSum);
        JButton putButton = new JButton("Пополнить");
        putMenuContainer.add(putButton);
        putButton.addActionListener(new putBottonEvent());
        userLineToChek = userLine;
        userBalanceToChange = Double.parseDouble(userBalance);
    }

    private class putBottonEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                double changeSum = Double.parseDouble(putSum.getText());
                double newUserBalance = userBalanceToChange + changeSum;
                if (changeSum > 0) {
                    try {
                        File bazaCopy = new File("bazaCopy.txt");
                        BufferedWriter writer = new BufferedWriter(new FileWriter("bazaCopy.txt"));
                        BufferedReader reader = new BufferedReader(new FileReader("baza.txt"));
                        String lineReader = reader.readLine();
                        String[] changeList;
                        while (lineReader != null) {
                            if (!lineReader.equals(userLineToChek)) {
                                writer.write(lineReader + "\n");

                            } else {
                                changeList = lineReader.split(" ");
                                readId = changeList[0];
                                readPass = changeList[1];
                                readName = changeList[3];
                                changedBalance = String.valueOf(newUserBalance);
                                String backLine = readId + " " + readPass + " " + changedBalance + " " + readName;
                                writer.write(backLine + "\n");

                            }
                            lineReader = reader.readLine();

                        }
                        writer.close();
                        reader.close();
                        File baza = new File("baza.txt");
                        baza.delete();
                        bazaCopy.renameTo(new File("baza.txt"));
                        dispose();
                        JOptionPane.showMessageDialog(null, "Успешное пополнение! Теперь ваш баланс: " + changedBalance + " .", "Операция выполнена", JOptionPane.PLAIN_MESSAGE);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Вы можете пополнить баланс только на положительную сумму.", "Ошибка", JOptionPane.PLAIN_MESSAGE);
                }
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(null, "Укажите сумму пополнения.", "Ошибка", JOptionPane.PLAIN_MESSAGE);

            }
        }
    }

}
