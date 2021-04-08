package me.osanda;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *  Name : Osanda Ginige
 *  UoW id : w1761754
 *  IIT id : 2018181
 *  Last updated:  8/04/2021
 */
public class Parser {

    /**
     *  This method is used to read the text files in the resource package
     *
     * @param fileName Name of the text file
     * @return Each file line as string
     * @throws IOException
     */
    public ArrayList<List<String>> readFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        // Buffer reader is used to read the files faster
        BufferedReader br = new BufferedReader(fr);

        ArrayList<List<String>> fileData = new ArrayList<>();

        String line;
        // Each file line is stored in a list and added to a arrayList
        // Storing data this way is makes it easier to access it later
        while((line = br.readLine())!= null) {
            String[] numbers = line.split(" ");
            fileData.add(Arrays.asList(numbers));
        }
       return fileData;
    }

}
