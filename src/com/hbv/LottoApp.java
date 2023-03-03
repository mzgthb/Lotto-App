package com.hbv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

enum Lottery {LOTTO, EUROJACKPOT}

public class LottoApp {

    public static void main(String[] args) throws IOException {
        Lottery lottery = initLottery();

        switch (lottery) {
            case LOTTO -> System.out.println("Du hast LOTTO ausgewählt!");
            case EUROJACKPOT -> System.out.println("Du hast EUROJACKPOT ausgewaehlt!");
            default -> System.out.println("Du hast EUROJACKPOT ausgewaehlt!");
        }
    }

    static Lottery initLottery() throws IOException {
        Lottery lottery = null;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("---------------------------------------------");
        System.out.println("Welche Tippreihe soll generiert werden?");
        System.out.println("\n1. 6aus49");
        System.out.println("2. Eurojackpot");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        String input = reader.readLine();

        if (input.equalsIgnoreCase("6aus49")) {
            System.out.println("******* 6aus49 wurde ausgewaehlt! *******");
            lottery = Lottery.LOTTO;
        } else if (input.equalsIgnoreCase("Eurojackpot")) {
            System.out.println("******* Eurojackpot wurde ausgewaehlt! *******");
            lottery = Lottery.EUROJACKPOT;
        } else if (input.isEmpty()) {
            System.out.println("******* 6aus49 wurde ausgewaehlt! *******");
            lottery = Lottery.LOTTO;
        } else {
            boolean isValid = false;
            while (!isValid) {
                System.out.println("---------------------------------------------");
                System.out.println("*** UNGÜLTIGE AUSWAHL! ***");
                System.out.println("Welche Tippreihe soll generiert werden?");
                System.out.println("\n1. 6aus49");
                System.out.println("2. Eurojackpot");
                System.out.println("---------------------------------------------");
                System.out.print("Deine Auswahl: ");

                input = reader.readLine();

                if (input.equalsIgnoreCase("6aus49")) {
                    isValid = true;
                    System.out.println("******* 6aus49 wurde ausgewaehlt! *******");
                    lottery = Lottery.LOTTO;
                } else if (input.equalsIgnoreCase("Eurojackpot")) {
                    isValid = true;
                    System.out.println("******* Eurojackpot wurde ausgewaehlt! *******");
                    lottery = Lottery.EUROJACKPOT;
                } else if (input.isEmpty()) {
                    isValid = true;
                    System.out.println("******* 6aus49 wurde ausgewaehlt! *******");
                    lottery = Lottery.LOTTO;
                }
            }
        }
        return lottery;
    }
}