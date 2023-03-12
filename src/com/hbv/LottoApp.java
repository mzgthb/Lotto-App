package com.hbv;

// used librarys
import com.hbv.lottery.Eurojackpot;
import com.hbv.lottery.Lotto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// enums to check lottery variant
enum Lottery {LOTTO, EUROJACKPOT}

public class LottoApp {

    public static void main(String[] args) {
        // create lottery object
        Lottery lottery = initLottery();
        // check lottery variant
        switch (lottery) {
            case LOTTO:
                // create lotto object and start
                Lotto lotto = new Lotto();
                lotto.start();
                break;
            case EUROJACKPOT:
                // create eurojackpot object and start
                Eurojackpot eurojackpot = new Eurojackpot();
                eurojackpot.start();
                break;
            default:
                // create lotto as default object and start
                lotto = new Lotto();
                lotto.start();
        }
    }

    // initialise lottery
    static Lottery initLottery() {
        Lottery lottery = null;
        // bufferedreader for getting input
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("---------------------------------------------");
        System.out.println("Welche Tippreihe soll generiert werden?");
        System.out.println("\n1. 6aus49");
        System.out.println("2. Eurojackpot");
        System.out.println("---------------------------------------------");
        System.out.print("Deine Auswahl: ");

        try {
            String input = reader.readLine();

            // if user choose "6aus49"
            if (input.equalsIgnoreCase("6aus49")) {
                System.out.println("******* 6aus49 wurde ausgewaehlt! *******");
                lottery = Lottery.LOTTO;
            // if user choose "eurojackpot"
            } else if (input.equalsIgnoreCase("Eurojackpot")) {
                System.out.println("******* Eurojackpot wurde ausgewaehlt! *******");
                lottery = Lottery.EUROJACKPOT;
            // if user choose nothing (clicked on enter)
            } else if (input.isEmpty()) {
                System.out.println("******* 6aus49 wurde ausgewaehlt! *******");
                lottery = Lottery.LOTTO;
            } else {
                // if user choose a not valid lottery variant
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

                    // if user choose "6aus49"
                    if (input.equalsIgnoreCase("6aus49")) {
                        isValid = true;
                        System.out.println("******* 6aus49 wurde ausgewaehlt! *******");
                        lottery = Lottery.LOTTO;
                    // if user choose "Eurojackpot"
                    } else if (input.equalsIgnoreCase("Eurojackpot")) {
                        isValid = true;
                        System.out.println("******* Eurojackpot wurde ausgewaehlt! *******");
                        lottery = Lottery.EUROJACKPOT;
                    // if user choose nothing (clicked on enter)
                    } else if (input.isEmpty()) {
                        isValid = true;
                        System.out.println("******* 6aus49 wurde ausgewaehlt! *******");
                        lottery = Lottery.LOTTO;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Fehler: " + e.getMessage());
        }
        return lottery;
    }
}
