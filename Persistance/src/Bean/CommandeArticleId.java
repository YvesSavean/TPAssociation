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
	

    // Must have a hashCode method
    @Override
    public int hashCode() {
        return commande.hashCode() + article.hashCode();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CommandeArticleId other = (CommandeArticleId) obj;
        if (commande == null)
        {
            if (other.commande != null)
                return false;
        }
        else if (!commande.equals(other.commande))
            return false;
        if (article == null)
        {
            if (other.article != null)
                return false;
        }
        else if (!article.equals(other.article))
            return false;
        return true;
    }

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
