package main.java.strings.assignment_problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Week 2 - Problem 5: Stop-Word-Filtered Word Frequency
 * Counts and prints frequencies of non-stop words in descending frequency order.
 */
public class WordFrequencyCounter {

    private static final Set<String> STOP_WORDS = new HashSet<>(
            Arrays.asList("the", "was", "and", "a", "is", "of", "in")
    );

    /**
     * Cleans the feedback text, filters out stop words, counts occurrences,
     * and prints words in descending frequency order.
     *
     * @param feedback feedback string to process
     */
    public static void printFilteredWordFrequency(String feedback) {
        if (feedback == null || feedback.trim().isEmpty()) {
            return;
        }

        // Convert text to lowercase
        String cleaned = feedback.toLowerCase();

        // Remove punctuation like periods, commas, exclamation marks, question marks using replace()
        cleaned = cleaned.replace(".", "")
                         .replace(",", "")
                         .replace("!", "")
                         .replace("?", "")
                         .replace(";", "")
                         .replace(":", "");

        // Split on whitespace
        String[] tokens = cleaned.trim().split("\\s+");

        Map<String, Integer> freqMap = new HashMap<>();
        for (String token : tokens) {
            if (token.isEmpty() || STOP_WORDS.contains(token)) {
                continue;
            }
            freqMap.put(token, freqMap.getOrDefault(token, 0) + 1);
        }

        // Sort entries by descending frequency
        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(freqMap.entrySet());
        entryList.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        String input = "The mentor was great, the session was great and clear.";
        System.out.println("Input: " + input);
        System.out.println("Output:");
        printFilteredWordFrequency(input);
    }
}
