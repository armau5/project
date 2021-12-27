package ir.infosphere.sport.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "ozv")
public class OzvEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeOzv")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeNahiye")
	private NahiyeEntity nahiye;

	@OneToOne
	@JoinColumn(name = "ShenaseyeAks")
	private AksEntity aks;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal")
	private PortalEntity portal;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKeshvar")
	private KodEntity keshvar;

	@ManyToOne
	@JoinColumn(name = "NoeOzv", nullable = false)
	private KodEntity noeOzv;

	@Column(name = "Nam", length = 64)
	private String name;

	@Column(name = "Famil", length = 64)
	private String famil;

	@Column(name = "KodeMeli", length = 11)
	private String kodeMeli;

	@Column(name = "ShomareyeGozarname", length = 20)
	private String shomareyeGozarname;

	@Column(name = "ShomareyeHamrah", length = 14)
	private String shomareyeHamrah;

	@Column(name = "ShomareyeSabet", length = 14)
	private String shomareyeSabet;

	@Column(name = "KodePosti", length = 11)
	private String kodePosti;

	@Column(name = "Adres", length = 255)
	private String adres;

	@Column(name = "PosteElectronik", length = 64)
	private String posteElectronik;

	@ManyToOne
	@JoinColumn(name = "Jensiat")
	private KodEntity jensiat;

	@Column(name = "TarikheTavallod")
	private Date tarikheTavallod;

	@Column(name = "NameKarbari", length = 45, nullable = false)
	private String nameKarbari;

	@Column(name = "HasheRamzeOboor", length = 40, nullable = false)
	private String hasheRamzeOboor;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeSoaleAmniati", nullable = false)
	private SoaleAmniatiEntity soaleAmniati;

	@Column(name = "HashePasokheSoaleAmniati", length = 40, nullable = false)
	private String hashePasokheSoaleAmniati;

	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal = false;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTimeMoredeAlaghe")
	private TimEntity timeMoredeAlaghe;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeReshteyeVarzeshiyeMoredeAlaghe")
	private ReshteyeVarzeshiEntity reshteyeVarzeshiyeMoredeAlaghe;

	@ManyToOne
	@JoinColumn(name = "TaeedeAks")
	private KodEntity taeedeAks;

	@Column(name = "DalileRadKardan")
	private String dalileRadkardan;

	@Column(name = "TarikheSabt")
	private Date tarikheSabt;

	@OneToMany(mappedBy = "ozvEntity")
	private List<KartEntity> karts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NahiyeEntity getNahiye() {
		return nahiye;
	}

	public void setNahiye(NahiyeEntity nahiye) {
		this.nahiye = nahiye;
	}

	public AksEntity getAks() {
		return aks;
	}

	public void setAks(AksEntity aks) {
		this.aks = aks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamil() {
		return famil;
	}

	public void setFamil(String famil) {
		this.famil = famil;
	}

	public String getKodeMeli() {
		return kodeMeli;
	}

	public void setKodeMeli(String kodeMeli) {
		this.kodeMeli = kodeMeli;
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

	public String getKodePosti() {
		return kodePosti;
	}

	public void setKodePosti(String kodePosti) {
		this.kodePosti = kodePosti;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getPosteElectronik() {
		return posteElectronik;
	}

	public void setPosteElectronik(String posteElectronik) {
		this.posteElectronik = posteElectronik;
	}

	public KodEntity getJensiat() {
		return jensiat;
	}

	public void setJensiat(KodEntity jensiat) {
		this.jensiat = jensiat;
	}

	public Date getTarikheTavallod() {
		return tarikheTavallod;
	}

	public void setTarikheTavallod(Date tarikheTavallod) {
		this.tarikheTavallod = tarikheTavallod;
	}

	public String getNameKarbari() {
		return nameKarbari;
	}

	public void setNameKarbari(String nameKarbari) {
		this.nameKarbari = nameKarbari;
	}

	public ReshteyeVarzeshiEntity getReshteyeVarzeshiyeMoredeAlaghe() {
		return reshteyeVarzeshiyeMoredeAlaghe;
	}

	public void setReshteyeVarzeshiyeMoredeAlaghe(
			ReshteyeVarzeshiEntity reshteyeVarzeshiyeMoredeAlaghe) {
		this.reshteyeVarzeshiyeMoredeAlaghe = reshteyeVarzeshiyeMoredeAlaghe;
	}

	public List<KartEntity> getKarts() {
		return karts;
	}

	public void setKarts(List<KartEntity> karts) {
		this.karts = karts;
	}

	public KodEntity getTaeedeAks() {
		return taeedeAks;
	}

	public void setTaeedeAks(KodEntity taeedeAks) {
		this.taeedeAks = taeedeAks;
	}

	public KodEntity getNoeOzv() {
		return noeOzv;
	}

	public void setNoeOzv(KodEntity noeOzv) {
		this.noeOzv = noeOzv;
	}

	public String getHasheRamzeOboor() {
		return hasheRamzeOboor;
	}

	public void setHasheRamzeOboor(String hasheRamzeOboor) {
		this.hasheRamzeOboor = hasheRamzeOboor;
	}

	public SoaleAmniatiEntity getSoaleAmniati() {
		return soaleAmniati;
	}

	public void setSoaleAmniati(SoaleAmniatiEntity soaleAmniati) {
		this.soaleAmniati = soaleAmniati;
	}

	public String getHashePasokheSoaleAmniati() {
		return hashePasokheSoaleAmniati;
	}

	public void setHashePasokheSoaleAmniati(String hashePasokheSoaleAmniati) {
		this.hashePasokheSoaleAmniati = hashePasokheSoaleAmniati;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public String getShomareyeGozarname() {
		return shomareyeGozarname;
	}

	public void setShomareyeGozarname(String shomareyeGozarname) {
		this.shomareyeGozarname = shomareyeGozarname;
	}

	public TimEntity getTimeMoredeAlaghe() {
		return timeMoredeAlaghe;
	}

	public void setTimeMoredeAlaghe(TimEntity timeMoredeAlaghe) {
		this.timeMoredeAlaghe = timeMoredeAlaghe;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public KodEntity getKeshvar() {
		return keshvar;
	}

	public void setKeshvar(KodEntity keshvar) {
		this.keshvar = keshvar;
	}

	public String getDalileRadkardan() {
		return dalileRadkardan;
	}

	public void setDalileRadkardan(String dalileRadkardan) {
		this.dalileRadkardan = dalileRadkardan;
	}

	public Date getTarikheSabt() {
		return tarikheSabt;
	}

	public void setTarikheSabt(Date tarikheSabt) {
		this.tarikheSabt = tarikheSabt;
	}
}
