package Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="ARTICLE", schema="SONY")
public class Article {
	@Id
	@NotBlank @Size(min=1, max=10)
	@Column(name="CODE")
	private String code;
	
	@NotBlank @Size(min=1, max=30)
	@Column(name="NOM")
	private String nom;
	
	@NotBlank @Size(min=1, max=30)
	@Column(name="PRIX")
	private int prix;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public Article() {
	}

}
