package com.hbv.test;

import com.hbv.lottery.Eurojackpot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EurojackpotTest {

    @Test
    public void testCheckArrayValues() {
        Eurojackpot eurojackpot = new Eurojackpot();
        eurojackpot.checkArrayValues();
        int[] numbers = eurojackpot.numbers;
        int[] numbers2 = eurojackpot.numbers2;

        for(int number : numbers) {
            Assertions.assertTrue(number >= 0 && number <= 50);
        }
        for(int number : numbers2) {
            Assertions.assertTrue(number >= 0 && number <= 10);
        }

        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; ++j) {
                Assertions.assertNotEquals(numbers[i], numbers[j]);
            }
        }
        for(int i = 0; i < numbers2.length; i++) {
            for(int j = i + 1; j < numbers2.length; ++j) {
                Assertions.assertNotEquals(numbers[i], numbers[j]);
            }
        }
    }

    @Test
    public void testCheckUnluckyNumbers() {
        Eurojackpot eurojackpot = new Eurojackpot();
        Assertions.assertTrue(eurojackpot.checkUnluckyNumbers());
    }

}
