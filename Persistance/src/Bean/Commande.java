package Bean;

import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="COMMANDE", schema="SONY")
public class Commande {
	
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
	
	public Commande(Integer id, Date dateCommande, Adherent lAdherent) {
		super();
		this.id = id;
		this.dateCommande = dateCommande;
		this.lAdherent = lAdherent;
	}

	public Commande() {
		super();
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

