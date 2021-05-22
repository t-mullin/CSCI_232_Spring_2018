/**
 * Authors: Tristan Mullin
 * Date: 3/23/2018
 * Overview: This program reads in a csv file and inserts the contents of the csv into a hash table.
 * The hash table will resize when it has reached ~80% capacity and uses linear probing to resolve conflicts
 */
package Lab6;

public class HashTable {
    //variables
    private Data[] hashTable;
    private int insertionCounter = 0;

    //constructor for setting up the HashTable with a size of 5
    public HashTable() {
        hashTable = new Data[5];
    }

    //method for inserting into the HashTable
    public void insert(Data data) {
        //checks if the HashTable needs to be resized
        if((int)((((double) insertionCounter) / hashTable.length) * 100) >= 80) {
            doubleSize();
        }
        //checks for collision, if true then need to linear probe for new location to fill
        if(hashTable[hash(data.getKey())] != null) {
            hashTable[collisionResolution(hash(data.getKey()))] = data;
        } else {
            hashTable[hash(data.getKey())] = data;
        }
        insertionCounter++;
    }

    //method for calculating the hash during insertion into the HashTable
    private int hash(int key) {
        return key % hashTable.length;
    }

    //method for dealing with collisions
    private int collisionResolution(int hashKey) {
        int newKey = hashKey;
        while(hashTable[newKey] != null) {
            if(newKey == hashTable.length - 1) {
                newKey = 0;
            } else {
                newKey++;
            }
        }
        return newKey;
    }

    //method for doubling the size of the HashTable when it is about 80% full
    private void doubleSize() {
        Data[] elongatedTable = new Data[2*hashTable.length];
        Data[] tempTable = hashTable;
        insertionCounter = 0;
        hashTable = elongatedTable;

        //re-inserts the contents with the new hash
        for(int i = 0; i < tempTable.length; i++) {
            if(tempTable[i] != null) {
                insert(tempTable[i]);
            }
        }
    }

    //prints the HashTable
    public void printHashTable() {
        for(int i = 0; i < hashTable.length; i++) {
            if(hashTable[i] == null) {
                System.out.println("[Empty]");
            } else {
                hashTable[i].printData();
            }
        }
    }
}
