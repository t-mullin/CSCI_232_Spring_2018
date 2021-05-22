/**
 * Authors: Tristan Mullin
 * Date: 1/19/2018
 * Overview: This program read in a file called "input.txt" then outputs to the console and a file
 * with or without additional formatting
 */

package Lab1;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //Setting up path variables for input and output files
        Path inputFile = Paths.get("././input/input.txt");
        Path outputFileA = Paths.get("././output/outputA.txt");
        Path outputFileB = Paths.get("././output/outputB.txt");

        //Calling the methods to read the input file
        readFileA(inputFile, outputFileA);
        System.out.println();
        readFileB(inputFile, outputFileB);
    }

    //reads in the input file then outputs to the console the text with the original formatting
    private static void readFileA(Path inputFile, Path outputFileA) {
        //tests to see if the file that will be read even exists
        try (BufferedReader inputBuffer = Files.newBufferedReader(inputFile)) {
            String inputLine = null;
            ArrayList<String> lineList = new ArrayList();
            while((inputLine = inputBuffer.readLine()) != null) {
                lineList.add(inputLine);
                System.out.println(inputLine);
            }
            lineList.trimToSize();
            writeFile(lineList, outputFileA);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //reads the input file then output to the console the text with each word on its own line
    private static void readFileB(Path inputFile, Path outputFileB) {
        //tests to see if the file that will be read even exists
        try (BufferedReader inputBuffer = Files.newBufferedReader(inputFile)) {
            String inputLine = null;
            ArrayList<String> lineList = new ArrayList();
            while((inputLine = inputBuffer.readLine()) != null) {
                String[] split = inputLine.split(" ");
                int i = 0;
                while(i < split.length) {
                    System.out.println(split[i]);
                    lineList.add(split[i]);
                    i++;
                }
            }
            lineList.trimToSize();
            writeFile(lineList, outputFileB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //writes a copy of the console output to a specified file
    private static void writeFile(ArrayList inputList, Path outputFile) {
        //checks to see if the output file exist otherwise it throws an exception
        try (BufferedWriter outputLine = Files.newBufferedWriter(outputFile)) {
            //for each to print the contents of the arrayList
            for (Object input: inputList) {
                outputLine.write(input.toString(), 0, input.toString().length());
                outputLine.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
