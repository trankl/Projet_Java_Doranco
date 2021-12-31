package gestion_boulangerie;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import projet.server.gestion_boulangerie.model.Produit;
import projet.server.gestion_boulangerie.resource.ProduitResource;
import projet.server.gestion_boulangerie.service.CategorieService;
import projet.server.gestion_boulangerie.service.IngredientService;
import projet.server.gestion_boulangerie.service.MPService;
import projet.server.gestion_boulangerie.service.ProduitService;

class Test_case_produit {

    private ProduitService produitService = new ProduitService();
    private CategorieService categorieService = new CategorieService();
    private MPService mpService = new MPService();
	private IngredientService ingredientService = new IngredientService();
    private ProduitResource produitResource = new ProduitResource();
   /* 
    @Test
	public void whenCreateProduitinProduitResource() throws Exception {
		
	
		int categorie_id= 1;
		
		Produit produit = new Produit("pain",1.2,10);
		
		Produit prodb = produitResource.addProduit(produit, categorie_id);
		
		assertEquals("Pains",prodb.getProduitCategorie().getCategorie_nom());
	
	    System.out.println("test CREATE NEW PRODUCT so CREATE NEW CATEGORY IF NOT EXISTED -> OK");
		
	}
    

    
    /*
	@Test
	public void whenCreateProduit() throws Exception {
		
	
		Categorie categorie = categorieService.findById(1);
		
		Produit produit = new Produit("PainXL",1.05,7,categorie );
		
		Produit prodb = produitService.insertProduit(produit);
		
		assertEquals("Pains",prodb.getProduitCategorie().getCategorie_nom());
	
	    System.out.println("test CREATE NEW PRODUCT so CREATE NEW CATEGORY IF NOT EXISTED -> OK");
		
	}
    
 

	
	@Test
	public void whenDeleting1Produit_thenCategoriesShouldAlsoBeExisted() throws Exception {
		
		int i = produitService.getAllProduit().size();
		int j = categorieService.getAllCategorie().size();
		
		produitService.removeProduit(8);;
		
		assertEquals(i-1, produitService.getAllProduit().size());
		assertEquals(j, categorieService.getAllCategorie().size());
	    
	    System.out.println("test DELETE 1 PRODUCT -> OK");
	}

	@Test
	public void whenDeleting1Produit_thenDeletingHISMPDansTableIngredientsTOO() throws Exception {
		
		int i = produitService.getAllProduit().size();
		int j = ingredientService.getAllIngredient().size();
		int z= ingredientService.findByProduitId(7).size();
		
		produitService.removeProduit(7);
		
		assertEquals(i-1, produitService.getAllProduit().size());
		assertEquals(j-z,ingredientService.getAllIngredient().size() );
	    
	    System.out.println("test DELETE 1 PRODUCT with its ingredients -> OK");
	}
	*/
	
	@Test
	void whenFindProduitByName() throws Exception {
		
		String name = "Croissant";
		
		Produit produit = produitService.findByName(name);
		
		assertEquals(3, produit.getProduit_id());
		
		System.out.println("Test FIND 1 PRODUCT BY NAME --> OK");
	}

}
