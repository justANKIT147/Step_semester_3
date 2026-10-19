package main.java.accessmodifiers.assignment_problems;

/**
 * Week 5 - Problem 2: Protected Access Across Packages
 * Demonstrates Java Language Specification rules for protected member access
 * across packages (JLS 6.6.2): protected access is permitted only through a reference
 * of the subclass type or its subtypes, not an arbitrary parent-type reference.
 */
public class ProtectedAccessChecker {

    public static final String SUBCLASS_OWN_TYPE = "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE";
    public static final String SUBCLASS_PARENT_TYPE = "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE";

    /**
     * Checks if access is permitted under the extended cross-package inheritance contexts.
     *
     * @param modifier access modifier
     * @param context  subclass cross-package context
     * @return true if access is allowed by JLS rules, false if denied
     */
    public static boolean isAccessAllowed(String modifier, String context) {
        if (modifier == null || context == null) return false;
        String mod = modifier.toLowerCase().trim();
        String ctx = context.trim();

        if ("public".equals(mod)) {
            return true;
        }
        if ("private".equals(mod) || "default".equals(mod) || "package-private".equals(mod)) {
            return false;
        }
        if ("protected".equals(mod)) {
            if (SUBCLASS_OWN_TYPE.equalsIgnoreCase(ctx)) {
                return true;
            }
            if (SUBCLASS_PARENT_TYPE.equalsIgnoreCase(ctx)) {
                return false;
            }
        }
        return false;
    }

    /**
     * Scans attempts sequentially and returns the first denied attempt, or "None Denied".
     * Format: "<modifier> via <context> (attempt #<index>)"
     *
     * @param attempts array of [modifier, context]
     * @return description of the first denied attempt or "None Denied"
     */
    public static String firstDeniedAttempt(String[][] attempts) {
        if (attempts == null || attempts.length == 0) {
            return "None Denied";
        }

        for (int i = 0; i < attempts.length; i++) {
            String[] attempt = attempts[i];
            if (attempt == null || attempt.length < 2) continue;

            String modifier = attempt[0];
            String context = attempt[1];
            if (!isAccessAllowed(modifier, context)) {
                return modifier + " via " + context + " (attempt #" + (i + 1) + ")";
            }
        }

        return "None Denied";
    }

    public static void main(String[] args) {
        String[][] attempts = {
            {"protected", SUBCLASS_OWN_TYPE},
            {"protected", SUBCLASS_PARENT_TYPE},
            {"public", SUBCLASS_PARENT_TYPE}
        };

        System.out.println(firstDeniedAttempt(attempts));

        String[][] allowedOnly = {
            {"protected", SUBCLASS_OWN_TYPE},
            {"public", SUBCLASS_PARENT_TYPE}
        };
        System.out.println(firstDeniedAttempt(allowedOnly));
    }
}
