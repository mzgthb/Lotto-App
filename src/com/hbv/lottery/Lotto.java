package com.hbv.lottery;

import com.hbv.Generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Lotto implements Generator {

    int[] numbers = new int[6];
    int[] unluckyNumbers = new int[6];

    public void start() throws IOException {
        askForUnluckyNumbers();
        getArray(numbers);
    }

    public void getArray(int[] arr) {
        System.out.println("---------------------------------------------");
        System.out.print("Deine Zahlen lauten: ");
        for(int i : arr)
            System.out.print(i + " ");
        System.out.println("\n---------------------------------------------");
    }

    public void askForUnluckyNumbers() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("---------------------------------------------");
        System.out.println("Möchtest du Unglueckszahlen auswaehlen? (Ja/Nein)");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        String input = reader.readLine();

        if(input.equalsIgnoreCase("Ja")) {
            addUnluckyNumbers();
        } else if (input.equalsIgnoreCase("Nein")) {
            generateNumbers(false);
        } else {
            System.out.println("---------------------------------------------");
            System.out.println("Ungueltige Antwort!");
            System.out.println("---------------------------------------------");
        }
    }

    public void addUnluckyNumbers() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("---------------------------------------------");
        System.out.println("Wie viele Zahlen moechtest du ausschließen?");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        int count = Integer.parseInt(reader.readLine());

        while(count < 0 || count > 6) {
            System.out.println("---------------------------------------------");
            System.out.println("Zahl nicht gueltig! (0 bis 6)");
            System.out.println("---------------------------------------------");
            System.out.print("Deine Auswahl: ");

            count = Integer.parseInt(reader.readLine());
        }

        for(int i = 0; i < count; i++) {
            System.out.print(i + 1 + ". Zahl: ");
            unluckyNumbers[i] = Integer.parseInt(reader.readLine());
        }
        generateNumbers(true);
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
    }

    @Override
    public int[] generateNumbers(boolean unluckyNumbers) {
        if (unluckyNumbers) {
            for(int i = 0; i < 6; i++) {
                numbers[i] = (int) (Math.random() * 49);
            }
            for(int i = 0; i < numbers.length; i++) {
                for(int j = 0; j < 6; j++) {
                    if(numbers[i] == this.unluckyNumbers[j]) {
                        numbers[i] = (int) (Math.random() * 49);
                    }
                }
            }
        } else {
            for(int i = 0; i < 6; i++) {
                numbers[i] = (int) (Math.random() * 49);
            }
        }
        checkArrayValues();
        return numbers;
    }
}
