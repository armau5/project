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

@Entity(name = "radifebolook")
public class RadifeBolookEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeRadifeBolook")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBolook", nullable = false)
	private BolookEntity bolook;

	@Column(name = "ShomareyeSandaliAz", nullable = false)
	private Byte shomareyeSandaliAz;

	@Column(name = "ShomareyeSandaliTa", nullable = false)
	private Byte ShomareyeSandaliTa;

	@Column(name = "ShomareyeRadif", nullable = false, length = 3)
	private String shomareyeRadif;

	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal = false;

	@OneToMany(mappedBy = "radifeBolook")
	private List<VaziateRadifeBolookEntity> vaziateRadifeBolookList;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BolookEntity getBolook() {
		return bolook;
	}

	public void setBolook(BolookEntity bolook) {
		this.bolook = bolook;
	}

	public Byte getShomareyeSandaliAz() {
		return shomareyeSandaliAz;
	}

	public void setShomareyeSandaliAz(Byte shomareyeSandaliAz) {
		this.shomareyeSandaliAz = shomareyeSandaliAz;
	}

	public Byte getShomareyeSandaliTa() {
		return ShomareyeSandaliTa;
	}

	public void setShomareyeSandaliTa(Byte shomareyeSandaliTa) {
		ShomareyeSandaliTa = shomareyeSandaliTa;
	}

	public String getShomareyeRadif() {
		return shomareyeRadif;
	}

	public void setShomareyeRadif(String shomareyeRadif) {
		this.shomareyeRadif = shomareyeRadif;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public List<VaziateRadifeBolookEntity> getVaziateRadifeBolookList() {
		return vaziateRadifeBolookList;
	}

	public void setVaziateRadifeBolookList(List<VaziateRadifeBolookEntity> vaziateRadifeBolookList) {
		this.vaziateRadifeBolookList = vaziateRadifeBolookList;
	}

}
