public class Stats {
    public static void run(String text) {
        text = text.toLowerCase()
                .replaceAll("--", " ") // Special formatting of Pride & Prejudice
                .replaceAll("(?![a-z]| ).", "")
                .replaceAll("(?<= ) {1,}", "")
                .trim(); // Finally!
                         // Finally? Two
                         // separate
                         // replacements
                         // so that " . "
                         // doesn't get
                         // converted to "
                         // "
        Tree analysis = new Tree();
        String[] words = text.split(" ");
        for (String word : words) {
            analysis.increment(word);
        }
        analysis.reverseDisplay();
    }
}
