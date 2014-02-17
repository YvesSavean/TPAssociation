package Bean;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="COMMANDE_ARTICLE", schema="SONY")
public class CommandeArticle{
	//attributs

	@EmbeddedId
	private CommandeArticleId Key;
	
	@Column(name="QUANTITE")
	@NotNull
	private int quantiteArticle;


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

	public CommandeArticleId getKey() {
		return Key;
	}

	public void setKey(CommandeArticleId key) {
		Key = key;
	}
	
	
}
