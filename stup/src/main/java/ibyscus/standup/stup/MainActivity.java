package ibyscus.standup.stup;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.res.AssetManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    TextView ecran;
    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.bNouvellePartie);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startClick();
            }
        });


        //Setup BDD
        Setup s = new Setup(this);

        //CarteDAO carteBdd = new CarteDAO(this);
        //Carte carteFromBdd = carteBdd.getCarteWithTitre("Football");
        //carteBdd.close();


        //ecran.setText(s.test());       //carteFromBdd.getIntituleCarte()



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
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

        /*
        setContentView(R.layout.game);

        ecran = (TextView) findViewById(R.id.TV_MotCourant);
        ecran.setText("ahaha");
        */
    }
}
