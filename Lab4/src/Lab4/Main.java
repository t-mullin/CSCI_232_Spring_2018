/**
 * Authors: Tristan Mullin
 * Date: 2/16/2018
 * Overview: This program read in a file called "input.txt" then builds a binary tree. Actions are performed on said tree
 * Note: This uses code from (Robert Lafore. 2002.Data Structures and Algorithms in Java(2 ed.).Sams, Indianapolis, IN, USA)
 */

package Lab4;

import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {
        long time = 0;
        long start;
        long end;

        for(int i = 0; i <= 30; i++) {
            int arraySize = (int) Math.pow(2, i);
            Integer[] randArray = new Integer[arraySize];
            Integer[] orderedArray = new Integer[arraySize];
            TreeMap<Integer, Double> treeMap = new TreeMap();
            Tree tree = new Tree();

            int j = 0;
            //populates both arrays
            while (j < arraySize) {
                int rand = (int) (Math.random() * arraySize);
                randArray[j] = rand;
                orderedArray[j] = j;
                j++;
            }

            start = System.currentTimeMillis();
            for (int k = 0; k < orderedArray.length; k++) {
                treeMap.put(orderedArray[k], orderedArray[k]+0.9);
                //treeMap.put(randArray[k], randArray[k]+0.9);
                //tree.insert(orderedArray[k], (orderedArray[k] + 0.9));
                //tree.insert(randArray[k], (randArray[k] + 0.9));
            }
            end = System.currentTimeMillis();
            time = (end - start);
            System.out.println(time);
        }
	// write your code here
    }
}
