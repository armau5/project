package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="pozehesab")
public class PozeHesabEntity extends BaseEntity<Integer> {
	

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyePozeHesab")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeHesab")
	private HesabEntity hesab;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePoz")
	private PozEntity poz;
	/*
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal")
	private PortalEntity portal;
	*/
	public KodEntity getKarbari() {
		return karbari;
	}

	public void setKarbari(KodEntity karbari) {
		this.karbari = karbari;
	}

	@Column(name = "TarikheTahvil")
	private Date tarikheTahvil;
	
	@ManyToOne
	@JoinColumn(name = "KarbariyePoz")
	private KodEntity karbari;
	
	@Column(name = "TarikheOdat")
	private Date tarikheOdat;
	
	@Column(name = "NameTahvilGirandeh", length = 45)
	private String nameTahvilGirandeh;

	@Column(name = "FamileTahvilGirandeh", length = 45)
	private String famileTahvilGirandeh;
	
	@Column(name = "ShomareyeSabet", length = 14)
	private String shomareyeSabet;
	
	@Column(name = "ShomareyeHamrah", length = 14)
	private String shomareyeHamrah;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public HesabEntity getHesab() {
		return hesab;
	}

	public void setHesab(HesabEntity hesab) {
		this.hesab = hesab;
	}

	public PozEntity getPoz() {
		return poz;
	}

	public void setPoz(PozEntity poz) {
		this.poz = poz;
	}

	

	public Date getTarikheTahvil() {
		return tarikheTahvil;
	}

	public void setTarikheTahvil(Date tarikheTahvil) {
		this.tarikheTahvil = tarikheTahvil;
	}

	public Date getTarikheOdat() {
		return tarikheOdat;
	}

	public void setTarikheOdat(Date tarikheOdat) {
		this.tarikheOdat = tarikheOdat;
	}

	public String getNameTahvilGirandeh() {
		return nameTahvilGirandeh;
	}

	public void setNameTahvilGirandeh(String nameTahvilGirandeh) {
		this.nameTahvilGirandeh = nameTahvilGirandeh;
	}

	public String getFamileTahvilGirandeh() {
		return famileTahvilGirandeh;
	}

	public void setFamileTahvilGirandeh(String famileTahvilGirandeh) {
		this.famileTahvilGirandeh = famileTahvilGirandeh;
	}

	public String getShomareyeSabet() {
		return shomareyeSabet;
	}

	public void setShomareyeSabet(String shomareyeSabet) {
		this.shomareyeSabet = shomareyeSabet;
	}

	public String getShomareyeHamrah() {
		return shomareyeHamrah;
	}

	public void setShomareyeHamrah(String shomareyeHamrah) {
		this.shomareyeHamrah = shomareyeHamrah;
	}
	/*
	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}*/
}
