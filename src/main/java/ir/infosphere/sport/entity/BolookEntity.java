package ir.infosphere.sport.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "bolook")
public class BolookEntity extends BaseEntity<Short> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeBolook")
	private Short id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeAks", nullable = true)
	private AksEntity aks;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBakhsh", nullable = false)
	private BakhshEntity bakhsh;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMogheiyyat", nullable = true)
	private MogheiyyatEntity mogheiyyat;

	@Column(name = "GheyreFaal", nullable = true)
	private Boolean gheyreFaal = false;

	@Column(name = "ShomareyeBolook")
	private Short shomareyeBolook;

	@ManyToOne
	@JoinColumn(name = "MogheiyyateJoghrafiyayi", nullable = true)
	private KodEntity mogheiyyateJoghrafiyayi;
	
	@Column(name = "Mokhtassat", nullable = true, length = 700)
	private String mokhtassat;

	@OneToMany(mappedBy = "bolook")
	private List<RadifeBolookEntity> radifeBolookList;

	@OneToMany(mappedBy = "bolook")
	private List<VaziateBolookEntity> vaziateBolookList;

	// ////////////////////////

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public AksEntity getAks() {
		return aks;
	}

	public void setAks(AksEntity aks) {
		this.aks = aks;
	}

	public BakhshEntity getBakhsh() {
		return bakhsh;
	}

	public void setBakhsh(BakhshEntity bakhsh) {
		this.bakhsh = bakhsh;
	}

	public MogheiyyatEntity getMogheiyyat() {
		return mogheiyyat;
	}

	public void setMogheiyyat(MogheiyyatEntity mogheiyyat) {
		this.mogheiyyat = mogheiyyat;
	}

	public Short getShomareyeBolook() {
		return shomareyeBolook;
	}

	public void setShomareyeBolook(Short shomareyeBolook) {
		this.shomareyeBolook = shomareyeBolook;
	}

	public KodEntity getMogheiyyateJoghrafiyayi() {
		return mogheiyyateJoghrafiyayi;
	}

	public void setMogheiyyateJoghrafiyayi(KodEntity mogheiyyateJoghrafiyayi) {
		this.mogheiyyateJoghrafiyayi = mogheiyyateJoghrafiyayi;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public List<RadifeBolookEntity> getRadifeBolookList() {
		return radifeBolookList;
	}
	
	public void setRadifeBolookList(List<RadifeBolookEntity> radifeBolookList) {
		this.radifeBolookList = radifeBolookList;
	}


	public List<VaziateBolookEntity> getVaziateBolookList() {
		return vaziateBolookList;
	}

	public void setVaziateBolookList(List<VaziateBolookEntity> vaziateBolookList) {
		this.vaziateBolookList = vaziateBolookList;
	}

	public String getMokhtassat() {
		return mokhtassat;
	}

	public void setMokhtassat(String mokhtassat) {
		this.mokhtassat = mokhtassat;
	}

}
