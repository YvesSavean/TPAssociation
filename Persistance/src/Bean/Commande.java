package Bean;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="COMMANDE", schema="SONY")
public class Commande  implements Serializable{
	
	@Id
	@NotNull
	@Column(name="ID")
	private Integer id;
	
	@NotNull
	@Column(name="DATECOMMANDE")
	private Date dateCommande;
	
	@ManyToOne
	@JoinColumn(name = "IDENTIFIANT")
	private Adherent lAdherent;
	
	@CollectionTable(name="COMMANDE_ARTICLE",joinColumns=@JoinColumn(name="IDENTIFIANT")) 
	private Map<Integer,CommandeArticle> lesLignesArticles;
	
	public Commande(Integer id, Date dateCommande, Adherent lAdherent) {
		super();
		this.id = id;
		this.dateCommande = dateCommande;
		this.lAdherent = lAdherent;
	}

	public Map<Integer, CommandeArticle> getLesLignesArticles() {
		return lesLignesArticles;
	}

	public void setLesLignesArticles(Map<Integer, CommandeArticle> lesLignesArticles) {
		this.lesLignesArticles = lesLignesArticles;
	}
	
	public void ajoutLigne(CommandeArticle laLigne){
		this.lesLignesArticles.put(this.lesLignesArticles.size()+1, laLigne);
	}

	public Commande() {
		super();
		lesLignesArticles = new HashMap<Integer,CommandeArticle>();
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer i) {
		this.id = i;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Adherent getlAdherent() {
		return lAdherent;
	}

	public void setlAdherent(Adherent lAdherent) {
		this.lAdherent = lAdherent;
	}
}

