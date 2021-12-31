package projet.server.gestion_boulangerie.utils;

import javax.crypto.SecretKey;
import projet.server.gestion_boulangerie.model.Utilisateur;

public class UserChiffrement {
	// Prendre la cle
	private static SecretKey cle = ChiffrementPassword.key_generator();
	static String password = null;

	public static Utilisateur UserPassChiffrement(Utilisateur user) throws Exception {

		password = user.getUser_password();

		//Chiffrement password
		byte[] enc= ChiffrementPassword.encrypter(password, cle);

		System.out.println("Password encrypte: " + enc);
		String s = new String(enc);
 
		user.setUser_password(s);
		
		return user;

	}

	public static Utilisateur UserPassDechiffrement (Utilisateur user) throws Exception{
		
		password = user.getUser_password();
		
		byte[] enc = password.getBytes();
		
		// Dechiffrement password
		String dec = ChiffrementPassword.decrypter(enc, cle);
		
		user.setUser_password(dec);
		
		return user;
		
	}

	
}
