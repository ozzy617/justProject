import javax.swing.*;
import java.io.*;

public interface MoneyOperator {
     default void putMoney(double newUserBalance,String userLineToChek){
         String changedBalance = String.valueOf(newUserBalance);
         System.out.println(changedBalance + " - LOL");
         try {
             File bazaCopy = new File("bazaCopy.txt");
             BufferedWriter writer = new BufferedWriter(new FileWriter("bazaCopy.txt"));
             BufferedReader reader = new BufferedReader(new FileReader("baza.txt"));
             String lineReader = reader.readLine();
             String[] changeList;
             while (lineReader != null) {
                 if (lineReader.equals(userLineToChek)) {
                     changeList = lineReader.split(" ");
                     String readId = changeList[0];
                     String readPass = changeList[1];
                     String readName = changeList[3];
                     String backLine = readId + " " + readPass + " " + changedBalance + " " + readName;
                     writer.write(backLine + "\n");
                 } else {
                     writer.write(lineReader + "\n");
                 }
                 lineReader = reader.readLine();
             }
             writer.close();
             reader.close();
             File baza = new File("baza.txt");
             baza.delete();
             bazaCopy.renameTo(new File("baza.txt"));
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
     default boolean chekSum(String sum){
        boolean succes = false;
        try {
            double changeSum = Double.parseDouble(sum);
            if (changeSum > 0) {
                succes = true;
            } else {
                JOptionPane.showMessageDialog(null, "Вы можете пополнить баланс только на положительную сумму.", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Сумма для пополения должна быть числом.", "Ошибка", JOptionPane.PLAIN_MESSAGE);
            throw new RuntimeException(ex);
        }
        return succes;
    }


}
