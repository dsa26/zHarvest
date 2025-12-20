public class Stats {
    private WTree analysis;
    private PTree prediction;

    public Stats(String text) {
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
        WTree analysis = new WTree();
        PTree prediction = new PTree();
        String[] words = text.split(" ");
        for (int i = 0; i < words.length; i++) {
            analysis.increment(words[i]);
            if (i != words.length - 1) {
                prediction.increment(words[i], words[i + 1]);
            }
        }
        this.analysis = analysis;
        this.prediction = prediction;
    }

    public void predict(String start) {
        prediction.predict(start);
        // prediction.get(start).value.display();
    }

    public void verifyLL() {
        WDoubleLL linkedList = new WDoubleLL(analysis);
        linkedList.display();
        // Works with a small test file, but StackOverflow with Jane Austen texts --
        // Assuming that's a size issue and considering the problem solved!
    }
}
