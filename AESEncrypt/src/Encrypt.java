import java.io.StringReader;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;

public class Encrypt{
    public static void main(String[] args) throws Exception{
        encrypt();
    }
    public static void encrypt() throws Exception {
		String pinBlock = generatePinBlock("3695");
		final byte[] aesIv = new byte[12];
		final byte[] aesKey = new byte[32];
		SecureRandom random = SecureRandom.getInstanceStrong();
		random.nextBytes(aesIv);
		random.nextBytes(aesKey);
			
		Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
		GCMParameterSpec parameterSpec = new GCMParameterSpec(128, aesIv);
		SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, parameterSpec);

		byte[] pinBlockBytes = pinBlock.getBytes();
		byte[] cipherText = cipher.doFinal(pinBlockBytes);
		byte[] encPinBlockWithoutIvAndAuthTag = Arrays.copyOfRange(cipherText, 0, cipherText.length - 16);
		byte[] authTag = cipher.getParameters().getParameterSpec(GCMParameterSpec.class).getIV();
		String encodedMessage = Base64.getEncoder().encodeToString(encPinBlockWithoutIvAndAuthTag);
		byte[] details = concatenateBytes(new byte[] { (byte) aesKey.length }, aesKey, new byte[] { (byte) aesIv.length }, aesIv, new byte[] { (byte) authTag.length }, authTag);
			
		String encodedDetails = encryptRSAOAEP(details, "LS0tLS1CRUdJTiBQVUJMSUMgS0VZLS0tLS0KTUlJQ0lqQU5CZ2txaGtpRzl3MEJBUUVGQUFPQ0FnOEFNSUlDQ2dLQ0FnRUFvZ0dSQ3k4N1FjQ3d6MFI0NE9LTwpucEhhZ0p1bW83Wll1V21JdXY2b1ZseUxqYkcwWlgyUFBSUy9LVUdhbXdiWGQwMGtYeHBXbFA5cXJ2N2hYMlNSCkJ2TFJVWFR0TCtvWS9QajN0c2Z6d0liT3VDei9qUnQ5Uk9WdzNBTkZTNjF6blVFTkVsSlNXN2dudnJuL29USWwKRWlid2VVTE5aTlJObFRwQTI1QVhKanhXMzM3ZUx0Y2F5cXJiQlNJNVFmRitCTGJJbE1Rd2tqSkNhUFEzV0pUKwpxcnlCREFCME5ocm02VlBmK2toN2FyR2JqL3ZLS0NRWVBkQWhRKzI3OGp0ZGJZdFBzYWtjN0RqVXpTenl2Wm9HCmhiSEt4V0ZTODFnL2ZlQUZNbFFDVlErZk15ZVN5dFZKOGlmZjFZR2RydEJCdG16U0NRN2V0K1IwaHpUbzJXblcKZzREZG1oWmlZT0MvUU55dk5uY1VSeDRZMU84VW1nSmorNE56c3VyQ2dQMmRSUjNpVXRIYlZaZjRzTEpZWk5CWApORGxjUDNzYWlwTXJ6Z0RpM0VCbWZzdEJOODdvWVdsRzRQNmlPVGt3dzdDVyt0TUdRNmJPcVBBNFF0cWMrYUZMCktZR3FWREhWRnBhZWdwYXl0U2g3T25nd0cwckJod0M4ODNpeVFaZDNKRW1lSWt2V05wYTVRSExqRmJKTEg4M2YKdk9TR2E0aGR6ZmZqYUhUM2VvR2VCRU5NaTZIbFo4RVZHWUh0VUpXZGMzZ2h5OHdPbmZkTlN2NzV3SUZYbHNragpIdHM3Z3NkM3hDN1B3ZnBqOEV5Lyt2aTVLNHM5M29IaE5TYlRhNlUzNUVsZkFXL20zK1YxWjZqVUpwRWpmZkZYCnI0YlFiR3pQNVhWYkNKL21ndmVNM3lNQ0F3RUFBUT09Ci0tLS0tRU5EIFBVQkxJQyBLRVktLS0tLQo=");
        System.out.println("Encripted pin: " +encodedMessage);
        System.out.println("Encripted details :" + encodedDetails);
	}

    private static byte[] concatenateBytes(byte[]... byteArrays) {
    int totalLength = 0;
    for (byte[] byteArray : byteArrays) {
        totalLength += byteArray.length;
    }
    
    byte[] result = new byte[totalLength];
    int destPos = 0;
    for (byte[] byteArray : byteArrays) {
        System.arraycopy(byteArray, 0, result, destPos, byteArray.length);
        destPos += byteArray.length;
    }
    return result;
}

    private static String generatePinBlock(String pin) {
        List<String> encodedPinArray = new ArrayList<>();
        boolean hasDuplicates = true;
        Random random = new Random();
        while (hasDuplicates) {
            encodedPinArray = genRandomHex(random, 50);
            hasDuplicates = hasRandomPositionsDuplicates(encodedPinArray, pin.length());
        }
        encodedPinArray.set(0, String.valueOf(pin.length()));
        for (int i = 0; i < pin.length(); i++) {
            int position = Integer.parseInt(encodedPinArray.get(i + 1), 16);
            encodedPinArray.set(1 + pin.length() + position, String.valueOf(pin.charAt(i)));
        }
        return String.join("", encodedPinArray).toUpperCase();
    }

    private static List<String> genRandomHex(Random random, int length) {
        List<String> hexList = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            hexList.add(Integer.toHexString(random.nextInt(16)));
        }
        return hexList;
    }


    private static boolean hasRandomPositionsDuplicates(List<String> encodedPinArray, int pinLength) {
        List<String> positions = new ArrayList<>();
        for (int i = 1; i <= pinLength; i++) {
            if (positions.contains(encodedPinArray.get(i))) {
                return true;
            }
            positions.add(encodedPinArray.get(i));
        }
        return false;
    }

    public static String encryptRSAOAEP(byte[] details, String publicKey) throws Exception{
			// Step 1: Decode the base64 encoded public key
			byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey);
			// Step 2: Load the PEM format public key
			PemReader pemReader = new PemReader(new StringReader(new String(publicKeyBytes)));
			PemObject pemObject = pemReader.readPemObject();
			byte[] encodedPublicKey = pemObject.getContent();

			// Step 3: Create the RSA public key object
			RSAKeyParameters rsaPublicKey = (RSAKeyParameters) PublicKeyFactory.createKey(encodedPublicKey);

			// Step 4 : Encrypt the details using RSA-OAEP with SHA-256
			RSAEngine rsaEngine = new RSAEngine();
			OAEPEncoding rsaOaepEncoding = new OAEPEncoding(rsaEngine, new SHA256Digest());
			rsaOaepEncoding.init(true, rsaPublicKey);
			byte[] encryptedMessage = rsaOaepEncoding.processBlock(details, 0, details.length);

			// Step 5: Encode the encrypted details as base64
			return Base64.getEncoder().encodeToString(encryptedMessage);
	}
}