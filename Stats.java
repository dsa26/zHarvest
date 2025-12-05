public class Stats {
    public static void run(String text) {
        text = text.toLowerCase().replaceAll("(?![a-z]| ).|( {2,})", ""); // Finally!
        Tree analysis = new Tree();
        String[] words = text.split(" ");
        for (String word : words) {
            analysis.increment(word);
        }
        analysis.reverseDisplay();
    }
}
