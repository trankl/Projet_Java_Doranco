package gestion_boulangerie;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import projet.server.gestion_boulangerie.model.Utilisateur;
import projet.server.gestion_boulangerie.service.UtilisateurService;

class Test_case_utilisateur {

	private UtilisateurService userService= new UtilisateurService();
	
	/*
	@Test
	void WhenGetAllUser() throws Exception {
		
		List<Utilisateur> listUsers= userService.getAllUtilisateur();
		
		assertEquals(2,listUsers.size());
		System.out.println("TEST GET ALL USER --> OK");
	}
	*/
	
	@Test
	void WhenFindUserByName() throws Exception {
		
		String name = "Jean";
		
		Utilisateur user = userService.findByName(name);
		
		assertEquals("12345",user.getUser_password());
		System.out.println("TEST FIND USER BY NAME --> OK");
	}
}
