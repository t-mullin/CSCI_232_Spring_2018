/**
 * Author: Tristan Mullin
 * Date: 4/24/18
 * Overview: This program implements the change making problem using dynamic programing
 * Note: JUnit 5 is used to implement testing
 */
package ProgrammingAssignment4;

import java.util.ArrayList;

public class DynamicChangeAlg {

    public static ArrayList<Integer> getMinChange(int coinArray[], int changeAmount) {
        //throws an exception when the coinArray is empty or null
        if((coinArray == null) || (coinArray.length == 0)) {
            throw new IllegalArgumentException("The change drawer is empty!");
        }
        //throws an exception when there is no change to make
        if(changeAmount == 0) {
            throw new ArithmeticException("Exact amount has been given, no need to make change!");
        }

        //variables
        ArrayList<Integer> change = new ArrayList<>();
        int coinCount[] = new int[changeAmount + 1];
        int changeArray[] = new int[changeAmount + 1];
        coinCount[0] = 0;
        changeArray[0] = 0;

        //builds the tables for number of coins and the denomination of the coin
        for(int i = 1; i < changeAmount + 1; i++) {
            coinCount[i] = coinCount[i - 1] + 1;
            changeArray[i] = coinArray[0];
            for(int j = 0; j < coinArray.length; j++) {
                if(i - coinArray[j] >= 0) {
                    if((coinCount[i - coinArray[j]] + 1) < coinCount[i]){
                        coinCount[i] = coinCount[(i - coinArray[j])] + 1;
                        changeArray[i] = coinArray[j];
                    }
                }
            }
        }

        //backtracks through the denomination array to build the list of change to give
        //also throws an exception if a required denomination is not present
        int i = changeAmount;
        while(i != 0) {
            if(i < 0) {
                throw new ArithmeticException("It looks like we don't have the right coins to make change!");
            }
            change.add(changeArray[i]);
            i -= changeArray[i];
        }

        return change;
    }

}
