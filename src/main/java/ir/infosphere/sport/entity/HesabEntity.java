package ir.infosphere.sport.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="hesab")
public class HesabEntity extends BaseEntity<Short> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeHesab")
	private Short id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeTarafeGharardad", nullable = false)
	private TarafeGharardadEntity tarafeGharardad;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;
	
	@Column(name = "SahmeOzviyat", nullable = false)
	private BigDecimal sahmeOzviyat;
	
	@Column(name = "SahmeRiali", nullable = false)
	private BigDecimal sahmeRiali;
	
	@Column(name = "SahmeSharj", nullable = false)
	private BigDecimal sahmeSharj;
	
	@Column(name = "SahmeBelit", nullable = false)
	private BigDecimal sahmeBelit;
	
	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal = false;
	
	@Column(name = "ShomareyeHesab", length = 50, nullable = false)
	private String shomareyeHesab;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public TarafeGharardadEntity getTarafeGharardad() {
		return tarafeGharardad;
	}

	public void setTarafeGharardad(TarafeGharardadEntity tarafeGharardad) {
		this.tarafeGharardad = tarafeGharardad;
	}

	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}


	public BigDecimal getSahmeOzviyat() {
		return sahmeOzviyat;
	}

	public void setSahmeOzviyat(BigDecimal sahmeOzviyat) {
		this.sahmeOzviyat = sahmeOzviyat;
	}

	public BigDecimal getSahmeRiali() {
		return sahmeRiali;
	}

	public void setSahmeRiali(BigDecimal sahmeRiali) {
		this.sahmeRiali = sahmeRiali;
	}

	public BigDecimal getSahmeSharj() {
		return sahmeSharj;
	}

	public void setSahmeSharj(BigDecimal sahmeSharj) {
		this.sahmeSharj = sahmeSharj;
	}

	public BigDecimal getSahmeBelit() {
		return sahmeBelit;
	}

	public void setSahmeBelit(BigDecimal sahmeBelit) {
		this.sahmeBelit = sahmeBelit;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public String getShomareyeHesab() {
		return shomareyeHesab;
	}

	public void setShomareyeHesab(String shomareyeHesab) {
		this.shomareyeHesab = shomareyeHesab;
	}

	
}
