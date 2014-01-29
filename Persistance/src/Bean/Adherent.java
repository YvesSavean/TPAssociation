package Bean;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name="ADHERENT", schema="ROOT")
public class Adherent {
	
	@Id
	@NotNull
	@Column(name="IDENTIFIANT")
	private int identifiant;
	
	@NotNull @Size(min=1, max=20)
	@Column(name="MOTDEPASSE")
	private String motDePasse;
	
	@NotNull @Size(min=1, max=30)
	@Column(name="NOMDEFAMIlLE")
	private String nomDeFamille;
	
	@NotNull @Size(min=1, max=30)
	@Column(name="PRENOM")
	private int prenom;
	
	@NotNull @Size(min=1, max=50)
	@Column(name="ADRESSE")
	private String codePostal;
	
	@NotNull @Size(min=1, max=30)
	@Column(name="VILLE")
	private String ville;
	
	public int getIdentifiant() {
		return identifiant;
	}

	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getNomDeFamille() {
		return nomDeFamille;
	}

	public void setNomDeFamille(String nomDeFamille) {
		this.nomDeFamille = nomDeFamille;
	}

	public int getPrenom() {
		return prenom;
	}

	public void setPrenom(int prenom) {
		this.prenom = prenom;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	@Column(name="PAYS")
	private String pays;

	public Adherent() {
		super();
	}

	public Adherent(int identifiant, String motDePasse, String nomDeFamille,
			int prenom, String codePostal, String ville, String pays) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.nomDeFamille = nomDeFamille;
		this.prenom = prenom;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}
	
}
