package Ruslan_7kd;
import java.io.*;
import java.util.*;
/*
This project hold a detail concept and working of a real contact book.
Important feature of contact book like (Add, delete, update, Find and read) are added in this project.
The most important part is that, this book stores actual data on hard drive that can be used for future.
Using File handling techniques this book is now a powerful and considered to be an actual storage of contact details
Furthermore, once a user is registered he has the authority to work on his Contact book.
----------------------------------------------------------------------------------------
This project also hold an interesting phase of gamification, that has two games (Hangman , City Capital)
These games are constructed on basic concepts of 2D Arrays Switch statements,  Sequences and control structures.
 */
public class Functional_Contact_Management_System {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String[] cell_Functions = {"Contact - Book","Gamification - Arena"};
        char choice;
        char againYES_NO = 'y';
        do{
            System.out.println("\n\n********* Welcome To Functional Contact & Cell Management System *********\n");
            for (int i = 0; i < cell_Functions.length; i++)
                System.out.println((i+1)+") "+cell_Functions[i]);
            System.out.print("Enter your choice (1-2): ");
            choice = input.next().charAt(0);
            switch (choice){
                case '1': ContactBook.contact_Main();break;
                case '2': Gamification.gaming();break;
                default: System.out.println("Wrong Selection\nSelect appropriate option");continue;
            }
            System.out.println("\nSwitch off [x] the phone or Continue [y]: ");
            againYES_NO = input.next().toLowerCase().charAt(0);// yes
        }while (againYES_NO == 'y');
        System.out.println("Logging out.....");
    }
}
// This class is the called contact book because it performs the feature that are necessary for contact book.
class ContactBook {
    public static void contact_Main() throws IOException {
        Scanner input = new Scanner(System.in);
        String[] credentials = {"Login", "Sign-Up"};
        boolean loop = false;
        char userChoice;
        do {
            System.out.println("\n*********** [ Contact - Book ] ***********");
            for (int i = 0; i < credentials.length; i++)
                System.out.printf("\n[%d] %s ", (i + 1), credentials[i]);
            System.out.print("\nSelect appropriate option (0 to exit): ");
            userChoice = input.next().charAt(0);

            switch (userChoice) {
                case '1' : loop = loginPage();break;
                case '2' : loop = signUpPage();break;
                case '0' : return;
                default: loop = true;
            }
        } while (loop);
    }
    // After a registered user passed out from the login panel, he will access this method for
    // further access to feature of contact book.
    //The features includes (add, delete, read, search and update)
    public static void successfulLogin(String filename)throws IOException{
        Scanner input = new Scanner(System.in);
        String[] infoDetails = {"Contact-Name: ","Rol / Contact # ","Address: "};
        String[] list = {"Add ","Read ","Find ","Delete Contact / Update Data"};
        String choice;
        String data = "*************[Contact-Book]*************";
        char continueOrExit = 'y';
        do {
            for (int i = 0; i < list.length; i++)
                System.out.printf("[%d] %s \n",(i+1),list[i]);
            System.out.print("Enter Choice: ");
            choice = input.next();
            try{
                if (!(Integer.parseInt(choice) > 0  & Integer.parseInt(choice)<=4)){
                    System.out.println("Please select Appropriate Option");
                    continue;
                }
            }
            catch (Exception e) {
                System.out.println("Please select integer Option");
                continue;
            }
            switch (choice){
                case "1": {
                    data +=  add(infoDetails);
                    saveData(data, filename);
                }break;
                case "2": readData(filename);break;
                case "3": findEntity(filename);break;
                case "4": updateContact_contact( filename,infoDetails);break;
            }
            System.out.println("\nPress [x] to exit\nPress [y] to continue: ");
            continueOrExit = input.next().charAt(0);
        }while (continueOrExit == 'y');
    }
    // Method for adding data to contact book
    //File writer object is used for data appending and inserting
    public static String add(String[] info){
        String savedData = "\n";
        char choice;
        System.out.println("\nEnter contact Details...");
        do {
            Scanner input = new Scanner(System.in);
            for (int i = 0; i < info.length; i++) {
                input.useDelimiter("\\r");
                System.out.print("\n" + info[i]);
                String data = input.nextLine();
                input.reset();
                savedData += info[i] + data +"\n";
            }
            savedData += "\n---------------\n";
            System.out.println("\nPress (y) to save another contact or (n) to exit");
            choice = input.next().toLowerCase().charAt(0);
        }while(choice == 'y');
        return savedData;
    }
    // Method for Storing data to contact book
    //File writer object is used for data appending and inserting
    public static void saveData(String data , String filename) throws IOException{
        File contact_book_text_file = new File(filename);
        if(!(contact_book_text_file.exists())) {
            try {
                contact_book_text_file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileWriter fileWriter = new FileWriter(contact_book_text_file,true);
        fileWriter.write(data);
        fileWriter.close();
    }
    // Method for reading data to contact book
    //Scanner object is used for data reading
    //The whole contact book is read through hasNext() iterator
    // And Sequences are used to counter the contact being found
    public static void readData(String filename)throws IOException{
        File myFile = new File(filename);
        Scanner scan = new Scanner(myFile);
        try {
            scan.nextLine();
        }
        catch (Exception e){
            System.out.println("Contact-Book exits but it is Empty");
            return;
        }
        Scanner scanner = new Scanner(myFile);
        System.out.println();
        while (scanner.hasNextLine())
            System.out.println(scanner.nextLine());
        scan.close();
    }
    // Method for finding data to contact book
    //Scanner object is used for data reading from the contact book
    //The whole contact book is read through hasNext() iterator
    // And Sequences are used to counter the contact being found
    public static void findEntity(String filename)throws IOException{
        File myFile = new File(filename);
        Scanner scan = new Scanner(myFile);
        try {
            scan.nextLine();
        }
        catch (Exception e){
            System.out.println("Contact-Book exits but it is Empty");//0510111446699
            return;
        }
        System.out.print("Enter name to find: ");
        Scanner input = new Scanner(System.in);
        String name = input.nextLine().toLowerCase(Locale.ROOT);
        while (scan.hasNextLine()){
            String line = scan.nextLine();
            if (line.startsWith("Contact-Name:") && line.toLowerCase().endsWith(name.toLowerCase())){
                System.out.println("Entity found...\n"+line);
                try{
                    System.out.println(scan.nextLine());
                    System.out.println(scan.nextLine());
                }catch (Exception e){
                    System.out.println("End Of Contact Book Reached");
                }
                scan.close();
                return;
            }
        }
        System.out.println("Contact not found.");
    }
    // This is the login page whenever a user wants to access his/her contact book,
    //he/she must go through this page and insert his/her credentials
    // If the credentials matched from the registration file then he will have access to his Contact book.
    public static boolean loginPage()throws IOException{
        Scanner input = new Scanner(System.in);
        System.out.println("\n\t\t**** [Welcome to Log-in Page] ****\n");
        System.out.print("Enter your username: ");
        String username = input.nextLine();
        String filename = "./"+username+".txt";
        File userFile = new File(filename);
        File registerationVerification = new File("./Registered_Area.txt");
        if (!(registerationVerification.exists())){
            System.out.println("Not registered.\nTry other username or sign-up");
            return true;//break
        }
        else if  (!(userFile.exists())){
            System.out.println("Login Failed\nNo Such Id found");
            return true;
        }
        else {
            Scanner userScan = new Scanner(registerationVerification);
            System.out.print("Enter password: ");
            String passcode = input.nextLine();
            while (userScan.hasNextLine())
                if (userScan.nextLine().toLowerCase(Locale.ROOT).endsWith(username.toLowerCase(Locale.ROOT))){
                    String s = userScan.nextLine();
                    if (passcode.equals(s.substring(s.indexOf(" ")+1))){
                        System.out.println("Password matched.... logging in...\nNow you can perform the following functions");
                        successfulLogin(filename);
                        return false;//return
                    }
                }
        }
        System.out.println("Wrong Password");
        return true;
    }
    // A new user can access any contact book, unless he signs up
    //Sign up page fetched the new user's credentials and these credentials are secured in a file called Registration text file
    // When the user Sign's up, a contact book will be generated according to his name
    // Now he/she can access his contact book
    public static boolean signUpPage()throws IOException{
        Scanner input = new Scanner(System.in);
        System.out.println("\n\t\t**** [Welcome to Sign-Up Page] ****\n");
        boolean OK  = true;
        do{
            System.out.print("Enter User-name: ");
            String username = input.nextLine();
            File f = new File("./"+username+".txt");
            if (f.exists())
                System.out.println("User already Exits\nTry new username");
            else {
                System.out.print("Create Password: ");
                String password = input.nextLine();
                f.createNewFile();
                File registered = new File("./Registered_Area.txt");
                if (!(registered.exists()))
                    registered.createNewFile();
                FileWriter fileWriter = new FileWriter("./Registered_Area.txt",true);
                fileWriter.write("\nUsername: "+username+"\n");
                fileWriter.write("Password: "+password+"\n");
                fileWriter.close();
                System.out.println("User registered successfully\nNow you can Log-in to your contact book\n");
                OK = false;
            }
        }while (OK);
        return true;
    }
    //This method hold the feature of deleting or updating.
    //According to user choice this method redirect user to the specific (deletion or updating ) method
    public static void updateContact_contact(String filename, String[] info)throws IOException {
        Scanner input = new Scanner(System.in);
        File file = new File(filename);
        Scanner scan = new Scanner(file);
        char choice;
        boolean found = false;
        System.out.println("\nEnter contact name to update...");
        String contactName = input.nextLine();
        while (scan.hasNextLine()) {
            if (scan.nextLine().toLowerCase().endsWith(contactName.toLowerCase())) {
                System.out.println("Contact found");
                found = true;
                break;
            }
        }
        if (found) {
            String[] deleteUpdate = {"Delete", "Rename or Update"};
            boolean correct = true;
            do {
                for (int i = 0; i < deleteUpdate.length; i++)
                    System.out.printf("\n[%d] %s", (i + 1), deleteUpdate[i]);
                System.out.print("\nSelect choice: ");
                choice = input.next().charAt(0);
                switch (choice) {
                    case '1': delete(filename,contactName,info);correct = false;break;
                    case '2': updateExistingData(filename,contactName,info);correct = false;break;
                    default: System.out.println("Wrong selection:");continue;
                }
            } while (correct);
        } else {
            System.out.println("Contact not found\nTry correct Contact name");
            return;
        }
    }
    //Delete method requires the contact book name and the entity name for deletion purposes
    //Whenever the entity is encountered in contact book. Then he/she can deleted using special technique here below
    public static void delete(String filename,String contactName,String[] info)throws IOException{
        File oldFile = new File(filename);
        Scanner scan = new Scanner(oldFile);
        String All_data = "";
        while (scan.hasNextLine()){
            String n = scan.nextLine();
            if (n.toLowerCase(Locale.ROOT).endsWith(contactName.toLowerCase(Locale.ROOT))){
                scan.nextLine();
                scan.nextLine();
            }
            else
                All_data += (n+"\n");
        }
        scan.close();
        boolean x = oldFile.delete();
        if(!x)
            System.out.println("Contact Deleted Successfully");
        File newFile = new File(filename);
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.write(All_data);
        fileWriter.close();
    }
    //Delete method requires the contact book name and the entity name for updating purposes
    //Whenever the entity is encountered in contact book. Then he/she can update data using special technique here below
    public static void updateExistingData(String filename,String contactName,String[] info)throws IOException{
        File oldFile = new File(filename);
        Scanner scan = new Scanner(oldFile);
        Scanner input = new Scanner(System.in);
        String All_data = "";
        while (scan.hasNextLine()){
            String n = scan.nextLine();
            if (n.toLowerCase(Locale.ROOT).endsWith(contactName.toLowerCase(Locale.ROOT))){
                scan.nextLine();
                scan.nextLine();
                System.out.println("Enter data to be updated: ");
                for (int i = 0; i < info.length; i++) {
                    input.useDelimiter("\\r");
                    System.out.print("\n" + info[i]);
                    String data = input.nextLine();
                    All_data += info[i] + data +"\n";
                    input.reset();
                }
            }
            else
                All_data += (n+"\n");
        }
        scan.close();
        boolean x = oldFile.delete();
        if(!x)
            System.out.println("Contact details Updated Successfully");
        File newFile = new File(filename);
        FileWriter fileWriter = new FileWriter(newFile);
        fileWriter.write(All_data);
        fileWriter.close();
    }
}
/*
This project also hold an interesting phase of gamification, that has two games (Hangman , City Capital)
These games are constructed on basic concepts of 2D Arrays Switch statements,  Sequences and control structures.
 */
class Gamification {
    public static void gaming() throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("\nWelcome To:\n|******** Gamification - Arena ********| ");
        String[] listofGames = {" Hang Man GAME", " Country_Capital"};
        for (int i = 0; i < listofGames.length; i++)
            System.out.println((i + 1) + ") " + listofGames[i]);
        System.out.print("Select Game: 1-2 ");
        char choiceOfGame = input.next().charAt(0);
        switch (choiceOfGame) {
            case '1': hang_Man_Game();break;
            case '2': country_Capital();break;
        }
    }
    //This game is more like a quiz that asks user about the capital of different countries
    //2D arrays are used for handling this technique
    //The results are displayed using formatting techniques.
    public static void country_Capital() throws IOException {
        String[][] list = {
                {"Pakistan", "Islamabad"}, {"France", "Paris"}, {"India", "New Delhi"},
                {"America", "Washington"}, {"Iraq", "Baghdad"}};
        shuffle(list);
        int win = 0;
        int a = 0;
        String[][] output = new String[5][3];
        while (a != 5) {
            for (int i = 0; i < list.length; i++) {
                for (int j = 0; j < 1; j++) {
                    System.out.print("What is the capital of " + list[i][j]);
                    Scanner in = new Scanner(System.in);
                    String answer = in.nextLine();
                    if (answer.equalsIgnoreCase(list[i][j + 1])) {
                        System.out.println("Correct answer!");
                        win++;
                    } else
                        System.out.println("Wrong answer! Correct answer is: " + list[i][j + 1]);
                    output[i] = new String[]{list[i][j] , answer , list[i][j + 1]};
                }
                a++;
            }
        }

        System.out.println("The correct count is: " + win + " out of 5");
        System.out.println("The summary is: ");
        System.out.printf("%10s           %10s           %10s","States","Answers","Capitals");
        System.out.println("\n------------------------------------------------------");
        for (int i = 0; i < output.length; i++) {
            System.out.println();
            for (int j = 0; j < 3; j++) {
                System.out.printf("%10s           ",output[i][j]);
            }
        }

    }
    public static void shuffle(String[][] list) {
        for (int i = 0; i < list.length; i++) {
            int rd = (int) (Math.random() * list.length);
            String[] temp = list[i];
            list[i] = list[rd];
            list[rd] = temp;
        }
    }
    /*
    This module calls the random word module to take a random list of words.
    Then it calls the play game module to start playing game.
    At the end, it ask from user to play it again or not. If user enters “y”, the while loop executes again and if user enters “n”, the while loop breaks.
     */
    public static void hang_Man_Game() {
        Scanner input = new Scanner(System.in);
        char y = 'y';
        while (y == 'y') {
            char[] list = randomWord();
            playGame(list);
            System.out.print("Do you want to play again: ");
            String yesNo = input.next().toLowerCase();
            y = yesNo.charAt(0);
        }
    }
    public static void playGame(char[] list) {
        String word = Arrays.toString(list);
        Scanner input = new Scanner(System.in);
        System.out.println("******* Guess Word *******");
        //list = a m e r i c a
        char[] asterisks = new char[list.length];
        Arrays.fill(asterisks, '*'); // ****************  fill
        int count = 0;
        int chance = 5;
        while (count < list.length && chance > 0) {
            System.out.print("Enter guess: ");
            System.out.print(asterisks);
            System.out.print(" : ");
            char guess = input.next().charAt(0);
            boolean x = false;
            int index = 0;
            for (int i = 0; i < list.length; i++) {
                if (guess == list[i]){
                    x = true;
                    index = i;
                }
            }
            if (x == true) {
                asterisks[index] = list[index];
                list[index] = '*';
                System.out.println(asterisks);
                count++;
            } else {
                System.out.println(guess + " is not part");
                System.out.println("Chances left: " + chance--);
            }
            if (count == list.length || chance == 0) {
                System.out.println("The correct is: " + word);
                System.out.println("You missed: " + (5 - chance));
            }
        }
    }
    public static char[] randomWord() {
        char[] list1 = {'p', 'a', 'k', 'i', 's', 't', 'a', 'n'};
        char[] list2 = {'a', 'm', 'e', 'r', 'i', 'c', 'a'};
        char[] list3 = {'n', 'e', 'w', 'z', 'e', 'l', 'a', 'n', 'd'};
        char[] list4 = {'t', 'u', 'r', 'k', 'e', 'y'};
        int random = (int) (1 + (Math.random() * 4));
        switch (random) {
            case 1: return list1;
            case 2: return list2;
            case 3: return list3;
            case 4: return list4;
        }return list1;
    }
}