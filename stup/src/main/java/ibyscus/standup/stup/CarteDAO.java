package ibyscus.standup.stup;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CarteDAO {

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "standup.db";

    private static final String TABLE_CARTES = "table_cartes";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_INTITULE = "intitule";
    private static final int NUM_COL_INTITULE = 1;

    private SQLiteDatabase bdd;

    private MaBaseSQLite maBaseSQLite;

    public CarteDAO(Context context){
        //On crée la BDD et sa table
        maBaseSQLite = new MaBaseSQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        //on ouvre la BDD en écriture
        bdd = maBaseSQLite.getWritableDatabase();
    }

    public void close(){
        //on ferme l'accès à la BDD
        bdd.close();
    }

    public SQLiteDatabase getBDD(){
        return bdd;
    }

    public long insertCarte(Carte carte){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_INTITULE, carte.getIntituleCarte() );
        //on insère l'objet dans la BDD via le ContentValues
        return bdd.insert(TABLE_CARTES, null, values);
    }

    public int updateCarte(int id, Carte carte){
        //La mise à jour d'un livre dans la BDD fonctionne plus ou moins comme une insertion
        //il faut simplement préciser quel livre on doit mettre à jour grâce à l'ID
        ContentValues values = new ContentValues();
        values.put(COL_INTITULE, carte.getIntituleCarte() );
        return bdd.update(TABLE_CARTES, values, COL_ID + " = " +id, null);
    }

    public int removeCarteWithID(int id){
        //Suppression d'un livre de la BDD grâce à l'ID
        return bdd.delete(TABLE_CARTES, COL_ID + " = " +id, null);
    }

    public Carte getCarteWithTitre(String intitule){
        //Récupère dans un Cursor les valeurs correspondant à un livre contenu dans la BDD (ici on sélectionne le livre grâce à son titre)
        Cursor c = bdd.query(TABLE_CARTES, new String[] {COL_ID, COL_INTITULE}, COL_INTITULE + " LIKE \"%" + intitule +"%\"", null, null, null, null);

        return cursorToLivre(c);
    }

    //Cette méthode permet de convertir un cursor en un livre
    private Carte cursorToLivre(Cursor c){
        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (c.getCount() == 0)
            return null;

        //Sinon on se place sur le premier élément
        c.moveToFirst();
        //On créé un livre
        Carte carte = new Carte();
        //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
        carte.setIdCarte(c.getInt(NUM_COL_ID));
        carte.setIntituleCarte(c.getString(NUM_COL_INTITULE));
        //On ferme le cursor
        c.close();

        //On retourne le livre
        return carte;
    }
}