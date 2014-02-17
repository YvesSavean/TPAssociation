package Bean;

import java.io.Serializable;

import javax.persistence.Column;

public class CommandeArticleId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3008166735944175797L;
	
	@Column(name="ID")
	private Integer commande;
	
	@Column(name="CODE")
	private String article;

	public CommandeArticleId() {

	}

	public CommandeArticleId(Integer laCommande, String lArticle) {
		commande = laCommande;
		article = lArticle;
	}

	public Integer getCommande() {
		return commande;
	}

	public void setCommande(Integer commande) {
		this.commande = commande;
	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}
	

    // Must have a hashCode method
    @Override
    public int hashCode() {
        return commande + (Integer)article.hashCode();
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
