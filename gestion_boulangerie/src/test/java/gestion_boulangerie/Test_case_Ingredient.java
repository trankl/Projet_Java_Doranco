package gestion_boulangerie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import projet.server.gestion_boulangerie.model.Ingredient;
import projet.server.gestion_boulangerie.model.MatierePremiere;
import projet.server.gestion_boulangerie.resource.IngredientResource;
import projet.server.gestion_boulangerie.service.IngredientService;
import projet.server.gestion_boulangerie.service.MPService;
import projet.server.gestion_boulangerie.service.ProduitService;

class Test_case_Ingredient {
	
	private IngredientService ingredientService = new IngredientService();
	private MPService mpService = new MPService();
	private ProduitService produitService = new ProduitService();
	private IngredientResource ingredientResource = new IngredientResource();
	/*
	@Test
	public void WhenCreateNewIngredient_thenUpdateQuantiteMP() throws Exception {
		
		Ingredient i = new Ingredient(5, 5, 400, "ml");
		
		MatierePremiere mp = mpService.findById(i.getMp_id());
		int mp_quantite= mp.getMp_quantite();
		
		Ingredient idb= ingredientResource.addIngredient(i);
		System.out.println(idb);
		
		MatierePremiere newMP = mpService.findById(idb.getMp_id());
		
		assertEquals(mp_quantite-400, newMP.getMp_quantite());
		
		System.out.println("Test CREATE INGREDIENT -> OK");
		
	}
	
	
	@Test
	public void WhenFindListsDesIngredients() throws Exception {
		
		List<Ingredient> listIngredient = new ArrayList<>();
		
		listIngredient = ingredientService.getAllIngredient();
		
		assertEquals(49, listIngredient.size());
		System.out.println("Test FIND ALL INGREDIENTS -> OK");
	}

	
	
	
	@Test
	public void WhenFindListIngredientD1Produit() throws Exception {
		
		List<Ingredient> listIngredient = new ArrayList<>();
		
		listIngredient = ingredientService.findByProduitId(1);
		
		assertEquals(8, listIngredient.size());
		System.out.println("Test FIND LIST INGREDIENT D'1 PRODUIT -> OK");
	}

	@Test
	public void WhenUpdateIngredientFromProduitID() throws Exception {
		
		Ingredient i = new Ingredient(1, 3, 400, "g");
		
		int id = 1;
		
		Ingredient idb= ingredientService.updateIngredient(i, id);
		System.out.println(idb);
		
		MatierePremiere mp = mpService.findById(idb.getMp_id());
		
		assertEquals("Citron", mp.getMp_nom());
		
		System.out.println("Test UPDATE INGREDIENT -> OK");
		
	}
	
	
	
	@Test
	public void WhenDelete1Ingredient() throws Exception {
		
		int i = produitService.getAllProduit().size();
		int j = ingredientService.getAllIngredient().size();
		
		ingredientService.removeIngredientByID(29);
		
		assertEquals(i, produitService.getAllProduit().size());
		assertEquals(j-1,ingredientService.getAllIngredient().size() );
		
		System.out.println("Test DELETE ONLY 1 INGREDIENT -> OK");
		
	}
	*/
}
