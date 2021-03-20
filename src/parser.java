import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class parser {


    public List<Integer> readFile(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        List<Integer> fileData = new ArrayList<>();

        String line;

        while((line = br.readLine())!= null){fileData.add(Integer.parseInt(line));}
        return fileData;
    }

}
