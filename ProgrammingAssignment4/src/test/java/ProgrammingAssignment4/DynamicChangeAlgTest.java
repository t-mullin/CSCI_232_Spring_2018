/**
 * Author: Tristan Mullin
 * Date: 4/24/18
 * Overview: This program implements the change making problem using dynamic programing
 * Note: JUnit 5 is used to implement testing
 */
package ProgrammingAssignment4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DynamicChangeAlgTest {

    //tests to see if the coinArray was empty
    @Test
    public void testEmptyArray() {
        int coinArray[] = {};
        int changeAmount = 5;
        Exception e = assertThrows(IllegalArgumentException.class, () -> {DynamicChangeAlg.getMinChange(coinArray, changeAmount);});
        System.out.println(e.getMessage());
        assertEquals("The change drawer is empty!", e.getMessage());
    }

    //tests to see if the implementation of the DP change making alg is working correctly with a nonstandard coin
    @Test
    public void testMinChangeWithNonstandardCoin() {
        int coinArray[] = {1,5,12,25};
        int changeAmount = 16;
        ArrayList<Integer> change = new ArrayList<>();
        change.add(1);
        change.add(5);
        change.add(5);
        change.add(5);
        assertEquals(change, DynamicChangeAlg.getMinChange(coinArray, changeAmount));
    }

    //tests to see if the implementation of the DP change making alg is working correctly with standard coins
    @Test
    public void testMinChangeWithStandardCoins() {
        int coinArray[] = {1,5,10,25};
        int changeAmount = 42;
        ArrayList<Integer> change = new ArrayList<>();
        change.add(1);
        change.add(1);
        change.add(5);
        change.add(10);
        change.add(25);
        assertEquals(change, DynamicChangeAlg.getMinChange(coinArray, changeAmount));
    }

    //tests to see if there was no change to be made
    @Test
    public void testNoChangeToMake() {
        int coinArray[] = {1,5,10,25};
        int changeAmount = 0;
        Exception e = assertThrows(ArithmeticException.class, () -> {DynamicChangeAlg.getMinChange(coinArray, changeAmount);});
        System.out.println(e.getMessage());
        assertEquals("Exact amount has been given, no need to make change!", e.getMessage());
    }

    //test to see if change can be made if the coinArray is missing a required denomination
    @Test
    public void testChangeMakingWithoutNecessaryCoin() {
        int coinArray[] = {5,10,25};
        int changeAmount = 36;
        Exception e = assertThrows(ArithmeticException.class, () -> {DynamicChangeAlg.getMinChange(coinArray, changeAmount);});
        System.out.println(e.getMessage());
        assertEquals("It looks like we don't have the right coins to make change!", e.getMessage());
    }
}