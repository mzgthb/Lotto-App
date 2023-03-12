package com.hbv.test;

import com.hbv.lottery.Lotto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LottoTest {

    // check if the function "checkArrayValues" works correctly
    @Test
    public void testCheckArrayValues() {
        Lotto lotto = new Lotto();
        lotto.checkArrayValues();
        int[] numbers = lotto.numbers;

        // check if the numbers are between 0 and 49
        for(int number : numbers) {
            Assertions.assertTrue(number >= 0 && number <= 49);
        }

        // check if there are no double values
        for(int i = 0; i < numbers.length; i++) {
            for(int j = i + 1; j < numbers.length; ++j) {
                Assertions.assertNotEquals(numbers[i], numbers[j]);
            }
        }
    }

    // check if the function "checkUnluckyNumbers" works correctly
    @Test
    public void testCheckUnluckyNumbers() {
        Lotto lotto = new Lotto();
        Assertions.assertTrue(lotto.checkUnluckyNumbers());
    }

}
