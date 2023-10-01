package kursinis_hibernate.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/**
 * This class is used to hash passwords and compare them;
 * Using MD5 algorithm because too lazy to implement BCrypt.
 */
public class PasswordHashingUtil {

	public static String hashPassword(String password)
			throws NoSuchAlgorithmException, NoSuchProviderException {
		String securePassword = getSecurePassword( password );
		String regeneratedPassword = getSecurePassword( password );
		if ( securePassword.equals( regeneratedPassword ) ) {
			return securePassword;
		}
		else {
			return null;
		}
	}

	public static boolean comparePasswords(String password, String hashedPassword) {
		String newHashedPassword = getSecurePassword( password );
		return newHashedPassword.equals( hashedPassword );
	}

	private static String getSecurePassword(
			String passwordToHash) {
		String generatedPassword = null;
		try {
			// Static getInstance method is called with hashing MD5
			MessageDigest md = MessageDigest.getInstance( "MD5" );
			// digest() method is called to calculate message digest
			// of an input digest() return array of byte
			byte[] messageDigest = md.digest( passwordToHash.getBytes() );
			// Convert byte array into signum representation
			BigInteger no = new BigInteger( 1, messageDigest );
			// Convert message digest into hex value
			String hashtext = no.toString( 16 );
			while ( hashtext.length() < 32 ) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		}
		// For specifying wrong message digest algorithms
		catch (NoSuchAlgorithmException e) {
			throw new RuntimeException( e );
		}
	}
}
