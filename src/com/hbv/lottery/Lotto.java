package com.hbv.lottery;

import com.hbv.Generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;

public class Lotto implements Generator {

    int[] numbers = new int[6];
    int[] unluckyNumbers = new int[6];
    int count = 0;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDateTime = currentDateTime.format(formatter);

    public void start() {
        askForUnluckyNumbers();
    }

    public void getArray(int[] arr) {
        System.out.println("---------------------------------------------");
        System.out.print("Deine Zahlen lauten: ");
        for(int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println("\n---------------------------------------------");
    }

    public void checkArrayValues() {
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; j++) {
                while(numbers[i] == numbers[j]) {
                    numbers[i] = (int) (Math.random() * 49);
                }
            }
        }
        Arrays.sort(numbers);
        getArray(numbers);

        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write("\nGenerierte Tippreihe: ");
            for(int i : numbers) {
                buffer.write(i + " ");
            }
            buffer.write("\n \n");
            buffer.close();
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

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
                    Spiel: 6aus49
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

    public void addUnluckyNumbers() {
        System.out.println("---------------------------------------------");
        System.out.println("Wie viele Zahlen moechtest du ausschließen? (0 bis 6)");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        try {
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);

            count = Integer.parseInt(reader.readLine());
            buffer.write("Unglückszahlen (" + count + " Stueck): ");

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
                unluckyNumbers[i] = n;
                buffer.write(n + " ");
            }
            buffer.close();
            generateNumbers(true);
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
    }

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
