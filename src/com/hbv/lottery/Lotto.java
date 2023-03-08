package com.hbv.lottery;

import com.hbv.Generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lotto implements Generator {

    int[] numbers = new int[6];
    int[] unluckyNumbers = new int[6];

    public void start() throws IOException {
        askForUnluckyNumbers();
        System.out.print("Deine Zahlen lauten: ");
        for (int number : numbers) {
            System.out.print(number + " ");
        }
    }

    public boolean askForUnluckyNumbers() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Lotto startet...");
        System.out.println("Möchtest du Unglueckszahlen auswaehlen? (Ja/Nein)");
        System.out.print("Deine Auswahl: ");

        String input = reader.readLine();

        if(input.equalsIgnoreCase("Ja")) {
            System.out.println("Wie viele Zahlen moechtest du ausschließen?");
            System.out.print("Deine Auswahl: ");
            addUnluckyNumbers(Integer.parseInt(reader.readLine()));
        } else if (input.equalsIgnoreCase("Nein")) {
            generateNumbers(false);
        } else {
            System.out.println("Ungültige Antwort!");
        }
        return false;
    }

    public void addUnluckyNumbers(int count) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        for(int i = 0; i < count; ++i) {
            System.out.print(i + 1 + ". Zahl: ");
            unluckyNumbers[i] = Integer.parseInt(reader.readLine());
        }
        generateNumbers(true);
    }

    @Override
    public int[] generateNumbers(boolean unluckyNumbers) {
        if(unluckyNumbers) {
            for (int i = 0; i < 6; ++i) {
                for(int j = 0; j < this.unluckyNumbers.length; ++j) {
                    numbers[i] = (int) (Math.random() * 49);
                    while(numbers[i] == this.unluckyNumbers.length) {
                        numbers[i] = (int) (Math.random() * 49);
                    }
                }
            }
            return numbers;
        } else {
            for (int i = 0; i < 6; ++i) {
                numbers[i] = (int) (Math.random() * 49);
            }
        }
        return numbers;
    }
}