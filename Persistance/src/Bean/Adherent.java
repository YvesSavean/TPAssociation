package Bean;

import java.util.HashMap;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="ADHERENT", schema="APP")
public class Adherent {
	
	@Id
	@NotBlank @Size(min=1, max=30)
	@Column(name="IDENTIFIANT")
	private String identifiant;
	
	@NotBlank @Size(min=1, max=20)
	@Column(name="MOTDEPASSE")
	private String motDePasse;
	
	@NotBlank @Size(min=1, max=30)
	@Column(name="NOMDEFAMIlLE")
	private String nomDeFamille;
	
	@NotBlank @Size(min=1, max=30)
	@Column(name="PRENOM")
	private String prenom;
	
	@Size(min=1, max=50)
	@Column(name="ADRESSE")
	private String Adresse;
	
	@Size(min=1, max=50)
	@Column(name="COMPLEMENTADRESSE")
	private String complementAdresse;
	
	@Size(max=5)
	@Column(name="CODEPOSTAL")
	private String codePostal;
	
	@Size(max=30)
	@Column(name="VILLE")
	private String ville;
	
	@Size(max=30)
	@Column(name="PAYS")
	private String pays;
	
	@OneToMany
	@JoinColumn(name = "COMMANDE")
	private HashMap<Integer,Commande> lesCommandes;
	
	public String getIdentifiant() {
		return identifiant;
	}

	public Adherent(String identifiant, String motDePasse, String nomDeFamille,
			String prenom, String adresse, String complementAdresse,
			String codePostal, String ville, String pays,
			HashMap<Integer, Commande> lesCommandes) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.nomDeFamille = nomDeFamille;
		this.prenom = prenom;
		Adresse = adresse;
		this.complementAdresse = complementAdresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
		this.lesCommandes = lesCommandes;
	}

	public HashMap<Integer, Commande> getLesCommandes() {
		return lesCommandes;
	}

	public void setLesCommandes(HashMap<Integer, Commande> lesCommandes) {
		this.lesCommandes = lesCommandes;
	}

	public void setIdentifiant(String identifiant) {
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

	public String getPrenom() {
		return prenom;
	}

	public String getAdresse() {
		return Adresse;
	}

	public void setAdresse(String adresse) {
		Adresse = adresse;
	}

	public String getComplementAdresse() {
		return complementAdresse;
	}

	public void setComplementAdresse(String complementAdresse) {
		this.complementAdresse = complementAdresse;
	}

	public void setPrenom(String prenom) {
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


	public Adherent() {
		super();
	}

	public Adherent(String identifiant, String motDePasse, String nomDeFamille,
			String prenom, String adresse, String complementAdresse,
			String codePostal, String ville, String pays) {
		super();
		this.identifiant = identifiant;
		this.motDePasse = motDePasse;
		this.nomDeFamille = nomDeFamille;
		this.prenom = prenom;
		Adresse = adresse;
		this.complementAdresse = complementAdresse;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}


	
}

