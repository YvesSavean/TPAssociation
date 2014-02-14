package Bean;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class CommandeArticle {
	//attributs
	@ManyToOne
	@JoinColumn(name="ID")
	private Commande commande;
	
	@ManyToOne
	@JoinColumn(name="CODE")
	private Article article;
	
	@Column(name="QUANTITE")
	@NotNull
	private int quantiteArticle;

	//getters et setters
	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public int getQuantiteArticle() {
		return quantiteArticle;
	}

	public void setQuantiteArticle(int quantiteArticle) {
		this.quantiteArticle = quantiteArticle;
	}
	
	//constructeurs
	public CommandeArticle() {
		super();
	}

	public CommandeArticle(Commande commande, Article article,
			int quantiteArticle) {
		super();
		this.commande = commande;
		this.article = article;
		this.quantiteArticle = quantiteArticle;
	}

}
