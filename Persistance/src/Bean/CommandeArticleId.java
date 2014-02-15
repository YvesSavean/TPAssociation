package Bean;

import java.io.Serializable;

public class CommandeArticleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3008166735944175797L;
	private Commande commande;
	private Article article;

	public CommandeArticleId() {

	}

	public CommandeArticleId(Commande laCommande, Article lArticle) {
		commande = laCommande;
		article = lArticle;
	}

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

}
