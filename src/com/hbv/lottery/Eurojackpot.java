package com.hbv.lottery;

// used librarys
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Eurojackpot extends Lotto {

    // generated random numbers (5aus50)
    public int[] numbers = new int[5];
    // generated random numbers (2aus10)
    public int[] numbers2 = new int[2];

    // method to check double values at generated numbers
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
        // sort array (low to high)
        Arrays.sort(numbers);
        Arrays.sort(numbers2);
        // print array
        getArray(numbers);
        getArray(numbers2);

        try {
            // open log.txt and append a string
            FileWriter writer = new FileWriter("log.txt", true);
            BufferedWriter buffer = new BufferedWriter(writer);

            buffer.write("\nGenerierte Tippreihe [5aus50]: ");

            // write all generated numbers in "log.txt"
            for(int i : numbers) {
                buffer.write(i + " ");
            }

            buffer.write("\nGenerierte Tippreihe [2aus10]: ");

            // write all generated numbers in "log.txt"
            for(int i : numbers2) {
                buffer.write(i + " ");
            }
            buffer.write("\n \n");
            buffer.close();
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
            // generate random numbers between 0 and 50
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 50);
            }
            // replace any random number which is an unluckynumbers with a new number
            for(int i = 0; i < numbers.length; i++) {
                for(int j = 0; j < 6; j++) {
                    while(numbers[i] == this.unluckyNumbers[j]) {
                        numbers[i] = (int) (Math.random() * 50);
                    }
                }
            }
            // generate random numbers between 0 and 10
            for(int i = 0; i < numbers2.length; i++) {
                numbers2[i] = (int) (Math.random() * 10);
            }
            // replace any random number which is an unluckynumbers with a new number
            for(int i = 0; i < numbers2.length; i++) {
                for(int j = 0; j < 2; j++) {
                    while(numbers2[i] == this.unluckyNumbers[j]) {
                        numbers2[i] = (int) (Math.random() * 10);
                    }
                }
            }
        // if unlucky numbers are not used
        } else {
            // generate random numbers between 0 and 50
            for(int i = 0; i < numbers.length; i++) {
                numbers[i] = (int) (Math.random() * 50);
            }
            // generate random numbers between 0 and 10
            for(int i = 0; i < numbers2.length; i++) {
                numbers2[i] = (int) (Math.random() * 10);
            }
        }
        checkArrayValues();
    }
}
