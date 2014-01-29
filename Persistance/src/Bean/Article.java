package Bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="ARTICLE", schema="APP")
public class Article {
	@Id
	@NotBlank @Size(min=1, max=10)
	@Column(name="CODE")
	private Integer code;
	
	@NotBlank @Size(min=1, max=30)
	@Column(name="NOM")
	private Integer nom;
	
}
