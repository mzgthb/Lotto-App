package com.hbv.lottery;

import com.hbv.Generator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lotto implements Generator {

    int[] numbers = new int[6];
    int[] unluckyNumbers;

    public void start() throws IOException {
        askForUnluckyNumbers();
        for(int i = 0; i < numbers.length; ++i) {
            System.out.print(numbers[i] + " ");
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
           return true;
        } else if (input.equalsIgnoreCase("Nein")) {
            generateNumbers();
        } else {
            System.out.println("Ungültige Antwort!");
        }
        return false;
    }

    public int[] addUnluckyNumbers(int count) {
        numbers = new int[count];
        return unluckyNumbers;
    }

    @Override
    public int[] generateNumbers() {
        for(int i = 0; i < 6; ++i) {
            numbers[i] = (int) (Math.random() * 49);
        }
        return numbers;
    }
}