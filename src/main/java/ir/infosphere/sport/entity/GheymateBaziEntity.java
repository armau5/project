package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "gheymatebazi")
public class GheymateBaziEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeGheymateBazi")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMogheiyyat", nullable = false)
	private MogheiyyatEntity mogheiyyat;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBazi", nullable = false)
	private BaziEntity bazi;

	@Column(name = "GheymateMogheiyyat", nullable = false)
	private Integer gheymat;
	
	@Column(name = "GheymateMogheiyyatBaKarteMelli", nullable = false)
	private Integer gheymateBaKarteMelli;

	@Column(name = "ForooshBeBolookFaalAst", nullable = false)
	private Boolean forooshBeBolookFaalAst;

	@Column(name = "ForooshBaKarteMelliFaalAst", nullable = false)
	private Boolean forooshBaKarteMelliFaalAst;

	@Column(name = "ForooshBaKarteOzviyyatFaalAst", nullable = false)
	private Boolean forooshBaKarteOzviyyatFaalAst;
	
	@Column(name = "SandalieBaghimande", nullable = true)
	private Integer sandalieBaghimande;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MogheiyyatEntity getMogheiyyat() {
		return mogheiyyat;
	}

	public void setMogheiyyat(MogheiyyatEntity mogheiyyat) {
		this.mogheiyyat = mogheiyyat;
	}

	public BaziEntity getBazi() {
		return bazi;
	}

	public void setBazi(BaziEntity bazi) {
		this.bazi = bazi;
	}

	public Integer getGheymat() {
		return gheymat;
	}

	public void setGheymat(Integer gheymat) {
		this.gheymat = gheymat;
	}

	public Integer getGheymateBaKarteMelli() {
		return gheymateBaKarteMelli;
	}

	public void setGheymateBaKarteMelli(Integer gheymateBaKarteMelli) {
		this.gheymateBaKarteMelli = gheymateBaKarteMelli;
	}

	public Boolean getForooshBeBolookFaalAst() {
		return forooshBeBolookFaalAst;
	}

	public void setForooshBeBolookFaalAst(Boolean forooshBeBolookFaalAst) {
		this.forooshBeBolookFaalAst = forooshBeBolookFaalAst;
	}

	public Boolean getForooshBaKarteMelliFaalAst() {
		return forooshBaKarteMelliFaalAst;
	}

	public void setForooshBaKarteMelliFaalAst(Boolean forooshBaKarteMelliFaalAst) {
		this.forooshBaKarteMelliFaalAst = forooshBaKarteMelliFaalAst;
	}

	public Boolean getForooshBaKarteOzviyyatFaalAst() {
		return forooshBaKarteOzviyyatFaalAst;
	}
	
	

	public Integer getSandalieBaghimande() {
		return sandalieBaghimande;
	}

	public void setSandalieBaghimande(Integer sandalieBaghimande) {
		this.sandalieBaghimande = sandalieBaghimande;
	}

	public void setForooshBaKarteOzviyyatFaalAst(
			Boolean forooshBaKarteOzviyyatFaalAst) {
		this.forooshBaKarteOzviyyatFaalAst = forooshBaKarteOzviyyatFaalAst;
	}


}
