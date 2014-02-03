package EntityManager;
import Bean.Adherent;


public class test {
	public static void main(String args[]){
		AdherentEntityManager aem= new AdherentEntityManager();
		//Adherent lAdherent = new Adherent("id","mdp","yves","Savean","155 Rue Général Bual","","44000","Nantes","France");
		//aem.creer(lAdherent);
		for(Adherent unAdherent : aem.ChercherAdherents()){
			System.out.println(unAdherent.getNomDeFamille());
		}
		aem.close();
	}
}
