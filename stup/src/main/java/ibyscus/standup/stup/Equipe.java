package ibyscus.standup.stup;

/*******************************
 *    A FAIRE
 * 
 *******************************/


public class Equipe implements Comparable<Equipe>{
	private static int ids = 1;
	private int nEquipe, nbMembres, points = 0;
	
	Equipe(){
		setNbMembres(2);
		setnEquipe(ids);
		ids++;
	}
	
	Equipe(int nbMbr){
		setNbMembres(nbMbr);
		setnEquipe(ids);
        setPoints();
		ids++;
	}

	
	/****************************
	 * Getteurs/Setteurs
	 ****************************/
	public int getnEquipe() {
		return nEquipe;
	}

    public String getStringEquipe() {
		return ( (Integer)nEquipe).toString();
	}
    
	public void setnEquipe(int nEquipe) {
		this.nEquipe = nEquipe;
	}

	public int getNbMembres() {
		return nbMembres;
	}

	public void setNbMembres(int nbMembres) {
		this.nbMembres = nbMembres;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints() {
		this.points = points + 10;
	}

	
	
	/****************************
	 * Méthodes
	 ****************************/
	@Override
	public int compareTo(Equipe o) {
		return nEquipe - o.getnEquipe();
	}

    public static void resetIds(){
       ids = 1;
    }
	
}
