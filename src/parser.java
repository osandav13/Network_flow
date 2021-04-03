import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class parser {


    public ArrayList<List<String>> readFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        ArrayList<List<String>> fileData = new ArrayList<>();

        String line;

        while((line = br.readLine())!= null) {
            String[] numbers = line.split(" ");
            fileData.add(Arrays.asList(numbers));
        }
       return fileData;
    }

}
