package main.java.accessmodifiers.assignment_problems;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Week 5 - Problem 1: Access Classifier
 * Classifies Java access attempts based on standard access modifier visibility rules.
 */
public class AccessClassifier {

    /**
     * Determines whether an access attempt is allowed given the modifier and access context.
     *
     * @param modifier "private", "default", "protected", or "public"
     * @param context  "SAME_CLASS", "SAME_PACKAGE", or "DIFFERENT_PACKAGE"
     * @return true if allowed by Java visibility rules, false otherwise
     */
    public static boolean classifyAccess(String modifier, String context) {
        if (modifier == null || context == null) {
            return false;
        }

        String mod = modifier.toLowerCase().trim();
        String ctx = context.toUpperCase().trim();

        switch (mod) {
            case "private":
                return "SAME_CLASS".equals(ctx);
            case "default":
            case "package-private":
                return "SAME_CLASS".equals(ctx) || "SAME_PACKAGE".equals(ctx);
            case "protected":
                return "SAME_CLASS".equals(ctx) || "SAME_PACKAGE".equals(ctx);
            case "public":
                return true;
            default:
                return false;
        }
    }

    /**
     * Summarizes attempts by modifier in format:
     * "private: X allowed / Y denied | default: X allowed / Y denied | protected: X allowed / Y denied | public: X allowed / Y denied"
     *
     * @param attempts 2D array where each row is [modifier, context]
     * @return formatted summary string
     */
    public static String summarizeByModifier(String[][] attempts) {
        Map<String, int[]> stats = new LinkedHashMap<>();
        stats.put("private", new int[]{0, 0});
        stats.put("default", new int[]{0, 0});
        stats.put("protected", new int[]{0, 0});
        stats.put("public", new int[]{0, 0});

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt == null || attempt.length < 2) continue;
                String mod = attempt[0].toLowerCase().trim();
                String ctx = attempt[1];
                if (stats.containsKey(mod)) {
                    boolean allowed = classifyAccess(mod, ctx);
                    if (allowed) {
                        stats.get(mod)[0]++;
                    } else {
                        stats.get(mod)[1]++;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, int[]> entry : stats.entrySet()) {
            if (!first) {
                sb.append(" | ");
            }
            sb.append(entry.getKey()).append(": ")
              .append(entry.getValue()[0]).append(" allowed / ")
              .append(entry.getValue()[1]).append(" denied");
            first = false;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "DIFFERENT_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"protected", "SAME_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        String summary = summarizeByModifier(attempts);
        System.out.println(summary);
    }
}
