package com.myapplication.boulangerie_serverRMI;

import java.rmi.Naming;
import java.util.List;
import java.util.Scanner;

import com.myapplication.boulangerie.model.Categorie;
import com.myapplication.boulangerie.model.Ingredient;
import com.myapplication.boulangerie.model.MatierePremiere;
import com.myapplication.boulangerie.model.Produit;
import com.myapplication.boulangerie.model.Utilisateur;

public class Boulangerie_clientRMI {

	public static void main(String[] args) {
		// Declarer les tableaux des objets type Produit, Categorie, Utilisateur
		List<Produit>  listeProduit = null;
		List<Categorie> listCategorie = null;
		List<Utilisateur> listUsers = null;
		List<MatierePremiere> listMPs= null;
		List<Ingredient> listIngredients = null;

		// Declarer objet type Utilisateur pour stocker user
		Utilisateur user = null;

		// Declarer objet type Scanner pour lire le resultat du clavier
		Scanner clavier = new Scanner(System.in);

		// Declarer un objet de type booleen pour stocker les resultats des fonctions de comparaison
		boolean verify = false;

		String non = Cas.Non.toString();
		String oui = Cas.Oui.toString();
		String arret = Cas.Arret.toString();
		String modifier = Cas.Modifier.toString();
		String creer = Cas.Creer.toString();
		String supprimer = Cas.Supprimer.toString();
		String liste = Cas.Liste.toString();
		String str_produit = Cas.Produit.toString();
		String str_ingredient = Cas.Ingredient.toString();
		String str_categorie = Cas.Categorie.toString();
		String str_matierepremiere = Cas.MatierePremiere.toString();
		String str_utilisateur = Cas.Utilisateur.toString();

		String continu = oui;


		// Connecter à l'annuaire
		try {
			// le stub permet de communiquer avec l'objet distant
			// le lookup pour retour 1 objet type remote
			// Realisation d'un cast de "Remote" vers "IBanqueRemote"
			IBoulangerieRemote stub = (IBoulangerieRemote) Naming.lookup("rmi://localhost:1099/BOULANGERIE");

			// Prendre la resultat des listes d'objets au server et mettre dans les objets qu'on a declare avant
			listUsers= stub.getAllUtilisateur();


			System.out.println("-----------CONNEXION-----------");

			System.out.println("Votre nom ?  ");
			//Prendre la valeur nom du clavier et mettre dans l'objet type String

			String user_nom = clavier.nextLine();

			System.out.println("Mot de passe ?  ");
			//Prendre la valeur mot de pass du clavier et mettre dans l'objet type String

			String user_pass = clavier.nextLine();

			// creer 1 nouvel utilisateur avec les valeurs prises au clavier
			user = new Utilisateur(user_nom, user_pass);

			// appeller fonction veririfer utilisateur et mettre son resultat dans l'objet type boolean
			verify = verifierUser(user, listUsers);

			// tant que verify = faux, il faut reconnecter
			while (!verify) {
				System.out.println(" Votre nom et/ ou mot de pass n'est pas correct");
				System.out.println(" Vous voulez reconnecter? O (oui) - N (non) ");

				String reponse = clavier.nextLine();
				// si la reponse et Non (N), on arrete la programme
				if (reponse.equals(non)){
					break;
				} // si non, on continue de la reconnexion
				else {
					System.out.println("Votre nom ?  ");
					user_nom = clavier.nextLine();

					System.out.println("Mot de passe ?  ");
					user_pass = clavier.nextLine();

					user = new Utilisateur(user_nom, user_pass);
					verify = verifierUser(user, listUsers);}

			}
			// si verify est vrai, on continue le programme et faire les actions
			while (verify) {

				// Appeller methode pour choisir actions et mettre le resultat dans 1 objet type int
				String choix = choixActionPrincipal(clavier);

				// methode switch pour faire les actions au choix de l'utilisateur
				// ----------- au cas de "voir liste des Categories" ------------------------------------
				if (choix.equals(str_categorie)) {
					listCategorie = stub.getAllCategorie();

					// Appeller methode pour affichage liste des categories
					affichageListCategories(listCategorie);

					clavier = new Scanner(System.in);
					// mettre le resultat dans 1 objet type String
					String actionListCategorie = choixCRUD(clavier);

					// si on choisi action arreter la programme, on arret la programme
					if (actionListCategorie.equals(arret)) {

						break;
					}

					// si le resultat est different que Non ou Arreter, faire les actions Modifier/Supprimer/Creer
					while (!actionListCategorie.equals(non) && !actionListCategorie.equals(arret)) {
						int categorie_id = 0;
						String categorie_nom = null;
						Categorie categorie = null;
						if (actionListCategorie.equals(modifier)) {
							System.out.println(" Vous avez choisi MODIFIER CATEGORIE. Quel Categorie à modifier? Donnez moi son id?");
							//clavier = new Scanner(System.in);
							categorie_id = clavier.nextInt();

							System.out.println(" Quel nom à changer? ");
							clavier = new Scanner(System.in);
							categorie_nom = clavier.nextLine();

							// Mettre nouvelle categorie avec nouveau nom
							categorie = new Categorie(categorie_nom);
							// appeller methode modifier categorie par son id et mettre dans objet categorie
							categorie = stub.updateCategorie(categorie, categorie_id);

							System.out.println(" Cette categorie est modifiee");
							System.out.println(" Categorie est : " + categorie);

						} else if (actionListCategorie.equals(supprimer)) {
							System.out.println(" Vous avez choisi SUPPRIMER CATEGORIE. Quel Categorie à modifier? Donnez moi son id?");

							categorie_id = clavier.nextInt();

							categorie = stub.getCategorieByID(categorie_id);


							System.out.println(" Vous voulez supprimer cette categorie : " + categorie );

							System.out.println("Ete-vous sur de supprimer cette categorie? O(oui) - N (non)");
							clavier = new Scanner(System.in);
							String reponse_confirmation_supprimation = clavier.nextLine();

							if (reponse_confirmation_supprimation.equals("O")) {
								// appeller methode supprimer categorie par son id
								stub.deleteCategorie(categorie_id);
								System.out.println(" Cette categorie est supprime");
							} else {
								System.out.println(" Cette demande est annulée");
							}

						} else if (actionListCategorie.equals(creer)) {
							System.out.println(" Vous avez choisi CREER NOUVELLE CATEGORIE.");
							System.out.println("Nom categorie = ");

							clavier  =  new Scanner(System.in);
							categorie_nom = clavier.nextLine();

							// Mettre nouvelle categorie avec nom
							categorie = new Categorie(categorie_nom);

							listCategorie = stub.getAllCategorie();
							// Creer 1 objet type boolean pour stocker resultat de la fonction verifier si cette categorie est exite ou pas
							boolean check = verifierCategorie(categorie, listCategorie);

							// si check = faux, on cree nouvelle categorie
							if (!check) {
								// Appeller methode pour creer nouvelle categorie
								categorie = stub.addCategorie(categorie);

								System.out.println(" Nouvelle categorie est cree");
								System.out.println(" Nouvelle categorie est : " + categorie);
							} else {
								System.out.println("Cette categorie est existee");
							}

						} else if (actionListCategorie.equals(liste)) {
							listCategorie = stub.getAllCategorie();

							affichageListCategories(listCategorie);

						}
						// Appeller methode pour afficher et lire le resultat s'utilisateur veut continue ou pas
						actionListCategorie =choixContinue(clavier);
						// si le reponse est non, on arret les actions CRUD
						if (actionListCategorie.equals(non)) {
							break;
							// si oui, on continue choisir autre actions CRUD
						} else {
							// Apeller methode pour afficher et lire le resultat d'action qu'utilisateur choisi à faire
							actionListCategorie = choixCRUD(clavier);
						}
					}

					// Appeller methode pour afficher et lire le resultat s'utilisateur veut voir autre liste du stock ou arreter le programme.
					actionListCategorie = choixVoirAutreListeOuArret(clavier);
					// si on choisi action arreter la programme, on arret la programme
					if (actionListCategorie.equals(arret)) {

						break;
					} 
				} 
				//------ au cas de "voir liste des Produits" --------------------------------------------
				else if (choix.equals(str_produit)) {

					listeProduit = stub.getAllProduit();

					affichageListProduit(listeProduit);

					clavier = new Scanner(System.in);
					String actionListProduit = choixCRUD(clavier);

					if (actionListProduit.equals(arret)) {

						break;
					}

					// si le resultat est different que Non ou Arreter, faire les actions Modifier/Supprimer/Creer
					while (!actionListProduit.equals(non) && !actionListProduit.equals(arret)) {
						int produit_id = 0;
						String produit_nom = null;
						double produit_prix = 0.0;
						int produit_quantite = 0;
						int produit_categorie_id = 0;
						Produit produit = null;

						if (actionListProduit.equals(modifier)) {
							System.out.println(" Vous avez choisi MODIFIER produit. Quel produit à modifier? Donnez moi son id?");
							//clavier = new Scanner(System.in);
							produit_id = clavier.nextInt();

							produit = stub.getProduitByID(produit_id);
							System.out.println(" Ce produit est " + produit);
							listIngredients = stub.getIngredientByProduitID(produit_id);
							affichageListIngredient(listIngredients);

							System.out.println(" Vous voulez modifier Produit ou ses Ingredients? P (Produit) - I (Ingredient) ");
							clavier = new Scanner(System.in);
							String reponse = clavier.nextLine();

							if (reponse.equals(str_produit)) {

								continu = oui;
								while (!continu.equals(non)) {
									System.out.println("Vous voulez changer quelle critère du produit ? \n"+
											"- 1 - Nom \n"+
											"- 2 - Prix \n" +
											"- 3 - Quantité \n"+
											"- 4 - Categorie \n");
									//clavier = new Scanner(System.in);
									int reponseModifierProduit= clavier.nextInt();

									if (reponseModifierProduit ==1) {

										System.out.println(" Nom = ?");
										clavier = new Scanner(System.in);
										produit_nom = clavier.nextLine();
										// Mettre à jour le nom de produit avec nouveau nom qui viens etre saisi
										produit.setProduit_nom(produit_nom);

										// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
										continu = confirmerContinueChoixCrite(clavier); 
										// si reponse est NON, on arret 
										if (continu.equals(non)){
											break;
										}
									} else if (reponseModifierProduit==2) {
										System.out.println(" Prix = ?");
										//	clavier = new Scanner(System.in);
										produit_prix = clavier.nextDouble();
										// Mettre à jour le prix de produit avec nouveau prix qui viens etre saisi
										produit.setProduit_prix(produit_prix);

										// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
										continu = confirmerContinueChoixCrite(clavier); 
										// si reponse est NON, on arret 
										if (continu.equals(non)){
											break;
										}
									} else if (reponseModifierProduit ==3) {
										System.out.println(" Quantite = ?");
										//	clavier = new Scanner(System.in);
										produit_quantite = clavier.nextInt();
										// Mettre à jour la quatite de produit avec nouveau quantité qui viens etre saisi
										produit.setProduit_quantite(produit_quantite);
										// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
										continu = confirmerContinueChoixCrite(clavier); 
										// si reponse est NON, on arret 
										if (continu.equals(non)){
											break;
										}
									} else if (reponseModifierProduit ==4) {
										// prendre la listes des categories de database
										listCategorie = stub.getAllCategorie();

										// Appeller methode pour afficher liste des categories
										affichageListCategories(listCategorie);

										System.out.println(" Categorie id = ?");
										//	clavier = new Scanner(System.in);
										produit_categorie_id = clavier.nextInt();
										// prendre categorie de la base de donnees grace à son id
										Categorie categorie = stub.getCategorieByID(produit_categorie_id);
										// Mettre à jour la categorie du produit avec nouveau categorie
										produit.setProduitCategorie(categorie);

										// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
										continu = confirmerContinueChoixCrite(clavier); 
										// si reponse est NON, on arret 
										if (continu.equals(non)){
											break;
										}
									}else {
										System.out.println("Choisissez 1, 2, 3 ou 4 , svp!");
									}
								}


								// appeller methode modifier produit par son id et mettre dans objet produit
								produit = stub.updateProduit(produit, produit_id);

								System.out.println(" Cette produit est modifiee");
								System.out.println(" produit est : " + produit);
							}
							else if(reponse.equals(str_ingredient)) {
								affichageListIngredient(listIngredients);

								System.out.println("Vous voulez modifier 1 ingredient ou ajouter nouvel ingredient? M (modifier) - C (Ajouter)");
								String actionListIngredient = clavier.nextLine();

								if (actionListIngredient.equals(modifier)) {
									System.out.println("Quel ingredient vous voulez modifier? donnez moi son id? ");
									//clavier = new Scanner(System.in);
									int ingredient_id = clavier.nextInt();

									Ingredient ingredient = stub.getIngredientByID(ingredient_id);
									System.out.println("Cet ingredient est " + ingredient);

									continu = oui;

									while (!continu.equals(non)) {
										System.out.println("Vous voulez changer quelle critère d'ingredient ? \n"+
												"- 1 - Matiere premiere \n"+
												"- 2 - Quantité \n");
										//clavier = new Scanner(System.in);
										int reponseModifierIngredient= clavier.nextInt();

										if (reponseModifierIngredient ==1) {
											listMPs = stub.getAllMP();
											affichageListMP(listMPs);

											System.out.println(" matiere première id = ?");
											clavier = new Scanner(System.in);
											int ingredient_mp_id = clavier.nextInt();
											// mettre à jour la matiere premier d'ingredient grace au son id
											ingredient.setMp_id(ingredient_mp_id);
											// Chercher ce matiere premiere grace au son id 
											MatierePremiere mp = stub.getMPByID(ingredient_mp_id);
											// prendre son unite et mettre dans 1 objet type String
											String ingredient_unite = mp.getMp_unite();
											// Mettre à jour l'unité d'ingredient
											ingredient.setIngredient_unite(ingredient_unite);

											// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
											continu = confirmerContinueChoixCrite(clavier); 
											// si reponse est NON, on arret 
											if (continu.equals(non)){
												break;
											}

										}else if (reponseModifierIngredient ==2) {

											System.out.println(" Quantité = ?");
											clavier = new Scanner(System.in);
											int ingredient_quantite = clavier.nextInt();
											// Mettre a jour le quantité d'ingredient avec nouveau quantité
											ingredient.setIngredient_quantite(ingredient_quantite);

											// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
											continu = confirmerContinueChoixCrite(clavier); 
											// si reponse est NON, on arret 
											if (continu.equals(non)){
												break;
											}	
										} else {
											System.out.println(" Choisissez 1 ou 2, svp!");
										}

									}

									stub.updateIngredient(ingredient, ingredient_id);
									System.out.println(" Cet ingredient est modifiée");


								}else if (actionListIngredient.equals(creer)) {
									listMPs  = stub.getAllMP();
									affichageListMP(listMPs);
									System.out.println(" Matière Première id = ?");
									clavier = new Scanner(System.in);
									int ingredient_md_id = clavier.nextInt();
									produit_id = produit.getProduit_id();
									// prendre la liste des ingredients du produit pour verifier si cet ingredient est existe
									listIngredients= stub.getIngredientByProduitID(produit_id);
									if (listIngredients!=null) {
										boolean check = verifierIngredient(ingredient_md_id, listIngredients);
										if (!check) {
											System.out.println(" Quantité = ?");
											int ingredient_quantite = clavier.nextInt();

											MatierePremiere mp = stub.getMPByID(ingredient_md_id);
											String ingredient_unite = mp.getMp_unite();

											Ingredient ingredient = new Ingredient(produit_id, ingredient_md_id, ingredient_quantite, ingredient_unite);

											stub.addIngredient(ingredient);
											System.out.println(" Nouvel ingredient est crée.");
										} else {
											System.out.println(" Cet ingredient est existé");
										}
									} else {

										System.out.println(" Quantité = ?");
										int ingredient_quantite = clavier.nextInt();

										MatierePremiere mp = stub.getMPByID(ingredient_md_id);
										String ingredient_unite = mp.getMp_unite();

										Ingredient ingredient = new Ingredient(produit_id, ingredient_md_id, ingredient_quantite, ingredient_unite);

										stub.addIngredient(ingredient);
										System.out.println(" Nouvel ingredient est crée.");

									}
								}


							}

						} else if (actionListProduit.equals(supprimer)) {
							System.out.println(" Vous avez choisi SUPPRIMER produit. Quel produit à modifier? Donnez moi son id?");

							produit_id = clavier.nextInt();

							produit = stub.getProduitByID(produit_id);

							System.out.println(" Vous voulez supprimer ce produit : " + produit );

							System.out.println("Ete-vous sur de supprimer ce produit? O(oui) - N (non)");
							clavier = new Scanner(System.in);
							String reponse_confirmation_supprimation = clavier.nextLine();

							if (reponse_confirmation_supprimation.equals(oui)) {
								// appeller methode supprimer produit par son id
								stub.deleteProduit(produit_id);
								System.out.println(" Cette produit est supprime");
							} else {
								System.out.println(" Cette demande est annulée");
							}


						} else if (actionListProduit.equals(creer)) {
							System.out.println(" Vous avez choisi CREER NOUVELLE produit.");

							System.out.println("Nom produit = ? ");
							clavier  =  new Scanner(System.in);
							produit_nom = clavier.nextLine();

							System.out.println(" Prix = ?");
							produit_prix = clavier.nextDouble();

							System.out.println(" Quantite = ?");
							produit_quantite = clavier.nextInt();

							// prendre la listes des categories de database
							listCategorie = stub.getAllCategorie();

							// Appeller methode pour afficher liste des categories
							affichageListCategories(listCategorie);

							System.out.println(" Categorie id = ?");
							produit_categorie_id = clavier.nextInt();


							// Mettre nouvelle produit avec nom
							produit = new Produit(produit_nom, produit_prix, produit_quantite);

							listeProduit = stub.getAllProduit();
							// Creer 1 objet type boolean pour stocker resultat de la fonction verifier si cette matierePremiere est exite ou pas
							boolean check = verifierProduit(produit, listeProduit);

							// si check = faux, on cree nouvelle matiere Premiere
							if (!check) {
								// Appeller methode pour creer nouvelle produit
								produit = stub.addProduit(produit, produit_categorie_id);

								System.out.println(" Nouveau produit est cree");
								System.out.println(" Nouvelle produit est : " + produit);
								System.out.println(" Ajouter les ingredients du produit? O(oui) - N(non)");
								clavier  =  new Scanner(System.in);
								String action_Ajouter_Ingredient = clavier.nextLine();
								while (!action_Ajouter_Ingredient.equals(non)) {
									listMPs  = stub.getAllMP();
									affichageListMP(listMPs);
									System.out.println(" Matière Première id = ?");
									clavier = new Scanner(System.in);
									int ingredient_md_id = clavier.nextInt();
									produit_id = produit.getProduit_id();
									// prendre la liste des ingredients du produit pour verifier si cet ingredient est existe
									listIngredients= stub.getIngredientByProduitID(produit_id);
									if (listIngredients!=null) {
										// Appelle methode verifier si cet ingredient est existe dans la liste du stock et mettre le resultat dans l'objet check
										check = verifierIngredient(ingredient_md_id, listIngredients);
										// if check est faux, c-a-d, pas cet ingredient dans liste, donc creer nouveau
										if (!check) {
											System.out.println(" Quantité = ?");
											int ingredient_quantite = clavier.nextInt();

											MatierePremiere mp = stub.getMPByID(ingredient_md_id);
											String ingredient_unite = mp.getMp_unite();

											Ingredient ingredient = new Ingredient(produit_id, ingredient_md_id, ingredient_quantite, ingredient_unite);

											stub.addIngredient(ingredient);
											System.out.println(" Nouvel ingredient est crée.");
										} else {
											System.out.println(" Cet ingredient est existé");
											
										}System.out.println(" Ajouter autre ingredient du produit? O(oui) - N(non)");
										clavier  =  new Scanner(System.in);
										action_Ajouter_Ingredient = clavier.nextLine();
									} else {

										System.out.println(" Quantité = ?");
										int ingredient_quantite = clavier.nextInt();

										MatierePremiere mp = stub.getMPByID(ingredient_md_id);
										String ingredient_unite = mp.getMp_unite();

										Ingredient ingredient = new Ingredient(produit_id, ingredient_md_id, ingredient_quantite, ingredient_unite);

										stub.addIngredient(ingredient);
										System.out.println(" Nouvel ingredient est crée.");
										System.out.println(" Ajouter autre ingredient du produit? O(oui) - N(non)");
										//clavier  =  new Scanner(System.in);
										action_Ajouter_Ingredient = clavier.nextLine();
									} 
								}
							}
							else {
								System.out.println("Ce produit est existé");
							}




						}else if (actionListProduit.equals(liste)) {
							listeProduit= stub.getAllProduit();

							affichageListProduit(listeProduit);

						}
						clavier = new Scanner(System.in);
						// Appeller methode pour afficher et lire le resultat s'utilisateur veut continue ou pas
						actionListProduit = choixContinue(clavier);

						if (actionListProduit.equals(non)) {
							break;
						} else {
							// Apeller methode pour afficher et lire le resultat d'action qu'utilisateur choisi à faire
							actionListProduit = choixCRUD(clavier);
						}
					}

					// Appeller methode pour afficher et lire le resultat s'utilisateur veut voir autre liste du stock ou arreter le programme.
					actionListProduit = choixVoirAutreListeOuArret(clavier);
					if (actionListProduit.equals(arret)) {

						break;
					}

				}
				//----------- au cas de "voir liste des Matieres premieres"--------------------------------------------
				else if (choix.equals(str_matierepremiere)) {

					listMPs = stub.getAllMP();

					// Appeller methode pour affichage liste des matierePremieres
					affichageListMP(listMPs);

					clavier  =  new Scanner(System.in);
					// mettre le resultat dans 1 objet type String
					String actionListMP = choixCRUD(clavier);

					if (actionListMP.equals(arret)) {

						break;
					}

					// si le resultat est different que Non ou Arreter, faire les actions Modifier/Supprimer/Creer
					while (!actionListMP.equals(non) && !actionListMP.equals(arret)) {
						int mp_id = 0;
						String mp_nom = null;
						Double mp_prix = 0.0;
						int mp_quantite = 0;
						String mp_unite = null;
						String mp_marque = null;
						MatierePremiere mp = null;

						if (actionListMP.equals(modifier)) {
							System.out.println(" Vous avez choisi MODIFIER MATIERE PREMIERE. Quel matiere premiere à modifier? Donnez moi son id?");
							clavier = new Scanner(System.in);
							mp_id = clavier.nextInt();

							mp= stub.getMPByID(mp_id);
							System.out.println(" Matière première est "+ mp);

							continu = oui;
							while (!continu.equals(non)) {
								System.out.println("Vous voulez changer quelle critère? \n"+
										"- 1 - Nom \n"+
										"- 2 - Prix \n" +
										"- 3 - Quantité \n" +
										"- 4 - Unité \n" +
										"- 5 - Marque");
								//clavier = new Scanner(System.in);
								int reponseModifierMP= clavier.nextInt();

								if (reponseModifierMP ==1) {
									System.out.println(" Nom = ? ");
									clavier = new Scanner(System.in);
									mp_nom = clavier.nextLine();
									mp.setMp_nom(mp_nom);
									// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
									continu = confirmerContinueChoixCrite(clavier); 
									// si reponse est NON, on arret 
									if (continu.equals(non)){
										break;
									}
								} else if (reponseModifierMP ==2) {
									System.out.println(" Prix (type 0,00) = ? ");
									//clavier = new Scanner(System.in);
									mp_prix = clavier.nextDouble();
									mp.setMp_prix(mp_prix);
									// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
									continu = confirmerContinueChoixCrite(clavier);
									// si reponse est NON, on arret 
									if (continu.equals(non)){
										break;
									}
								}else if (reponseModifierMP ==3) {

									System.out.println(" Quantité = ? ");
									//clavier = new Scanner(System.in);
									mp_quantite = clavier.nextInt();
									mp.setMp_quantite(mp_quantite);
									// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
									continu = confirmerContinueChoixCrite(clavier);
									// si reponse est NON, on arret 
									if (continu.equals(non)){
										break;
									}
								}else if (reponseModifierMP ==4) {

									System.out.println(" Unité = ? ");
									//clavier = new Scanner(System.in);
									mp_unite= clavier.next();
									mp.setMp_unite(mp_unite);

									// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
									continu = confirmerContinueChoixCrite(clavier);
									// si reponse est NON, on arret 
									if (continu.equals(non)){
										break;
									}
								}else if (reponseModifierMP ==5) {

									System.out.println(" Marque = ? ");
									//clavier = new Scanner(System.in);
									mp_marque = clavier.next();
									mp.setMp_marque(mp_marque);

									// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
									continu = confirmerContinueChoixCrite(clavier);
									// si reponse est NON, on arret 
									if (continu.equals(non)){
										break;
									}
								}else {
									System.out.println("Choisissez 1, 2, 3 ,4 ou 5, svp!");
								}
							}

							// appeller methode modifier matierePremiere par son id et mettre dans objet matierePremiere
							mp = stub.updateMP(mp, mp_id);

							System.out.println(" Cette matierePremiere est modifiee");
							System.out.println(" MatierePremiere est : " + mp);


						} else if (actionListMP.equals(supprimer)) {
							System.out.println(" Vous avez choisi SUPPRIMER MATIERE PREMIRE. Quel Matiere Premiere à modifier? Donnez moi son id?");

							mp_id = clavier.nextInt();

							mp = stub.getMPByID(mp_id);

							System.out.println(" Vous voulez supprimer cette matiere premiere : " + mp );

							System.out.println("Ete-vous sur de supprimer cette matiere premiere? O(oui) - N (non)");
							clavier = new Scanner(System.in);
							String reponse_confirmation_supprimation = clavier.nextLine();

							if (reponse_confirmation_supprimation.equals(oui)) {
								// appeller methode supprimer matierePremiere par son id
								stub.deleteMP(mp_id);
								System.out.println(" Cette matierePremiere est supprime");
							} else {
								System.out.println(" Cette demande est annulée");
							}

						} else if (actionListMP.equals(creer)) {
							System.out.println(" Vous avez choisi CREER NOUVELLE MATIERE PREMIERE.");

							System.out.println("Nom matiere premiere = ");
							// lire le resultat de la clavier et mettre dans objet
							mp_nom = clavier.nextLine();

							System.out.println(" Prix (type 0,00) =  ");
							mp_prix = clavier.nextDouble();

							System.out.println(" Quantité = ? ");
							mp_quantite = clavier.nextInt();

							System.out.println(" Unité = ? ");
							mp_unite= clavier.next();

							System.out.println(" Marque = ? ");
							mp_marque = clavier.next();

							// Mettre nouvelle matierePremiere avec nom
							mp = new MatierePremiere(mp_nom, mp_prix, mp_quantite, mp_unite, mp_marque);

							listMPs = stub.getAllMP();
							// Creer 1 objet type boolean pour stocker resultat de la fonction verifier si cette matierePremiere est exite ou pas
							boolean check = verifierMP(mp, listMPs);

							// si check = faux, on cree nouvelle matiere Premiere
							if (!check) {
								// Appeller methode pour creer nouvelle matiere Premiere
								mp = stub.addMP(mp);

								System.out.println(" Nouvelle matiere premiere est cree");
								System.out.println(" Nouvelle matiere premiere est : " + mp);
							} else {
								System.out.println("Cette matiere premiere est existee");
							}

						}else if (actionListMP.equals(liste)) {
							listMPs = stub.getAllMP();
							affichageListMP(listMPs);
						}
						clavier = new Scanner(System.in);
						// Appeller methode pour afficher et lire le resultat s'utilisateur veut continue ou pas
						actionListMP = choixContinue(clavier);

						if (actionListMP.equals(non)) {
							break;
						} else {
							// Apeller methode pour afficher et lire le resultat d'action qu'utilisateur choisi à faire
							actionListMP = choixCRUD(clavier);
						}

					}

					// Appeller methode pour afficher et lire le resultat s'utilisateur veut voir autre liste du stock ou arreter le programme.
					actionListMP = choixVoirAutreListeOuArret(clavier);
					if (actionListMP.equals(arret)) {

						break;
					} 
				}
				//----------- au cas de "voir liste des Utilisateurs"--------------------------------------

				else if (choix.equals(str_utilisateur)) {
					if (!user.getUser_nom().equals("admin")) {
						System.out.println(" INTERDIT!!! Vous n'êtes pas autorisé à consulter la liste des Utilisateurs.");
						System.out.println(" \n Vous voulez continuer autre 'case' ou arreter du programme? O (continue) - A (Arreter)");
						clavier  =  new Scanner(System.in);
						String actionCase4 = clavier.nextLine();
						if (actionCase4.equals(arret)) {

							break;
						} 

					}else {

						listUsers = stub.getAllUtilisateur();

						// Appeller methode pour affichage liste des utilisateurs
						affichagelistUsers(listUsers);

						clavier  =  new Scanner(System.in);
						// mettre le resultat dans 1 objet type String
						String actionListUsers = choixCRUD(clavier);
						if (actionListUsers.equals(arret)) {

							break;
						}

						// si le resultat est different que Non ou Arreter, faire les actions Modifier/Supprimer/Creer
						while (!actionListUsers.equals(non) && !actionListUsers.equals(arret)) {
							int utilisateur_id = 0;
							String utilisateur_nom = null;
							String utilisateur_pass = null;
							Utilisateur utilisateur = null;
							if (actionListUsers.equals(modifier)) {
								System.out.println(" Vous avez choisi MODIFIER UTILISATEUR. Quel Utilisateur à modifier? Donnez moi son id?");
								//clavier = new Scanner(System.in);
								utilisateur_id = clavier.nextInt();

								utilisateur = stub.getUtilisateurByID(utilisateur_id);
								System.out.println(" Utilisateur est : " + utilisateur);

								continu = oui;

								while (!continu.equals(non)) {
									System.out.println("Vous voulez changer quelle critère? \n"+
											"- 1 - Nom \n"+
											"- 2 - Mot de passe");

									//clavier = new Scanner(System.in);
									int reponseModifierUser= clavier.nextInt();

									if (reponseModifierUser ==1) {
										if (utilisateur_id ==1) {
											System.out.println("INTERDIT!!! Vous ne pouvez pas changer nom du 'ADMIN' ");
										}else {
											System.out.println(" Nom = ? ");
											clavier = new Scanner(System.in);
											utilisateur_nom = clavier.nextLine();
											utilisateur.setUser_nom(utilisateur_nom);
										}
										// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
										continu = confirmerContinueChoixCrite(clavier); 
										// si reponse est NON, on arret 
										if (continu.equals(non)){
											break;
										}
									} else if (reponseModifierUser ==2) {

										System.out.println(" Mot de passe = ? ");
										//clavier = new Scanner(System.in);
										utilisateur_pass= clavier.nextLine();
										utilisateur.setUser_password(utilisateur_pass);

										// Apppele methode de confirmation continuer choix autre critere et mettre le resultat dans objet continue
										continu = confirmerContinueChoixCrite(clavier);
										// si reponse est NON, on arret 
										if (continu.equals(non)){
											break;
										}
									}else {
										System.out.println(" Choisissez 1 ou 2, svp!");
									}
								}

								// appeller methode modifier utilisateur par son id et mettre dans objet utilisateur
								utilisateur = stub.updateUtilisateur(utilisateur, utilisateur_id);

								System.out.println(" Cet utilisateur est modifiee");
								System.out.println(" Utilisateur est : " + utilisateur);

							} else if (actionListUsers.equals(supprimer)) {
								System.out.println(" Vous avez choisi SUPPRIMER UTILISATEUR. Quel Utilisateur à modifier? Donnez moi son id?");

								utilisateur_id = clavier.nextInt();

								if (utilisateur_id == 1) {
									System.out.println("INTERDIT!!! Vous ne pouvez pas supprimer 'ADMIN' ");
								}else {
									utilisateur = stub.getUtilisateurByID(utilisateur_id);


									System.out.println(" Vous voulez supprimer cet utilisateur : " + utilisateur);

									System.out.println("Ete-vous sur de supprimer cet utilisateur? O(oui) - N (non)");
									clavier = new Scanner(System.in);
									String reponse_confirmation_supprimation = clavier.nextLine();

									if (reponse_confirmation_supprimation.equals(oui)) {
										// appeller methode supprimer categorie par son id
										stub.deleteUtilisateur(utilisateur_id);
										System.out.println(" Cet utilisateur est supprime");
									} else {
										System.out.println(" Cette demande est annulée");
									}
								}

							} else if (actionListUsers.equals(creer)) {
								System.out.println(" Vous avez choisi CREER NOUVEL UTILISATEUR.");

								System.out.println("Nom utilisateur = ");
								clavier  =  new Scanner(System.in);
								utilisateur_nom = clavier.nextLine();

								System.out.println(" Mot de passe = ? ");
								//clavier = new Scanner(System.in);
								utilisateur_pass= clavier.nextLine();

								// Mettre nouvel utilisateur avec nom et mot de pass
								utilisateur = new Utilisateur(utilisateur_nom, utilisateur_pass);

								listUsers = stub.getAllUtilisateur();
								// Creer 1 objet type boolean pour stocker resultat de la fonction verifier si cet utilisateur est exite ou pas
								boolean check = verifierUtilisateurPourCreerNouvelUtilisateur(utilisateur, listUsers);

								// si check = faux, on cree nouvel utilisateur
								if (!check) {
									// Appeller methode pour creer nouvel utilisateur
									utilisateur = stub.addUtilisateur(utilisateur);

									System.out.println(" Nouvel utilisateur est cree");
									System.out.println(" Nouvel utilisateur est : " + utilisateur);
								} else {
									System.out.println("Cet utilisateur est existee");
								}

							} else if (actionListUsers.equals(liste)) {
								listUsers= stub.getAllUtilisateur();
								affichagelistUsers(listUsers);
							}
							clavier  =  new Scanner(System.in);
							// Appeller methode pour afficher et lire le resultat s'utilisateur veut continue ou pas
							actionListUsers = choixContinue(clavier);

							if (actionListUsers.equals(non)) {
								break;
							} else {
								// Apeller methode pour afficher et lire le resultat d'action qu'utilisateur choisi à faire
								actionListUsers = choixCRUD(clavier);
							}
						}
						clavier  =  new Scanner(System.in);
						// Appeller methode pour afficher et lire le resultat s'utilisateur veut voir autre liste du stock ou arreter le programme.
						actionListUsers = choixVoirAutreListeOuArret(clavier);
						if (actionListUsers.equals(arret)) {

							break;
						} 

					}

				}
				//----------- au cas de "Arreter du programme"---------------------------------------------
				else if (choix.equals(arret)) {
					break;
				}



			}


		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("ERREUR: " + e.toString());
		} finally {
			clavier.close();
			System.out.println("------------DECONNEXION--------------------");
			
		}
	}






	public enum Cas {
		Modifier {
			public String toString() {return "M";}
		},
		Supprimer {
			public String toString() {return "S";}
		},
		Creer {
			public String toString() {return "C";}
		},
		Liste {
			public String toString() {return "L";}
		},
		Arret {
			public String toString() {return "A";}
		},
		Non {
			public String toString() {return "N";}
		},
		Oui {
			public String toString() {return "O";}
		},
		Produit {
			public String toString() {return "P";}
		},
		Ingredient {
			public String toString() {return "I";}
		},
		Categorie {
			public String toString() {return "CA";}
		},
		MatierePremiere {
			public String toString() {return "MP";}
		},
		Utilisateur {
			public String toString() {return "U";}
		};
		public abstract String toString();
	}


	private static String choixActionPrincipal (Scanner clavier) {
		System.out.println("Quelle liste que vous voulez consulter? \n"
				+ "- CA - Voir liste des Catagories \n"
				+ "- P - Voir liste des Produits \n"
				+ "- MP - Voir liste des Matieres premieres \n"
				+ "- U - Voir liste des Utilisateurs \n"
				+ "- A - Arreter du programme");
		// Lire resultat au clavier
		//clavier =  new Scanner(System.in);

		// mettre le resultat dans 1 objet type int
		String choix = clavier.next();

		return choix;
	}

	private static String choixCRUD (Scanner clavier){
		System.out.println("\n Quelle action que vous voulez faire? \n"
				+ "- M - Modifier \n"
				+ "- S - Supprimer \n"
				+ "- C - Creer \n"
				+ "- L - Revoir la listes \n"
				+ "- A - Arreter du programme ");

		// lire le resultat au clavier
		//clavier  =  new Scanner(System.in);

		// mettre le resultat dans 1 objet type String
		String action = clavier.nextLine();
		return action;
	}

	private static String choixVoirAutreListeOuArret(Scanner clavier) {
		System.out.println(" \n Vous voulez continuer à voir autre liste ou arreter du programme? O (continue) - A (Arreter)");
		//clavier1  =  new Scanner(System.in);
		String choix = clavier.nextLine();

		return choix;
	}

	private static String choixContinue(Scanner clavier) {
		System.out.println(" Vous voulez continuer? O (oui) - N (non)");
		//clavier  =  new Scanner(System.in);
		String choix = clavier.nextLine();
		return choix;
	}



	private static boolean verifierIngredient(int ingredient_md_id, List<Ingredient> listIngredients) {
		boolean check = false;

		for (Ingredient ingredient : listIngredients) {
			if (ingredient.getMp_id()==ingredient_md_id) {
				check= true;
			}
		}

		return check;
	}

	private static boolean verifierProduit(Produit produit, List<Produit> listeProduit) {

		boolean check = false;

		String produit_nom = produit.getProduit_nom().toUpperCase();

		for (Produit produit2 : listeProduit) {
			if (produit_nom.equals(produit2.getProduit_nom().toUpperCase())) {
				check = true;
			}
		}

		return check;
	}


	private static boolean verifierUtilisateurPourCreerNouvelUtilisateur(Utilisateur user,
			List<Utilisateur> listUsers) {
		boolean check = false; 
		String user_name = user.getUser_nom().toUpperCase();

		for (Utilisateur utilisateur : listUsers) {
			if (user_name.equals(utilisateur.getUser_nom().toUpperCase())) {
				check = true;
				break;
			}		
		}

		return check;
	}



	private static boolean verifierMP(MatierePremiere mp, List<MatierePremiere> listMPs) {
		boolean check = false; 
		String mp_nom= mp.getMp_nom().toUpperCase();
		String mp_marque = mp.getMp_marque().toUpperCase();
		for (MatierePremiere matierePremiere : listMPs) {
			if (mp_nom.equals(matierePremiere.getMp_nom().toUpperCase())&&mp_marque.equals(matierePremiere.getMp_marque().toUpperCase())) {
				check = true;
			}
		}
		return check;
	}



	private static boolean verifierCategorie(Categorie categorie, List<Categorie> listCategorie) {
		boolean check = false; 
		String categorie_nom= categorie.getCategorie_nom().toUpperCase();
		for (Categorie c : listCategorie) {
			if (categorie_nom.equals(c.getCategorie_nom().toUpperCase())) {
				check = true;
			}
		}

		return check;
	}

	private static boolean verifierUser(Utilisateur user, List<Utilisateur> listUsers) {

		boolean check = false; 
		String user_name = user.getUser_nom();
		String user_pass = user.getUser_password();

		for (Utilisateur utilisateur : listUsers) {
			if (utilisateur.getUser_nom().equals(user_name)&& utilisateur.getUser_password().equals(user_pass)) {
				check = true;
				break;
			}		
		}

		return check;
	}


	private static String confirmerContinueChoixCrite(Scanner clavier) {
		System.out.println("Vous voulez changer autre critère? O (oui) - N (non)");
		String choix = clavier.next();
		return choix;
	}

	private static void affichageListCategories(List<Categorie> listCategorie){

		System.out.println("Liste des categories");

		// Afficher liste des categories
		for (Categorie categorie : listCategorie) {
			System.out.println(categorie);
		}

	}

	private static void affichageListMP(List<MatierePremiere> listMPs) {
		System.out.println("Liste des Matières Premières");
		// creer 1 objet type boolean pour stocker resultat de la comparaison
				boolean check = false;
		// Afficher liste des Matières Premières
		for (MatierePremiere matierePremiere : listMPs) {
			System.out.println(matierePremiere);
			int quantite = matierePremiere.getMp_quantite();
			// Comparer si produit est negatif ou =0, check est vrai
						if (quantite<=0) {
							check = true;
						}
		}
		// si check est vrai, afficher notification
				if (check) {
					System.out.println("\n ------------------------------------------------------------------------------------------------\n"+
							"         ATTENTION: IL Y A MATIERE PREMIERE N'EST PLUS EN STOCK! \n          A remplir ou supprimer, svp "+
							"\n ------------------------------------------------------------------------------------------------");
				}
	}

	private static void affichageListIngredient(List<Ingredient> listIngredients) {
		System.out.println("Liste des ingredients du produit");

		// Afficher liste des ingredients du produit
		for (Ingredient ingredient : listIngredients) {
			System.out.println(ingredient);
			
		} 
	}


	private static void affichagelistUsers(List<Utilisateur> listUsers) {
		System.out.println("Liste des utilisateurs");
		
		// Afficher liste des Utilisateurs
		for (Utilisateur utilisateur : listUsers) {
			System.out.println(utilisateur);
		}


	}

	private static void affichageListProduit(List<Produit> listeProduit) {
		System.out.println("Liste des produits");
		// creer 1 objet type boolean pour stocker resultat de la comparaison
		boolean check = false;
		for (Produit produit : listeProduit) {
			System.out.println(produit);
			int quantite = produit.getProduit_quantite();
			// Comparer si produit est negatif ou =0, check est vrai
			if (quantite<=0) {
				check = true;
			}
		}
		// si check est vrai, afficher notification
		if (check) {
			System.out.println("\n ------------------------------------------------------------------------------------------------\n"+
								"         ATTENTION: IL Y A PRODUIT N'EST PLUS EN STOCK! \n          A remplir ou supprimer, svp "+
								"\n ------------------------------------------------------------------------------------------------");
		}

	}

}
