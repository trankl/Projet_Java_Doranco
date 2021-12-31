package projet.server.gestion_boulangerie.utils;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

public class ChiffrementPassword {

public static SecretKey key_generator() {
		
	
		// Celui ci va nous permettre de generer une clef de chiffrement
		KeyGenerator keyGen = null;
	    try {
	 
	      keyGen = KeyGenerator.getInstance("DESede");
	      
	      keyGen.init(168);
	      
	      SecretKey cle = keyGen.generateKey();
	      
	      System.out.println("cle : " + new String(cle.getEncoded()));

	      return cle;

	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		return null;
	  }
	
	// Creer la methode pour chiffer la chain de message avec la cle
	 public static byte[] encrypter(final String message, SecretKey cle)
		      			throws 	NoSuchAlgorithmException, NoSuchPaddingException,
		      					InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		 	
		 	Cipher cipher = Cipher.getInstance("DESede");
		 	
		 	//Faire Chiffer valeur par:
		 	// Initialiser la classe pour le mode de fonctionnement pricipale (Cipher.ENCRYPT_MODE)
		    cipher.init(Cipher.ENCRYPT_MODE, cle);
		    // Creer 1 object type byte[] qui contient la valeur du message de methode getBytes()
		    byte[] donnees = message.getBytes();
		    
		    //retour la valeur du objet byte en utliser la methode "doFinal"
		    return cipher.doFinal(donnees);
		  }

	// Creer la methode pour dechiffer la chain de message avec la cle
	 public static String decrypter(final byte[] donnees, SecretKey cle)
		     			throws 	NoSuchAlgorithmException, NoSuchPaddingException,
		     				   	InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
		   //// Instancier l'objet Ciper grace a la methode getInstance() qui permet de 
		    //Generer d'une cle triple DES (DESede)
		    // on lui permet en parametre l'algorithme DESede
		 	Cipher cipher = Cipher.getInstance("DESede");
		    
		 	// Faire Dechiffre valeur
		    cipher.init(Cipher.DECRYPT_MODE, cle);
		    
		    //retour la valeur du objet String en utliser la methode "doFinal"
		    return new String(cipher.doFinal(donnees));
		  }
	
}
