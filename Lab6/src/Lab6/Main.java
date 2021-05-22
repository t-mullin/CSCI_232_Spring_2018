/**
 * Authors: Tristan Mullin
 * Date: 3/23/2018
 * Overview: This program reads in a csv file and inserts the contents of the csv into a hash table.
 * The hash table will resize when it has reached ~80% capacity and uses linear probing to resolve conflicts
 */
package Lab6;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path inputFile = Paths.get("input/StarWarsPlanets.csv");
        HashTable hashTable = new HashTable();
        try(BufferedReader inputBuffer = Files.newBufferedReader(inputFile)) {
            String inputLine = null;
            while((inputLine = inputBuffer.readLine()) != null) {
                String[] inputArray = inputLine.split(",");
                hashTable.insert(new Data(Integer.parseInt(inputArray[0]), inputArray[1]));
            }
            hashTable.printHashTable();
            System.out.println();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
