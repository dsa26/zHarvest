public class Stats {
    public static void run(String text) {
        text = text.toLowerCase();
        Tree analysis = new Tree();
        String[] words = text.split(" ");
        for (String word : words) {
            analysis.increment(word);
        }
        analysis.display();
    }
}
