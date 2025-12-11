import java.io.*;

public class Main {
    public static void main(String[] args) {
        Stats stats = new Stats(readFile("./p&p.txt") + readFile("./s&s.txt") + readFile("persuasion.txt")
                + readFile("emma.txt") + readFile("na.txt"));
        stats.predict(args[0]);
    }

    private static String readFile(String filePath) {
        String output = "";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            while (line != null) {
                output += " " + line;
                line = br.readLine();
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return output;
    }
}
