package com.hbv.lottery;

import com.hbv.Generator;

// used librarys
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Lotto implements Generator {

    // generated random numbers (6aus49)
    public int[] numbers = new int[6];
    // entered unlucky numbers
    public int[] unluckyNumbers = new int[6];
    // used to get amount of unlucky numbers
    int count = 0;
    // bufferedreader for getting input
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    // getting locale date
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public String formattedDateTime = currentDateTime.format(formatter);

    // method to start lottery
    @Override
    public void start() {
        askForUnluckyNumbers();
    }

    // method to print an array
    @Override
    public void getArray(int[] arr) {
        System.out.println("---------------------------------------------");
        System.out.print("Deine Zahlen lauten: ");
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("\n---------------------------------------------");
    }

    // method to check double values at generated numbers
    @Override
    public void checkArrayValues() {
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                while(numbers[i] == numbers[j]) {
                    numbers[i] = (int) (Math.random() * 49);
                }
            }
        }
        // sort array (low to high)
        Arrays.sort(numbers);

        try {
            // open log.txt and append a string
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("\nGenerierte Tippreihe [6aus49]: ");
            // write all generated numbers in "log.txt"
            for(int i : numbers) {
                buffer.write(i + " ");
            }
            buffer.write("\n \n");
            buffer.close();
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
        // printing generated numbers
        getArray(numbers);
    }

    // method for asking if the user want to use unlucky numbers
    @Override
    public void askForUnluckyNumbers() {
        if(checkUnluckyNumbers()) {
            System.out.println("---------------------------------------------");
            System.out.println("Bereits gespeicherte Unglückszahlen wurden gefunden!");

            // open "unluckynumbers.txt" file to check if unluckynumbers are already saved
            try {
                BufferedReader reader = new BufferedReader(new FileReader("unluckynumbers.txt"));
                String line = reader.readLine();
                // print already saved unlucky numbers
                while(line != null) {
                    System.out.print(line + " ");
                    line = reader.readLine();
                }
                reader.close();
                System.out.println("\n");
            } catch (IOException e) {
                System.out.println("Fehler: " + e.getMessage());
            }
            System.out.println("Willst du diese Zahlen verwenden? (Ja/Nein/Loeschen)");
            System.out.println("\n---------------------------------------------");
            System.out.print("Deine Auswahl: ");

            try {
                String input = reader.readLine();
                // if user want to use saved unlucky numbers
                if(input.equalsIgnoreCase("Ja")) {
                    BufferedReader reader = new BufferedReader(new FileReader("unluckynumbers.txt"));
                    String line = reader.readLine();

                    count = 0;
                    // save numbers in "unluckynumbers.txt" to an array
                    while(line != null) {
                        unluckyNumbers[count] = Integer.parseInt(line);
                        line = reader.readLine();
                        count++;
                    }

                    // open "log.txt" and write some strings
                    FileWriter writer = new FileWriter("log.txt", true);
                    BufferedWriter buffer = new BufferedWriter(writer);

                    // write date and unlucky numbers in "log.txt"
                    buffer.write("Datum: " + formattedDateTime);
                    buffer.write("\nUnglueckszahlen: ");
                    for(int unluckyNumber : unluckyNumbers) {
                        buffer.write(unluckyNumber + " ");
                    }
                    buffer.close();
                    // start to generate numbers
                    generateNumbers(true);
                // if user doenst want use saved unlucky numbers
                } else if(input.equalsIgnoreCase("Nein")) {
                    askGenerateUnluckyNumbers();
                // if user want to delete saved unlucky numbers
                } else if(input.equalsIgnoreCase("Loeschen")) {
                    // clear "unluckynumbers.txt"
                    BufferedWriter bwriter = new BufferedWriter(new FileWriter("unluckynumbers.txt"));
                    bwriter.write("");
                    bwriter.close();
                    askGenerateUnluckyNumbers();
                }
            } catch (IOException e) {
                System.out.println("Fehler: " + e.getMessage());
            }

        } else {
            askGenerateUnluckyNumbers();
        }
    }

    // write all generated numbers in "log.txt"
    @Override
    public void addUnluckyNumbers() {
        System.out.println("---------------------------------------------");
        System.out.println("Wie viele Zahlen moechtest du ausschließen? (0 bis 6)");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        try {
            // open "log.txt" and add some
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);

            count = Integer.parseInt(reader.readLine());
            buffer.write("Unglueckszahlen: ");

            // check if the amount of unlucky numbers are between 0 and 6
            while(count < 0 || count > 6) {
                System.out.println("---------------------------------------------");
                System.out.println("Zahl nicht gueltig! (0 bis 6)");
                System.out.println("---------------------------------------------");
                System.out.print("Deine Auswahl: ");

                count = Integer.parseInt(reader.readLine());
            }

            // temp variable to save input of unlucky numbers
            int n;

            for(int i = 0; i < count; i++) {
                System.out.print(i + 1 + ". Zahl: ");
                n = Integer.parseInt(reader.readLine());

                // check if unlucky numbers are between 0 and 49
                while(n < 0 || n > 49) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Zahl nicht gueltig! (0 bis 49)");
                    System.out.println("---------------------------------------------");
                    System.out.print(i + 1 + ". Zahl: ");
                    n = Integer.parseInt(reader.readLine());
                }
                // save unlucky numbers in an array
                unluckyNumbers[i] = n;
                buffer.write(n + " ");
            }
            buffer.close();
            // write unlucky numbers to "unluckynumbers.txt"
            saveUnluckyNumbers();
            // generate random numbers
            generateNumbers(true);
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    // method to check if some unluckynumbers are already saved
    @Override
    public boolean checkUnluckyNumbers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("unluckynumbers.txt"));
            String line = reader.readLine();
            // return false if there are no unlucky numbers
            if(line == null) {
                reader.close();
                return false;
            // return true of there a unlucky numbers
            } else {
                reader.close();
                return true;
            }
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
        return false;
    }

    // method to save unlucky numbers to "unluckynumbers.txt"
    @Override
    public void saveUnluckyNumbers() {
        try {
            FileWriter writer = new FileWriter("unluckynumbers.txt");
            BufferedWriter buffer = new BufferedWriter(writer);
            // write any number of the array unluckynumbers in "unluckynumbers.txt"
            for(int i : unluckyNumbers) {
                buffer.write(i + "\n");
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

    }

    // method to ask if the user want to enter unlucky numbers
    @Override
    public void askGenerateUnluckyNumbers() {
        System.out.println("---------------------------------------------");
        System.out.println("Möchtest du Unglueckszahlen auswaehlen? (Ja/Nein)");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);
            String input = reader.readLine();

            // write date in the "log.txt"
            buffer.write("Datum: " + formattedDateTime + "\n");

            // if the user want to enter some unlucky numbers
            if(input.equalsIgnoreCase("Ja")) {
                buffer.close();
                addUnluckyNumbers();
            // if the user want to enter some unlucky numbers
            } else if (input.equalsIgnoreCase("Nein")) {
                buffer.write("Unglueckszahlen: Nicht vorhanden");
                buffer.close();
                generateNumbers(false);
            // if the user entered an invalid input
            } else {
                System.out.println("---------------------------------------------");
                System.out.println("Ungueltige Antwort!");
                System.out.println("---------------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    // method to generate random numbers
    // (boolean for checking if unlucky numbers are used)
    @Override
    public void generateNumbers(boolean unluckyNumbers) {
        // if unlucky numbers are used
        if (unluckyNumbers) {
            // generate random numbers between 0 and 49
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 49);
            }
            // replace any random number which is an unluckynumbers with a new number
            for(int i = 0; i < numbers.length; i++) {
                for(int j = 0; j < numbers.length; j++) {
                    while(numbers[i] == this.unluckyNumbers[j]) {
                        numbers[i] = (int) (Math.random() * 49);
                    }
                }
            }
        // if unlucky numbers are not used
        } else {
            // generate random numbers between 0 and 49
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 49);
            }
        }
        checkArrayValues();
    }
}
