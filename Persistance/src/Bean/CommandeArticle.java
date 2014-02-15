package Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(CommandeArticleId.class)
@Table(name="COMMANDE_ARTICLE", schema="SONY")
public class CommandeArticle {
	//attributs
	@Id
	@ManyToOne
	@JoinColumn(name="ID")
	private Commande commande;
	
	@Id
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
