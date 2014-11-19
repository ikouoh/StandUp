package ibyscus.standup.stup;
/*******************************
 *    A VOIR 
 * - utilisation nbJoueur 
 * - gérer les tours (gérer les fin de partie à la fin de chaque tour)
 *******************************/

import android.content.res.AssetManager;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import android.content.res.AssetManager;
import java.util.*;


public class Partie {
	Timer Temps;
	private int nbEquipe, nbJoueur;
	ArrayList<Carte> Cartes;
	TreeMap<Equipe, ArrayList<Carte>> CartesEq;
	
	/****************************
	 * Constructeurs
	 ****************************/
	@SuppressWarnings("unchecked")
	public Partie() {
        //AssetManager assetManager = getAssets();
       
		CartesEq = new TreeMap<Equipe, ArrayList<Carte> >();
		//Récupération des objets contenu dans un fichier .jar
		try {
			FileInputStream fichier = new FileInputStream("carte.jar");
            //InputStream fichier = assetManager.open("carte.jar");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			Cartes = (ArrayList<Carte>) ois.readObject();
			ois.close();
		}
		catch (java.io.IOException e) {
			e.printStackTrace();
		}
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
		
		
		Temps = new Timer();
		//setNbJoueur(4);
		setNbEquipe(2);
		creerEqs(getNbEquipe() );
	}
	
    public Partie(int nEquip, AssetManager assetManager) {


		CartesEq = new TreeMap<Equipe, ArrayList<Carte> >();
		//Récupération des objets contenu dans un fichier .jar
		try {
			//FileInputStream fichier = new FileInputStream("carte.jar");
            InputStream fichier = assetManager.open("carte.jar");
			ObjectInputStream ois = new ObjectInputStream(fichier);
			Cartes = (ArrayList<Carte>) ois.readObject();
			ois.close();
		}
        catch (java.io.IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
		
		Temps = new Timer();
		
        if(nEquip < 2){
           nEquip = 2;
        }
        //setNbJoueur(4);                  A VOIR
		setNbEquipe(nEquip);
		creerEqs(getNbEquipe());
	}


	/****************************
	 * Getteurs/Setteurs
	 ****************************/
	public int getNbEquipe() {
		return nbEquipe;
	}

	public void setNbEquipe(int nbEquipe) {
		this.nbEquipe = nbEquipe;
	}

	public int getNbJoueur() {
		return nbJoueur;
	}

	public void setNbJoueur(int nbJoueur) {
		this.nbJoueur = nbJoueur;
	}
	
	
	/****************************
	 * Méthodes
	 ****************************/
	private boolean creerEqs(int nbEq) {

            int choix, nC;
            boolean exist = false;     //booléen d'éxistance d'une carte dans une liste de cartes d'équipe
            ArrayList<Carte> c;
            Equipe e;
            Equipe.resetIds();
            //boucle de création des équipes
            for (int i = 0; i < nbEq; i++) {
                nC = 0;    //nombre de cartes de l'équipe courante
                e = new Equipe();    //équipe courante
                c = new ArrayList<Carte>();    //liste des cartes de l'équipe courante
                //TANT QUE l'équipe courante n'a pas le nombre requis de cartes, ajout de cartes dans la collection de l'équipe courante
                while (nC <= (Cartes.size() / (nbEq * 2))) {
                    choix = (int) (10 * Math.random() * Math.random());    //choix aléatoire d'un nombre
                    //SI le nombre choisi est inférieur au nombre total de cartes, ajout de la carte
                    if (choix < Cartes.size()) {
                        //TANT QUE la carte n'est pas dans le HashMap
                        while (!exist) {
                            //SI le TreeMap contient déjà des équipes
                            if (!CartesEq.isEmpty()) {
                                //POUR chaque équipe de contenu dans le HashMap
                                for (Equipe e1 : CartesEq.keySet()) {
                                    //SI la carte existe déjà dans la liste d'une équipe, passe le booléen d'éxistence à "true"
                                    if (CartesEq.get(e1).contains(Cartes.get(choix))) {
                                        exist = true;
                                    }
                                }
                                //SI la carte n'éxiste pas déjà dans la liste d'une autre équipe
                                if (!exist) {
                                    //SI la carte éxiste déjà dans la liste de l'équipe courante, passe le booléen d'éxistence à "true"
                                    if (c.contains(Cartes.get(choix))) {
                                        exist = true;
                                    }
                                    //SINON, ajout de la carte dans la liste de l'équipe courante
                                    else {
                                        c.add(Cartes.get(choix));
                                        nC++;
                                        exist = true;
                                    }
                                }
                            }
                            //SI le TreeMap ne contient aucune équipe
                            else {
                                //SI la carte éxiste déjà dans la liste de l'équipe courante, passe le booléen d'éxistence à "true"
                                if (c.contains(Cartes.get(choix))) {
                                    exist = true;
                                }
                                //SINON, ajout de la carte dans la liste de l'équipe courante
                                else {
                                    c.add(Cartes.get(choix));
                                    nC++;
                                    exist = true;
                                }
                            }

                        }
                    }
                    exist = false;   //réinitialise le booléen d'éxistence pour l'ajout d'une nouvelle carte
                }
                CartesEq.put(e, c);    //ajout de la nouvelle équipe et de sa collection de cartes
            }
            return true;

    }
	
    public void jouerTour(){
       
    }

}
