/**
 * Author: Tristan Mullin
 * Date: 4/13/18
 * Overview: Implementing the Greedy Change Making algorithm and using unit testing to familiarize with TDD.
 * Note: Using JUnit5 for the testing environment.
 */
package Lab7;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class GreedyChangeTest {

    @Test
    public void testGetMinChangeWithoutChangeAmount() {
        int coinArray[] = {1,5,10,25};
        int changeAmount = 0;
        Exception e = assertThrows(ArithmeticException.class, () -> {GreedyChange.getMinChange(coinArray, changeAmount);});
        System.out.println(e.getMessage());
        assertEquals("Exact amount has been given, no need to make change!", e.getMessage());
    }

    @Test
    public void testGetMinChangeWithoutNecessaryCoin() {
        int coinArray[] = {5,10,25};
        int changeAmount = 63;
        Exception e = assertThrows(ArithmeticException.class, () -> {GreedyChange.getMinChange(coinArray, changeAmount);});
        System.out.println(e.getMessage());
        assertEquals("It looks like we don't have the right coins to make change!", e.getMessage());
    }

    @Test
    public void testGetMinChangeWithoutNickels() {
        int coinArray[] = {1,10,25};
        int changeAmount = 65;
        ArrayList<Integer> change = new ArrayList<>();
        change.add(25);
        change.add(25);
        change.add(10);
        change.add(1);
        change.add(1);
        change.add(1);
        change.add(1);
        change.add(1);
        assertEquals(change, GreedyChange.getMinChange(coinArray, changeAmount));
    }

    @Test
    public void testEmptyArray() {
        int coinArray[] = {};
        int changeAmount = 5;
        Exception e = assertThrows(IllegalArgumentException.class, () -> {GreedyChange.getMinChange(coinArray, changeAmount);});
        System.out.println(e.getMessage());
        assertEquals("The change drawer is empty!", e.getMessage());
    }
}