
public class NameHelper {
    public static void requestName() {
        System.out.println("Введите ФИО(через пробел): ");
    }
    public static String  getName(String name){
        return setName(name);
    }
    public static String setName(String name){
        return chekName(name);
    }
    private static String chekName(String name){
        String str = name.replace(" ","");
        char[] nameArray= str.toCharArray();
        boolean nameLetter = true;
        char[] spaceArray = name.toCharArray();
        int spaceCount = spaceArray.length - str.length();
        String[] strArray = name.split(" ");//2 ПРОБЕЛА!!
//ВАЙЛ
        for (int i = 0; i < strArray.length; i++){
           //System.out.println(strArray[i]);
            if (strArray[i] == " "){
                System.out.println(strArray[i]);
               //count = count + 1;
            }
       }
        System.out.println(spaceCount);
        if (spaceCount != 2 && strArray.length == 3){
            System.out.println("Ввод должен содержать только 2 пробела между словами");
            return "no";
        }
        else {
            if (strArray.length != 3){
                System.out.println("ФИО должно состоять из 3 слов");
                return "no";
            }
            else {
                for (int i = 0; i < nameArray.length; i++) {
                    if (!Character.isLetter(nameArray[i])) {
                        //if (!Character.isLetter(str.charAt(i))){
                        nameLetter = false;
                    }

                }
                if (nameLetter == true){
                    System.out.println("ФИО установлено - " + name);
                    return name;
                } else {
                    System.out.println("ФИО не может содержать цифр и сторонних символов");
                    return "no";
                }
            }
        }
    }
}
