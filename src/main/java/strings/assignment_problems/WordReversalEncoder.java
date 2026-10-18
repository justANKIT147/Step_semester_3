package main.java.strings.assignment_problems;

/**
 * Week 2 - Problem 2: Word Reversal Encoder
 * Reverses each word in a sentence independently while preserving word order.
 */
public class WordReversalEncoder {

    /**
     * Splits a sentence into words, reverses each word independently using StringBuilder,
     * and joins them back with single spaces.
     *
     * @param sentence input sentence
     * @return encoded sentence with each word reversed
     */
    public static String reverseEachWord(String sentence) {
        if (sentence == null || sentence.isEmpty()) {
            return sentence;
        }

        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            StringBuilder reversedWord = new StringBuilder(words[i]);
            reversedWord.reverse();
            result.append(reversedWord);

            if (i < words.length - 1) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Example from assignment
        String input = "hello club";
        String encoded = reverseEachWord(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + encoded);

        // Additional test case
        System.out.println("Test 2: " + reverseEachWord("Java programming is fun"));
    }
}
