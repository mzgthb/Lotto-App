package com.hbv.lottery;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Eurojackpot extends Lotto {

    int[] numbers = new int[5];
    int[] numbers2 = new int[2];
    int[] unluckyNumbers = new int[6];
    int[] unluckyNumbers2 = new int[2];

    @Override
    public void checkArrayValues() {
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                while(numbers[i] == numbers[j]) {
                    numbers[i] = (int) (Math.random() * 50);
                }
            }
        }
        for(int i = 0; i < numbers2.length; i++) {
            for(int j = i + 1; j < numbers2.length; j++) {
                while(numbers2[i] == numbers2[j]) {
                    numbers2[i] = (int) (Math.random() * 10);
                }
            }
        }
        Arrays.sort(numbers);
        Arrays.sort(numbers2);
        getArray(numbers);
        getArray(numbers2);

        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write("\nGenerierte Tippreihe [5aus50]: ");

            for(int i : numbers) {
                buffer.write(i + " ");
            }

            buffer.write("\nGenerierte Tippreihe [2aus10]: ");

            for(int i : numbers2) {
                buffer.write(i + " ");
            }
            buffer.write("\n \n");
            buffer.close();
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    @Override
    public void askForUnluckyNumbers() {
        System.out.println("---------------------------------------------");
        System.out.println("Möchtest du Unglueckszahlen auswaehlen? (Ja/Nein)");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);
            String input = reader.readLine();

            buffer.write("""
                    Spiel: Eurojackpot
                    """);
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
    public void addUnluckyNumbers() {
        System.out.println("---------------------------------------------");
        System.out.println("5aus50");
        System.out.println("Wie viele Zahlen moechtest du ausschließen? (0 bis 6)");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);

            count = Integer.parseInt(reader.readLine());

            while(count < 0 || count > 6) {
                System.out.println("---------------------------------------------");
                System.out.println("Zahl nicht gueltig! (0 bis 6)");
                System.out.println("---------------------------------------------");
                System.out.print("Deine Auswahl: ");

                count = Integer.parseInt(reader.readLine());
                buffer.write("Unglückszahlen (" + count + " Stueck) [5aus50]: ");
                int n;

                for(int i = 0; i < count; i++) {
                    System.out.print(i + 1 + ". Zahl: ");
                    n = Integer.parseInt(reader.readLine());
                    unluckyNumbers[i] = n;
                    buffer.write(n + " ");
                }

                System.out.println("---------------------------------------------");
                System.out.println("2aus10");
                System.out.println("Wie viele Zahlen moechtest du ausschließen? (0 bis 2)");
                System.out.println("---------------------------------------------");
                System.out.print("Deine Auswahl: ");

                count = Integer.parseInt(reader.readLine());
                buffer.write("\nUnglückszahlen (" + count + " Stueck) [2aus10]: ");

                for(int i = 0; i < count; i++) {
                    System.out.print(i + 1 + ". Zahl: ");
                    n = Integer.parseInt(reader.readLine());
                    unluckyNumbers2[i] = n;
                    buffer.write(n + " ");
                }
                buffer.close();
                generateNumbers(true);
            }

            buffer.write("Unglückszahlen (" + count + " Stueck) [5aus50]: ");
            int n;

            for(int i = 0; i < count; i++) {
                System.out.print(i + 1 + ". Zahl: ");
                n = Integer.parseInt(reader.readLine());
                unluckyNumbers[i] = n;
                buffer.write(n + " ");
            }

            System.out.println("---------------------------------------------");
            System.out.println("2aus10");
            System.out.println("Wie viele Zahlen moechtest du ausschließen? (0 bis 2)");
            System.out.println("---------------------------------------------");
            System.out.print("Deine Auswahl: ");

            count = Integer.parseInt(reader.readLine());
            buffer.write("\nUnglückszahlen (" + count + " Stueck) [2aus10]: ");

            for(int i = 0; i < count; i++) {
                System.out.print(i + 1 + ". Zahl: ");
                n = Integer.parseInt(reader.readLine());
                unluckyNumbers2[i] = n;
                buffer.write(n + " ");
            }
            buffer.close();
            generateNumbers(true);
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

    @Override
    public void generateNumbers(boolean unluckyNumbers) {
        if (unluckyNumbers) {
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 50);
            }
            for(int i = 0; i < numbers.length; i++) {
                for(int j = 0; j < numbers.length; j++) {
                    while(numbers[i] == this.unluckyNumbers[j]) {
                        numbers[i] = (int) (Math.random() * 50);
                    }
                }
            }
            for(int i = 0; i < numbers2.length; i++) {
                numbers2[i] = (int) (Math.random() * 10);
            }
            for(int i = 0; i < numbers2.length; i++) {
                for(int j = 0; j < numbers2.length; j++) {
                    while(numbers2[i] == this.unluckyNumbers2[j]) {
                        numbers2[i] = (int) (Math.random() * 10);
                    }
                }
            }
        } else {
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 50);
            }
            for(int i = 0; i < numbers2.length; i++) {
                numbers2[i] = (int) (Math.random() * 10);
            }
        }
        checkArrayValues();
    }
}
