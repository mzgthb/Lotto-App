package com.hbv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LottoApp {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Welche Tippreihe soll generiert werden?");
        System.out.println("\n1. 6aus49");
        System.out.println("2. Eurojackpot");
        System.out.print("\nDeine Auswahl: ");

        String input = reader.readLine();

        if (input.equalsIgnoreCase("6aus49")) {
            System.out.println("Ausgewaehlt: 6aus49");
        } else if (input.equalsIgnoreCase("Eurojackpot")) {
            System.out.println("Ausgewaehlt: Eurojackpot");
        } else if (input.isEmpty()) {
            System.out.println("Ausgewaehlt: 6aus49");
        } else {
            boolean isValid = false;
            while (!isValid) {
                System.out.println("*** UNGÜLTIGE AUSWAHL! ***");
                System.out.println("Welche Tippreihe soll generiert werden?");
                System.out.println("\n1. 6aus49");
                System.out.println("2. Eurojackpot");
                System.out.print("\nDeine Auswahl: ");

                input = reader.readLine();

                if (input.equalsIgnoreCase("6aus49")) {
                    isValid = true;
                    System.out.println("Ausgewaehlt: 6aus49");
                } else if (input.equalsIgnoreCase("Eurojackpot")) {
                    isValid = true;
                    System.out.println("Ausgewaehlt: Eurojackpot");
                } else if (input.isEmpty()) {
                    isValid = true;
                    System.out.println("Ausgewaehlt: 6aus49");
                }
            }
        }
    }
}
