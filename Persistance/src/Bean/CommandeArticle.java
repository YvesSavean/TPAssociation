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
	private Integer commande;
	
	@Id
	private String article;
	
	@Column(name="QUANTITE")
	@NotNull
	private int quantiteArticle;

	//getters et setters
	public Integer getCommande() {
		return commande;
	}

	public void setCommande(Integer commande) {
		this.commande = commande;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String string) {
		this.article = string;
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

	public CommandeArticle(Integer commande, String article,
			int quantiteArticle) {
		super();
		this.commande = commande;
		this.article = article;
		this.quantiteArticle = quantiteArticle;
	}

}
