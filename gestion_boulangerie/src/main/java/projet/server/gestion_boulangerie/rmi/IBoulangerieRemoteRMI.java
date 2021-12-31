package projet.server.gestion_boulangerie.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import jakarta.ws.rs.PathParam;
import projet.server.gestion_boulangerie.model.Categorie;
import projet.server.gestion_boulangerie.model.Ingredient;
import projet.server.gestion_boulangerie.model.MatierePremiere;
import projet.server.gestion_boulangerie.model.Produit;
import projet.server.gestion_boulangerie.model.Utilisateur;

public interface IBoulangerieRemoteRMI extends Remote {
	
	public List<Produit> getAllProduit () throws Exception, RemoteException;
	
	public Produit addProduit (Produit produit) throws Exception, RemoteException;
	
	public Produit updateProduit (Produit produit,@PathParam("id") int id) throws Exception, RemoteException;
	
	public boolean deleteProduit (@PathParam("id") int id) throws Exception, RemoteException;
	
	public Produit getProduitByID (@PathParam("id") int id) throws Exception, RemoteException;
	
	public Produit getProduitByName (@PathParam("name") String name) throws Exception, RemoteException;
	
	public List<Categorie> getAllCategorie () throws Exception, RemoteException;
	
	public Categorie addCategorie (Categorie categorie) throws Exception, RemoteException;
	
	public Categorie updateCategorie (Categorie categorie, @PathParam("id") int id) throws Exception, RemoteException;
	
	public boolean deleteCategorie (@PathParam("id") int id) throws Exception, RemoteException;
	
	public Categorie getCategorieByID (@PathParam("id") int id) throws Exception, RemoteException;
	
	public Categorie getCategorieByName (@PathParam("name") String name) throws Exception, RemoteException;
	
	public List<MatierePremiere> getAllMP () throws Exception, RemoteException;
	
	public MatierePremiere addMP (MatierePremiere mp) throws Exception, RemoteException;
	
	public MatierePremiere updateMP (MatierePremiere mp, @PathParam("id") int id) throws Exception, RemoteException;
	
	public boolean deleteMP (@PathParam("id") int id) throws Exception,RemoteException;
	
	public MatierePremiere getMPByID (@PathParam("id") int id) throws Exception, RemoteException;
	
	public MatierePremiere getMPByName (@PathParam("name") String name) throws Exception, RemoteException;
	
	public List<Ingredient> getAllIngredient () throws Exception, RemoteException;
	
	public Ingredient addIngredient (Ingredient ingredient) throws Exception , RemoteException;
	
	public Ingredient updateIngredient (Ingredient ingredient, @PathParam("id") int id) throws Exception, RemoteException;
	
	public boolean deleteIngredient (@PathParam("id") int id) throws Exception,RemoteException;
	
	public List<Ingredient> getIngredientByProduitID (@PathParam("id") int id) throws Exception,RemoteException;

	public List<Utilisateur> getAllUtilisateur () throws Exception,RemoteException;
	
	public Utilisateur addUtilisateur (Utilisateur user) throws Exception, RemoteException;
	
	public Utilisateur updateUtilisateur (Utilisateur user, @PathParam("id") int id) throws Exception, RemoteException;
	
	public boolean deleteUtilisateur (@PathParam("id") int id) throws Exception, RemoteException;
	
	public Utilisateur getUtilisateurByID (@PathParam("id") int id) throws Exception, RemoteException;
	
	public Utilisateur getUtilisateurByName (@PathParam("name") String name) throws Exception, RemoteException;
}
