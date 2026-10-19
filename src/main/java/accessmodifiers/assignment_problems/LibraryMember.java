package main.java.accessmodifiers.assignment_problems;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Week 5 - Problem 4: JavaBean Library Member
 * Follows JavaBean standards with write-once property and irreversible one-way transformation.
 */
public class LibraryMember {
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    /**
     * Public no-argument constructor as required by JavaBean conventions.
     */
    public LibraryMember() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    /**
     * Write-once setter for membershipId. Later attempts to overwrite are ignored.
     */
    public void setMembershipId(String membershipId) {
        if (this.membershipId == null) {
            this.membershipId = membershipId;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premiumMember) {
        this.premiumMember = premiumMember;
    }

    /**
     * Stores a deterministic one-way transformed hash of the security answer.
     * Original answer cannot be recovered.
     */
    public void setSecurityAnswer(String answer) {
        if (answer == null) {
            this.securityAnswerHash = null;
            return;
        }
        this.securityAnswerHash = hashString(answer.trim().toLowerCase());
    }

    /**
     * Verifies whether the provided candidate matches the stored one-way hash.
     */
    public boolean verifySecurityAnswer(String candidate) {
        if (this.securityAnswerHash == null || candidate == null) {
            return false;
        }
        return this.securityAnswerHash.equals(hashString(candidate.trim().toLowerCase()));
    }

    private static String hashString(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] bytes = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public static void main(String[] args) {
        LibraryMember member = new LibraryMember();
        member.setName("Asha Rao");
        member.setMembershipId("MEM-1001");
        // Attempt overwrite
        member.setMembershipId("MEM-9999");
        System.out.println("Membership ID (write-once): " + member.getMembershipId()); // Should still be MEM-1001

        member.setPremiumMember(true);
        System.out.println("Premium: " + member.isPremiumMember());

        member.setSecurityAnswer("Fluffy");
        System.out.println("Verify Fluffy: " + member.verifySecurityAnswer("Fluffy"));
        System.out.println("Verify Wrong: " + member.verifySecurityAnswer("Spot"));
    }
}
