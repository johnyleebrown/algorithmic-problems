package hashtable._ds;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * MerkleHash
 *
 * MerkleHash is used to hash a tree with sha256, used in bitcoin to verify
 * transactions.
 */
public class MerkleHash {
    /**
     * Input string s - representation of the Tree node's data.
     */
    private String hash(String s) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        return toString(hash);
    }

    public String toString(byte[] s) {
        return Base64.getEncoder().encodeToString(s);
    }
}