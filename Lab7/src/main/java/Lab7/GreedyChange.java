/**
 * Author: Tristan Mullin
 * Date: 4/13/18
 * Overview: Implementing the Greedy Change Making algorithm and using unit testing to familiarize with TDD.
 * Note: Using JUnit5 for the testing environment.
 */
package Lab7;

import java.util.ArrayList;

public class GreedyChange {

    public static ArrayList<Integer> getMinChange(int coinArray[], int changeAmount) {
        ArrayList<Integer> change = new ArrayList<Integer>();
        if(coinArray.length == 0) {
            throw new IllegalArgumentException("The change drawer is empty!");
        }
        if(changeAmount == 0) {
            throw new ArithmeticException("Exact amount has been given, no need to make change!");
        }
        for(int i = coinArray.length - 1; i >= 0; i--) {
            while(changeAmount - coinArray[i] >= 0) {
                changeAmount -= coinArray[i];
                change.add(coinArray[i]);
            }
        }
        if(changeAmount != 0) {
            throw new ArithmeticException("It looks like we don't have the right coins to make change!");
        }
        return change;
    }
}
