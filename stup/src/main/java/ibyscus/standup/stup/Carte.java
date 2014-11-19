package ibyscus.standup.stup;

import java.io.Serializable;


public class Carte implements Serializable, Comparable<Carte>{
	
	private static final long serialVersionUID = 1L;
	static int nbCarte = 0;
	private int idCarte;
	private String intituleCarte;

    Carte(){

    }

    Carte(String nom){
		//nbCarte++;
        //setIdCarte(nbCarte);
        setIntituleCarte(nom);
	}

    public void setIdCarte(int id){
        this.idCarte = id;
    }

    public void setIntituleCarte(String intitule){
        this.intituleCarte = intitule;
    }
	
	public int getIdCarte(){
		return idCarte;
	}
	
	public String getIntituleCarte(){
		return intituleCarte;
	}

	@Override
	public int compareTo(Carte o) {
		return getIdCarte()-o.getIdCarte();
	}
}
