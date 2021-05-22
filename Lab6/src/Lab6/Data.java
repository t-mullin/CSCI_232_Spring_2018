/**
 * Authors: Tristan Mullin
 * Date: 3/23/2018
 * Overview: This program reads in a csv file and inserts the contents of the csv into a hash table.
 * The hash table will resize when it has reached ~80% capacity and uses linear probing to resolve conflicts
 */
package Lab6;

public class Data {
    //variables
    private int key;
    private String value;

    //constructor for setting the key and value of the Data object
    public Data (int key, String value) {
        this.key = key;
        this.value = value;
    }

    //returns the key
    public int getKey() {
        return key;
    }

    //prints the key and value contained in the Data object
    public void printData() {
        System.out.println("[" + key + " , " + value + "]" );
    }
}
