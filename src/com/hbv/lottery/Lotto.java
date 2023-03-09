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
        sortArray(numbers);
        getArray(numbers);
    }

    public void getArray(int[] arr) {
        System.out.println("---------------------------------------------");
        System.out.print("Deine Zahlen lauten: ");
        for(int i : arr)
            System.out.print(i + " ");
        System.out.println("\n---------------------------------------------");
    }

    public void sortArray(int[] arr) {
        for(int i = 0; i < arr.length; ++i) {
            for(int j = i + 1; j < arr.length - 1; ++j) {
                if(arr[i] > arr[j]) {
                    int tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    public boolean askForUnluckyNumbers() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("---------------------------------------------");
        System.out.println("Möchtest du Unglueckszahlen auswaehlen? (Ja/Nein)");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        String input = reader.readLine();

        if(input.equalsIgnoreCase("Ja")) {
            System.out.println("---------------------------------------------");
            System.out.println("Wie viele Zahlen moechtest du ausschließen?");
            System.out.println("---------------------------------------------");
            System.out.print("Deine Auswahl: ");

            int input2 = Integer.parseInt(reader.readLine());

            while(input2 < 0 ||input2 > 6) {
                System.out.println("---------------------------------------------");
                System.out.println("Zahl nicht gueltig (0 bis 6)");
                System.out.println("---------------------------------------------");
                System.out.print("Deine Auswahl: ");
                input2 = Integer.parseInt(reader.readLine());
            }
            addUnluckyNumbers(input2);
        } else if (input.equalsIgnoreCase("Nein")) {
            generateNumbers(false);
        } else {
            System.out.println("Ungueltige Antwort!");
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
        if(!unluckyNumbers) {
            for (int i = 0; i < 6; ++i) {
                numbers[i] = (int) (Math.random() * 49);
            }
            for(int i = 0; i < numbers.length; ++i) {
                for(int j = i + 1; j < numbers.length; ++j) {
                    while(numbers[i] == numbers[j]) {
                        numbers[i] = (int) (Math.random() * 49);
                    }
                }
            }
            return numbers;
        } else {
            for(int i = 0; i < numbers.length; ++i) {
                for(int j = 0; j < this.unluckyNumbers.length; ++j) {
                    while(numbers[i] == this.unluckyNumbers[j]) {
                        numbers[i] = (int) (Math.random() * 49);
                    }
                }
            }
        }
        return numbers;
    }
}
