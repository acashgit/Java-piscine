package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberWorkerTest {
    NumberWorker nw;
    @BeforeEach
    void beforeEach(){
        nw = new NumberWorker();
    }

    @ParameterizedTest
    @ValueSource(ints = {5 , 17, 1103})
    void isPrimeForPrimes(int number){
        Assertions.assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {4 , 21, 1105})
    void isPrimeForNotPrimes(int number){
        Assertions.assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, 0, 1})
    void isPrimeForIncorrectNumbers(int number){
        Assertions.assertThrows(NumberWorker.IllegalNumberException.class, () -> nw.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void checkDigitSum(int digit, int result){
        Assertions.assertEquals(nw.digitsSum(digit), result);
    }
}