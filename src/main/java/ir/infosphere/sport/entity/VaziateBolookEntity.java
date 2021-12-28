package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "vaziatebolook")
public class VaziateBolookEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeVaziateBolook")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBazi", nullable = false)
	private BaziEntity bazi;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBolook", nullable = false)
	private BolookEntity bolook;

	@ManyToOne
	@JoinColumn(name = "VaziateBolook", nullable = false)
	private KodEntity vaziateBolook;

	@Column(name = "SandalieBaghimande", nullable = true)
	private Integer sandalieBaghimande;

	@Column(name = "ForooshBeSandaliFaalAst", nullable = false)
	private Boolean forooshBeSandaliFaalAst;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BaziEntity getBazi() {
		return bazi;
	}

	public void setBazi(BaziEntity bazi) {
		this.bazi = bazi;
	}

	public BolookEntity getBolook() {
		return bolook;
	}

	public void setBolook(BolookEntity bolook) {
		this.bolook = bolook;
	}

	public KodEntity getVaziateBolook() {
		return vaziateBolook;
	}

	public void setVaziateBolook(KodEntity baziateBolook) {
		this.vaziateBolook = baziateBolook;
	}

	public Integer getSandalieBaghimande() {
		return sandalieBaghimande;
	}

	public void setSandalieBaghimande(Integer sandalieBaghimande) {
		this.sandalieBaghimande = sandalieBaghimande;
	}

	public Boolean getForooshBeSandaliFaalAst() {
		return forooshBeSandaliFaalAst;
	}

	public void setForooshBeSandaliFaalAst(Boolean forooshBeSandaliFaalAst) {
		this.forooshBeSandaliFaalAst = forooshBeSandaliFaalAst;
	}

}
