package ibyscus.standup.stup;


import android.content.Context;

import java.util.ArrayList;

/**
 * Created by ibyscus on 02/05/2014.
 */
public class Setup {

    private Context context;

    Setup(Context context){
        this.context = context;

    }

    public void newBDD(){
        //Création d'une instance de ma classe CarteDAO
        CarteDAO carteBdd = new CarteDAO(context);

        //On ouvre la base de données pour écrire dedans
        carteBdd.open();

        //Création d'une liste de cartes
        ArrayList<Carte> cartes = new ArrayList<Carte>();
        cartes.add(new Carte("Football") );
        cartes.add(new Carte("Handball") );
        cartes.add(new Carte("Basketball") );
        cartes.add(new Carte("Rugby") );
        cartes.add(new Carte("Maison") );
        cartes.add(new Carte("Lit") );
        cartes.add(new Carte("Chaise") );
        cartes.add(new Carte("Ordinateur") );
        cartes.add(new Carte("Voiture") );
        cartes.add(new Carte("Radio") );


        //On insère les cartes que l'on vient de créer
        for(Carte carte: cartes){
            carteBdd.insertCarte(carte);
        }

        carteBdd.close();
    }

    public String test(){
        String ret;
        //Création d'une instance de ma classe CarteDAO
        CarteDAO carteBdd = new CarteDAO(context);

        //On ouvre la base de données pour écrire dedans
        carteBdd.open();
        try{
            ret = carteBdd.getCarteWithTitre("Voitur").getIntituleCarte();
        }catch (NullPointerException e){
            ret = "null";
        }

        carteBdd.close();
        return ret;
    }
}
