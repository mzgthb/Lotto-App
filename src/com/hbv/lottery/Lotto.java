package com.hbv.lottery;

import com.hbv.Generator;

import java.io.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;

public class Lotto implements Generator {

    public int[] numbers = new int[6];
    public int[] unluckyNumbers = new int[6];
    int count = 0;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    public String formattedDateTime = currentDateTime.format(formatter);

    @Override
    public void start() {
        askForUnluckyNumbers();
    }

    @Override
    public void getArray(int[] arr) {
        System.out.println("---------------------------------------------");
        System.out.print("Deine Zahlen lauten: ");
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("\n---------------------------------------------");
    }

    @Override
    public void checkArrayValues() {
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                while(numbers[i] == numbers[j]) {
                    numbers[i] = (int) (Math.random() * 49);
                }
            }
        }
        Arrays.sort(numbers);

        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("\nGenerierte Tippreihe [6aus49]: ");
            for(int i : numbers) {
                buffer.write(i + " ");
            }
            buffer.write("\n \n");
            buffer.close();
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
        getArray(numbers);
    }

    @Override
    public void askForUnluckyNumbers() {
        if(checkUnluckyNumbers()) {
            System.out.println("---------------------------------------------");
            System.out.println("Bereits gespeicherte Unglückszahlen wurden gefunden!");

            try {
                BufferedReader reader = new BufferedReader(new FileReader("unluckynumbers.txt"));
                String line = reader.readLine();
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
                if(input.equalsIgnoreCase("Ja")) {
                    BufferedReader reader = new BufferedReader(new FileReader("unluckynumbers.txt"));
                    String line = reader.readLine();

                    count = 0;
                    while(line != null) {
                        unluckyNumbers[count] = Integer.parseInt(line);
                        line = reader.readLine();
                        count++;
                    }

                    FileWriter writer = new FileWriter("log.txt", true);
                    BufferedWriter buffer = new BufferedWriter(writer);

                    buffer.write("Datum: " + formattedDateTime);
                    buffer.write("\nUnglueckszahlen: ");
                    for(int unluckyNumber : unluckyNumbers) {
                        buffer.write(unluckyNumber + " ");
                    }
                    buffer.close();
                    generateNumbers(true);
                } else if(input.equalsIgnoreCase("Nein")) {
                    askGenerateUnluckyNumbers();
                } else if(input.equalsIgnoreCase("Loeschen")) {
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

    @Override
    public void addUnluckyNumbers() {
        System.out.println("---------------------------------------------");
        System.out.println("Wie viele Zahlen moechtest du ausschließen? (0 bis 6)");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);

            count = Integer.parseInt(reader.readLine());
            buffer.write("Unglueckszahlen: ");

            while(count < 0 || count > 6) {
                System.out.println("---------------------------------------------");
                System.out.println("Zahl nicht gueltig! (0 bis 6)");
                System.out.println("---------------------------------------------");
                System.out.print("Deine Auswahl: ");

                count = Integer.parseInt(reader.readLine());
            }

            int n;

            for(int i = 0; i < count; i++) {
                System.out.print(i + 1 + ". Zahl: ");
                n = Integer.parseInt(reader.readLine());

                while(n < 0 || n > 49) {
                    System.out.println("---------------------------------------------");
                    System.out.println("Zahl nicht gueltig! (0 bis 49)");
                    System.out.println("---------------------------------------------");
                    System.out.print(i + 1 + ". Zahl: ");
                    n = Integer.parseInt(reader.readLine());
                }

                unluckyNumbers[i] = n;
                buffer.write(n + " ");
            }
            buffer.close();
            saveUnluckyNumbers();
            generateNumbers(true);
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    @Override
    public boolean checkUnluckyNumbers() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("unluckynumbers.txt"));
            String line = reader.readLine();
            if(line == null) {
                reader.close();
                return false;
            } else {
                reader.close();
                return true;
            }
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void saveUnluckyNumbers() {
        try {
            FileWriter writer = new FileWriter("unluckynumbers.txt");
            BufferedWriter buffer = new BufferedWriter(writer);
            for(int i : unluckyNumbers) {
                buffer.write(i + "\n");
            }
            buffer.close();
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }

    }

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

            buffer.write("Datum: " + formattedDateTime + "\n");

            if(input.equalsIgnoreCase("Ja")) {
                buffer.close();
                addUnluckyNumbers();
            } else if (input.equalsIgnoreCase("Nein")) {
                buffer.write("Unglueckszahlen: Nicht vorhanden");
                buffer.close();
                generateNumbers(false);
            } else {
                System.out.println("---------------------------------------------");
                System.out.println("Ungueltige Antwort!");
                System.out.println("---------------------------------------------");
            }
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    @Override
    public void generateNumbers(boolean unluckyNumbers) {
        if (unluckyNumbers) {
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 49);
            }
            for(int i = 0; i < numbers.length; i++) {
                for(int j = 0; j < numbers.length; j++) {
                    while(numbers[i] == this.unluckyNumbers[j]) {
                        numbers[i] = (int) (Math.random() * 49);
                    }
                }
            }
        } else {
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 49);
            }
        }
        checkArrayValues();
    }
}
