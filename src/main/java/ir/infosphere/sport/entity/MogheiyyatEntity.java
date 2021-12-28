package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "mogheiyyat")
public class MogheiyyatEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeMogheiyyat")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBakhsh", nullable = false)
	private BakhshEntity bakhsh;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeAks", nullable = true)
	private AksEntity aks;

	@Column(name = "NameMogheiyyat", nullable = false, length = 10)
	private String nameMogheiyyat;

	@Column(name = "Mokhtassat", nullable = false, length = 10000)
	private String mokhtassat;

	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal = false;

	@Column(name = "GheymateMogheiyyat", nullable = false)
	private Integer gheymat;

	@Column(name = "GheymateMogheiyyatBaKarteMelli", nullable = false)
	private Integer gheymateBaKarteMelli;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BakhshEntity getBakhsh() {
		return bakhsh;
	}

	public void setBakhsh(BakhshEntity bakhsh) {
		this.bakhsh = bakhsh;
	}

	public String getNameMogheiyyat() {
		return nameMogheiyyat;
	}

	public void setNameMogheiyyat(String nameMogheiyyat) {
		this.nameMogheiyyat = nameMogheiyyat;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
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

	public AksEntity getAks() {
		return aks;
	}

	public void setAks(AksEntity aks) {
		this.aks = aks;
	}

	public String getMokhtassat() {
		return mokhtassat;
	}

	public void setMokhtassat(String mokhtassat) {
		this.mokhtassat = mokhtassat;
	}

}
