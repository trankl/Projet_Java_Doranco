package projet.server.gestion_boulangerie.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import projet.server.gestion_boulangerie.model.Categorie;
import projet.server.gestion_boulangerie.model.Ingredient;
import projet.server.gestion_boulangerie.model.MatierePremiere;
import projet.server.gestion_boulangerie.model.Produit;
import projet.server.gestion_boulangerie.model.Utilisateur;
import projet.server.gestion_boulangerie.service.CategorieService;
import projet.server.gestion_boulangerie.service.IngredientService;
import projet.server.gestion_boulangerie.service.MPService;
import projet.server.gestion_boulangerie.service.ProduitService;
import projet.server.gestion_boulangerie.service.UtilisateurService;

public class BoulangerieServiceRMI extends UnicastRemoteObject implements IBoulangerieRemoteRMI{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UtilisateurService userService = null;
	private ProduitService produitService = null;;
	private CategorieService categorieService = null;
	private MPService mpService = null;
	private IngredientService ingredientService = null;

	protected BoulangerieServiceRMI() throws RemoteException {
		super();
		this.userService = new UtilisateurService();
		this.produitService = new ProduitService();
		this.categorieService = new CategorieService();
		this.mpService = new MPService();
		this.ingredientService = new IngredientService();

	}

	@Override
	public List<Produit> getAllProduit() throws Exception, RemoteException {
		
		return produitService.getAllProduit();
	}

	@Override
	public Produit addProduit(Produit produit) throws Exception, RemoteException {
	
		return produitService.insertProduit(produit);
	}

	@Override
	public Produit updateProduit(Produit produit, int id) throws Exception, RemoteException {
		// TODO Auto-generated method stub
		return produitService.updateProduit(produit, id);
	}

	@Override
	public boolean deleteProduit(int id) throws Exception, RemoteException {
		boolean resultal = false;
		
		try {
		// On demande au service d'executer la methode "remove" 
		produitService.removeProduit(id);
		resultal = true;
		return resultal;
		} catch (Exception e) {
			throw new Exception("ne peut pas supprimer ce produit " + e.toString());
		}
	}

	@Override
	public Produit getProduitByID(int id) throws Exception, RemoteException {
		
		return produitService.findById(id);
	}

	@Override
	public Produit getProduitByName(String name) throws Exception, RemoteException {
		
		return produitService.findByName(name);
	}

	@Override
	public List<Categorie> getAllCategorie() throws Exception, RemoteException {
	
		return categorieService.getAllCategorie();
	}

	@Override
	public Categorie addCategorie(Categorie categorie) throws Exception, RemoteException {
		
		return categorieService.insertCategorie(categorie);
	}

	@Override
	public Categorie updateCategorie(Categorie categorie, int id) throws Exception, RemoteException {
		// TODO Auto-generated method stub
		return categorieService.updateCategorie(categorie, id);
	}

	@Override
	public boolean deleteCategorie(int id) throws Exception, RemoteException {
		boolean resultal = false;
		
		try {
		// On demande au service d'executer la methode "remove" 
		categorieService.removeCategorie(id);
		resultal = true;
		return resultal;
		} catch (Exception e) {
			throw new Exception("ne peut pas supprimer cette categorie " + e.toString());
		}
	}

	@Override
	public Categorie getCategorieByID(int id) throws Exception, RemoteException {
		
		return categorieService.findById(id);
	}

	@Override
	public Categorie getCategorieByName(String name) throws Exception, RemoteException {
		
		return categorieService.findByName(name);
	}

	@Override
	public List<MatierePremiere> getAllMP() throws Exception, RemoteException {
		
		return mpService.getAllMatierePremiere();
	}

	@Override
	public MatierePremiere addMP(MatierePremiere mp) throws Exception, RemoteException {
		
		return mpService.insertMatierePremiere(mp);
		
	}

	@Override
	public MatierePremiere updateMP(MatierePremiere mp, int id) throws Exception, RemoteException {
		
		return mpService.updateMatierePremiere(mp, id);
	}

	@Override
	public boolean deleteMP(int id) throws Exception, RemoteException {
boolean resultal = false;
		
		try {
		// On demande au service d'executer la methode "remove" 
		mpService.removeMatierePremiere(id);
		resultal = true;
		return resultal;
		} catch (Exception e) {
			throw new Exception("ne peut pas supprimer cette categorie " + e.toString());
		}
	}

	@Override
	public MatierePremiere getMPByID(int id) throws Exception, RemoteException {
		
		return mpService.findById(id);
	}

	@Override
	public MatierePremiere getMPByName(String name) throws Exception, RemoteException {
		
		return mpService.findByName(name);
	}

	@Override
	public List<Ingredient> getAllIngredient() throws Exception, RemoteException {
		
		return ingredientService.getAllIngredient();
	}

	@Override
	public Ingredient addIngredient(Ingredient ingredient) throws Exception, RemoteException {
	
		return ingredientService.insertIngredient(ingredient);
	}

	@Override
	public Ingredient updateIngredient(Ingredient ingredient, int id) throws Exception, RemoteException {
		
		return ingredientService.updateIngredient(ingredient, id);
	}

	@Override
	public boolean deleteIngredient(int id) throws Exception, RemoteException {
		boolean resultal = false;
		
		try {
		// On demande au service d'executer la methode "remove" 
		ingredientService.removeIngredientByID(id);
		resultal = true;
		return resultal;
		} catch (Exception e) {
			throw new Exception("ne peut pas supprimer cette categorie " + e.toString());
		}
	}

	@Override
	public List<Ingredient> getIngredientByProduitID(int id) throws Exception, RemoteException {
		
		return ingredientService.findByProduitId(id);
	}

	@Override
	public List<Utilisateur> getAllUtilisateur() throws Exception, RemoteException {
		
		return userService.getAllUtilisateur();
	}

	@Override
	public Utilisateur addUtilisateur(Utilisateur user) throws Exception, RemoteException {
		
		return userService.insertUtilisateur(user);
	}

	@Override
	public Utilisateur updateUtilisateur(Utilisateur user, int id) throws Exception, RemoteException {
		
		return userService.updateUtilisateur(user, id);
	}

	@Override
	public boolean deleteUtilisateur(int id) throws Exception, RemoteException {
boolean resultal = false;
		
		try {
		// On demande au service d'executer la methode "remove" 
		userService.removeUtilisateur(id);
		resultal = true;
		return resultal;
		} catch (Exception e) {
			throw new Exception("ne peut pas supprimer cette categorie " + e.toString());
		}
	}

	@Override
	public Utilisateur getUtilisateurByID(int id) throws Exception, RemoteException {
		
		return userService.findById(id);
	}

	@Override
	public Utilisateur getUtilisateurByName(String name) throws Exception, RemoteException {
		
		return userService.findByName(name);
	}

}
