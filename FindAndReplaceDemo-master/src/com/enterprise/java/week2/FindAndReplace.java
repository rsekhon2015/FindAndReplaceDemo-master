package com.enterprise.java.week2;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Sample Find and Replace program to practice debugging, unit testing, and refactoring.
 *
 * The program will read in a file of words and their replacement values. The format of the file is:
 * Placeholder:ReplacementValue,Placeholder:ReplacementValue
 *
 * The program will read in a file that contains some placeholder "words" to be replaced,
 * will replace the words with the correct values and then write out a new file.
 *
 *@author paulawaite
 */
public class FindAndReplace {

    String inputFile;
    String outputFile;
    String findReplaceFile;
    Map<String, String> findReplaceValues = new HashMap<>();
    private final Logger logger = Logger.getLogger(FindAndReplace.class);

    public FindAndReplace(String inputFile, String outputFile, String findReplaceFile) {
        logger.info("In the constructor");
        this.inputFile = inputFile;
        this.outputFile = outputFile;
        this.findReplaceFile = findReplaceFile;
    }

    public void run() {
        createMapOfFindReplaceValues();
        readInputFile();
    }


    public void createMapOfFindReplaceValues() {
        String line = null;
        try (BufferedReader in = new BufferedReader(new FileReader(findReplaceFile))) {
            while (in.ready()) {
                line = in.readLine();
                String str[] = line.split(",");
                for (int i = 0; i < str.length; i++) {
                    String arr[] = str[i].split(":");
                    findReplaceValues.put(arr[0], arr[1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readInputFile() {
        String line = null;
        PrintWriter out = createOutputFile();

        try (BufferedReader in = new BufferedReader(new FileReader(inputFile))) {
            while (in.ready()) {
                line = in.readLine();
                for (Map.Entry<String, String> entry : findReplaceValues.entrySet()) {
                    line = line.replace(entry.getKey(), entry.getValue());
                }
                out.println(line);
            }
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PrintWriter createOutputFile() {
        try {
           PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)));
                return out;
        } catch (IOException e) {
            logger.error("There was a problem", e);
            //e.printStackTrace();
        }
        return null;
    }

}
