import java.io.*;

public class Main {
    public static void main(String[] args) {
        Stats.run(readFile("./p&p.txt"));
        // String text = readFile("./test.txt");
        // for (int i = 0; i < text.length(); i++) {
        // char c = text.charAt(i);
        // System.out.println(
        // "Character at index " + i + ": '" + c + "' (Unicode: U+" +
        // String.format("%04X", (int) c) + ")");
        // }
        // for (String s : "a s d s
        // dd".toLowerCase().replaceAll("(\r|\n|\\?|\'|\"|!|,|-|\\.)", "").replaceAll("
        // ", " ")
        // .replaceAll(" ", " ").replaceAll(" ", " ").split(" ")) {
        // System.out.println(s);
        // }
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
