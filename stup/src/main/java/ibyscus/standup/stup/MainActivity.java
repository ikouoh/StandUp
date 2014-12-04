package ibyscus.standup.stup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class MainActivity extends Activity {

    TextView ecran;
    Button b_new_game, b_options;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup BDD
        Setup s = new Setup(this);

        //init les boutons
        b_new_game = (Button) findViewById(R.id.b_new_game);
        b_options = (Button) findViewById(R.id.b_options);

        //Listener bouton 'new game'
        b_new_game.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startClick();
            }
        });




        //CarteDAO carteBdd = new CarteDAO(this);
        //Carte carteFromBdd = carteBdd.getCarteWithTitre("Football");
        //carteBdd.close();
        //ecran.setText(s.test());       //carteFromBdd.getIntituleCarte()
    }


    public String newGame(){

        AssetManager assetManager = getAssets();
        String carte = ".";

        InputStream fichier;
        FileInputStream f;
        ArrayList<Carte> Cartes;

        try {
            fichier =  assetManager.open("carte.jar");
            f = new FileInputStream(fichier.toString() );
            ObjectInputStream ois = new ObjectInputStream(f);
            Cartes = (ArrayList<Carte>) ois.readObject();
            ois.close();

            for(Carte c : Cartes){
                carte += c.getIntituleCarte()+".";
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


 /*
        Partie p = new Partie(3, assetManager);


        //System.out.println(p.Cartes.get(1).getIntituleCarte() );
        for(Equipe e: p.CartesEq.keySet() ){
            System.out.println("Equipe "+ e.getStringEquipe());
            for(int i = 0; i < p.CartesEq.get(e).size(); i++)
                System.out.println(p.CartesEq.get(e).get(i).getIntituleCarte() );
            System.out.println("-\t-\t-\t");
        }
        */
        return carte;
    }

    public void startClick(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);

    }
}
