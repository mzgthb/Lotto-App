package com.hbv.lottery;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Eurojackpot extends Lotto {

    public int[] numbers = new int[5];
    public int[] numbers2 = new int[2];

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
                    while(numbers2[i] == this.unluckyNumbers[j]) {
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
