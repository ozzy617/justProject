import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MoneyOperator {

     public static void updateDbForUser(String newUserBalance, String userLineToCheck) {
         System.out.println("Changed balance: " + newUserBalance);
         try {
             File dbCopy = new File("bazaCopy.txt");
             BufferedWriter dbCopyWriter = new BufferedWriter(new FileWriter("bazaCopy.txt"));
             BufferedReader dbReader = new BufferedReader(new FileReader("baza.txt"));
             String line = dbReader.readLine();
             while (line != null) {
                 if (line.equals(userLineToCheck)) {
                     String[] newLineList = line.split(" ");
                     newLineList[2] = newUserBalance;
                     String newLine = String.join(" ", newLineList);
                     dbCopyWriter.write(newLine + "\n");
                 } else {
                     dbCopyWriter.write(line + "\n");
                 }
                 line = dbReader.readLine();
             }
             dbCopyWriter.close();
             dbReader.close();
             File dbFile = new File("baza.txt");
             dbFile.delete();
             dbCopy.renameTo(new File("baza.txt"));
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
     }
}
