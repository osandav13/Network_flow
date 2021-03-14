import java.io.*;

public class parser {

    public parser(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        String line;

        while((line = br.readLine())!= null){
            System.out.println(line);
        }

    }


    public static void main(String[] args) throws IOException {
        parser parser = new parser("src/resources/bridge_9.txt");
    }
}
