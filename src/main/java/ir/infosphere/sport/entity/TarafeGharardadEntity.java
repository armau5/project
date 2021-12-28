package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="tarafegharardad")
public class TarafeGharardadEntity extends BaseEntity<Short> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTarafeGharardad")
	private Short id;
	

	@Column(name = "Nam", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeNahiye", nullable = false)
	private NahiyeEntity nahiye;
	
	
	@Column(name = "Adres" , nullable = false )
	private String adres;

	
	@ManyToOne
	@JoinColumn(name = "NoeFaaliat", nullable = false)
	private KodEntity noeFaaliat;
	
	
	@Column(name = "ShomareyeHamrah", nullable = false)
	private String shomareyeHamrah;
	
	
	@Column(name = "ShomareyeSabet", nullable = false)
	private String shomareyeSabet;
	
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeBakhsh", nullable = false)
	private BakhshEntity bakhsh;
	
	
	public BakhshEntity getBakhsh() {
		return bakhsh;
	}


	public void setBakhsh(BakhshEntity bakhsh) {
		this.bakhsh = bakhsh;
	}


	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;
	
	
	@Column(name = "NameSahebeHesab", nullable = false)
	private String nameSahebeHesab;
	
	
	@Column(name = "ShomareyeHesab", nullable = true)
	private String shomareyeHesab;
	
	
	@ManyToOne
	@JoinColumn(name = "NameBank", nullable = true)
	private KodEntity nameBank;
	
	
	@Column(name = "ShomareyeKart", nullable = true)
	private String shomareyeKart;
	
	
	@Column(name = "NameShobe", nullable = true)
	private String nameShobe;
	
	
	@Column(name = "KodeShobe", nullable = true)
	private String kodeShobe;
	
	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}


	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}


	@Column(name = "GheyreFaal", nullable = false)
	private Boolean gheyreFaal;
	
	public Short getId() {
		return id;
	}


	public KodEntity getNoeFaaliat() {
		return noeFaaliat;
	}


	public void setNoeFaaliat(KodEntity noeFaaliat) {
		this.noeFaaliat = noeFaaliat;
	}


	public String getShomareyeHamrah() {
		return shomareyeHamrah;
	}


	public void setShomareyeHamrah(String shomareyeHamrah) {
		this.shomareyeHamrah = shomareyeHamrah;
	}


	public String getShomareyeSabet() {
		return shomareyeSabet;
	}


	public void setShomareyeSabet(String shomareyeSabet) {
		this.shomareyeSabet = shomareyeSabet;
	}

	public OzvEntity getOzv() {
		return ozv;
	}


	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}


	public String getNameSahebeHesab() {
		return nameSahebeHesab;
	}


	public void setNameSahebeHesab(String nameSahebeHesab) {
		this.nameSahebeHesab = nameSahebeHesab;
	}


	public String getShomareyeHesab() {
		return shomareyeHesab;
	}


	public void setShomareyeHesab(String shomareyeHesab) {
		this.shomareyeHesab = shomareyeHesab;
	}


	public KodEntity getNameBank() {
		return nameBank;
	}


	public void setNameBank(KodEntity nameBank) {
		this.nameBank = nameBank;
	}


	public String getShomareyeKart() {
		return shomareyeKart;
	}


	public void setShomareyeKart(String shomareyeKart) {
		this.shomareyeKart = shomareyeKart;
	}


	public String getNameShobe() {
		return nameShobe;
	}


	public void setNameShobe(String nameShobe) {
		this.nameShobe = nameShobe;
	}


	public String getKodeShobe() {
		return kodeShobe;
	}


	public void setKodeShobe(String kodeShobe) {
		this.kodeShobe = kodeShobe;
	}


	public void setId(Short id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAdres() {
		return adres;
	}


	public void setAdres(String adres) {
		this.adres = adres;
	}


	public NahiyeEntity getNahiye() {
		return nahiye;
	}


	public void setNahiye(NahiyeEntity shenaseyeNahiye) {
		this.nahiye = shenaseyeNahiye;
	}


	
}
