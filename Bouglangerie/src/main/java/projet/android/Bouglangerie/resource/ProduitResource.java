package projet.android.Bouglangerie.resource;

import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import projet.android.Bouglangerie.model.Produit;
import projet.android.Bouglangerie.service.ProduitService;

@Path ("/produit")
public class ProduitResource {

	private ProduitService service = new ProduitService();
	
	//cette methode a definit la partie de l'url d'acces a webservice sous path /getAllProduits
		//elle s'utilise avec GET
		//elle prend du JSON en entree
		@Path ("/getAllProduits")
		@GET  
		@Produces(MediaType.APPLICATION_JSON) 
		public List<Produit> getAllProduit () throws Exception {
		// On demande au service d'executer la methode "getAll" et retour la liste
		return service.getAllProduit();
		}
	
	
	
	//cette methode a definit la partie de l'url d'acces a webservice sous path /insertionProduit
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path ("/insertionProduit")
	@POST  
	@Consumes(MediaType.APPLICATION_JSON) 
	public Produit addProduit (Produit produit) throws Exception {
		// On demande au service d'executer la methode "insertProduit" et retour user
		return service.insertProduit(produit);
	}	

	
	//cette methode a definit la partie de l'url d'acces a webservice sous path /updateProduit
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path("/updateProduit/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Produit updateProduit (Produit produit, @PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "update" 
		return service.updateProduit(produit, id);
		
	}
	
	//cette methode a definit la partie de l'url d'acces a webservice sous path /deleteProduit/{id}
	//elle s'utilise avec DELETE
	//elle prend du JSON en entree
	@Path ("/deleteProduit/{id}")
	@DELETE  
	@Consumes(MediaType.APPLICATION_JSON) 
	public boolean deleteProduit (@PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "remove" 
		service.removeProduit(id);
		return true;
		
	}
	
	//cette methode a definit la partie de l'url d'acces a webservice sous path //findbyIDProduit/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyIDProduit/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Produit getProduitByID (@PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "findById(id)" et retour la produit avec id recherche
		return service.findById(id);
	}


	//cette methode a definit la partie de l'url d'acces a webservice sous path //findbyIDProduit/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyNameProduit/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Produit getProduitByName (@PathParam("name") String name) throws Exception {
		// On demande au service d'executer la methode "findById(id)" et retour la produit avec id recherche
		return service.findByName(name);
	}
	

}
