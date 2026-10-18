package main.java.strings.assignment_problems;

/**
 * Week 2 - Problem 4: Library ISBN Normalizer and Validator
 * Normalizes and validates 13-character ISBN strings without regular expressions.
 */
public class LibraryIsbnValidator {

    /**
     * Trims whitespace and converts the first three characters to uppercase
     * using substring() and string concatenation.
     *
     * @param raw the raw input code
     * @return normalized code string
     */
    public static String normalizeCode(String raw) {
        if (raw == null) {
            return null;
        }
        String trimmed = raw.trim();
        if (trimmed.length() < 3) {
            return trimmed.toUpperCase();
        }
        return trimmed.substring(0, 3).toUpperCase() + trimmed.substring(3);
    }

    /**
     * Validates the normalized 13-character code and returns either the formatted
     * output or a specific error message.
     *
     * @param code normalized code string
     * @return formatted representation or validation error message
     */
    public static String validateAndFormat(String code) {
        if (code == null || code.length() != 13) {
            return "Invalid: code must be exactly 13 characters";
        }

        // Verify first 3 characters are letters
        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(code.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        // Verify remaining 10 characters are digits
        for (int i = 3; i < 13; i++) {
            if (!Character.isDigit(code.charAt(i))) {
                return "Invalid: remaining 10 characters must be digits";
            }
        }

        String pubCode = code.substring(0, 3);
        String year = code.substring(3, 7);
        String catalog = code.substring(7, 13);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(pubCode).append("] YEAR: ").append(year).append(" | CATALOG: ").append(catalog);
        return sb.toString();
    }

    public static void main(String[] args) {
        // Examples from assignment
        String raw1 = " pen2026004251 ";
        String norm1 = normalizeCode(raw1);
        System.out.println("Input: \"" + raw1 + "\"");
        System.out.println("Result: " + validateAndFormat(norm1));

        String raw2 = "12N2026004251";
        String norm2 = normalizeCode(raw2);
        System.out.println("Input: \"" + raw2 + "\"");
        System.out.println("Result: " + validateAndFormat(norm2));

        // Edge case: incorrect length
        String raw3 = "PEN2026";
        System.out.println("Input: \"" + raw3 + "\"");
        System.out.println("Result: " + validateAndFormat(normalizeCode(raw3)));
    }
}
