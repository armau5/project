package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "vaziatesandali")
public class VaziateSandaliEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeVaziateSandali")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeRadifeBolook", nullable = false)
	private RadifeBolookEntity shenaseyeRadifeBolook;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBolook", nullable = false)
	private BolookEntity bolook;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMogheiyyat", nullable = false)
	private MogheiyyatEntity mogheiyyat;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBazi", nullable = false)
	private BaziEntity shenaseyeBazi;

	@Column(name = "ShomareyeSandali", nullable = false)
	private Byte shomareyeSandali;

	@ManyToOne
	@JoinColumn(name = "VaziateSandali", nullable = false)
	private KodEntity vaziateSandali;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeDarkhasteBeliteSazmani", nullable = true)
	private DarkhasteBeliteSazmaniEntity darkhasteBeliteSazmani;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKart", nullable = true)
	private KartEntity kart;

	@Column(name = "ShomareyeMelli", nullable = true, length = 11)
	private String shomareyeMelli;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKarbareKharidar", nullable = true)
	private OzvEntity karbareKharidar;

	@Column(name = "IsMizban", nullable = false)
	private Boolean isMizban = true;

	@Column(name = "Gheymat", nullable = false)
	private Integer gheymat;

	@Column(name = "GheymatBaKarteMelli", nullable = false)
	private Integer gheymatBaKarteMelli;

	@Column(name = "TarikheReserve", nullable = true)
	private Date tarikheReserve;

	@Column(name = "Synced", nullable = false)
	private Boolean synced;

	@Column(name = "Havadar")
	private Boolean havadar;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RadifeBolookEntity getShenaseyeRadifeBolook() {
		return shenaseyeRadifeBolook;
	}

	public void setShenaseyeRadifeBolook(
			RadifeBolookEntity shenaseyeRadifeBolook) {
		this.shenaseyeRadifeBolook = shenaseyeRadifeBolook;
	}

	public BaziEntity getShenaseyeBazi() {
		return shenaseyeBazi;
	}

	public void setShenaseyeBazi(BaziEntity shenaseyeBazi) {
		this.shenaseyeBazi = shenaseyeBazi;
	}

	public Byte getShomareyeSandali() {
		return shomareyeSandali;
	}

	public void setShomareyeSandali(Byte shomareyeSandali) {
		this.shomareyeSandali = shomareyeSandali;
	}

	public KodEntity getVaziateSandali() {
		return vaziateSandali;
	}

	public void setVaziateSandali(KodEntity vaziateSandali) {
		this.vaziateSandali = vaziateSandali;
	}

	public DarkhasteBeliteSazmaniEntity getDarkhasteBeliteSazmani() {
		return darkhasteBeliteSazmani;
	}

	public void setDarkhasteBeliteSazmani(
			DarkhasteBeliteSazmaniEntity darkhasteBeliteSazmani) {
		this.darkhasteBeliteSazmani = darkhasteBeliteSazmani;
	}

	public KartEntity getKart() {
		return kart;
	}

	public void setKart(KartEntity kart) {
		this.kart = kart;
	}

	public String getShomareyeMelli() {
		return shomareyeMelli;
	}

	public void setShomareyeMelli(String shomareyeMelli) {
		this.shomareyeMelli = shomareyeMelli;
	}

	public OzvEntity getKarbareKharidar() {
		return karbareKharidar;
	}

	public void setKarbareKharidar(OzvEntity karbareKharidar) {
		this.karbareKharidar = karbareKharidar;
	}

	public Boolean getIsMizban() {
		return isMizban;
	}

	public void setIsMizban(Boolean isMizban) {
		this.isMizban = isMizban;
	}

	public Integer getGheymat() {
		return gheymat;
	}

	public void setGheymat(Integer gheymat) {
		this.gheymat = gheymat;
	}

	public Integer getGheymatBaKarteMelli() {
		return gheymatBaKarteMelli;
	}

	public void setGheymatBaKarteMelli(Integer gheymatBaKarteMelli) {
		this.gheymatBaKarteMelli = gheymatBaKarteMelli;
	}

	public void setTarikheReserve(Date tarikheReserve) {
		this.tarikheReserve = tarikheReserve;
	}

	public Date getTarikheReserve() {
		return tarikheReserve;
	}

	@Override
	public String toString() {
		return this.id + " " + this.karbareKharidar;
	}

	public BolookEntity getBolook() {
		return bolook;
	}

	public void setBolook(BolookEntity bolook) {
		this.bolook = bolook;
	}

	public MogheiyyatEntity getMogheiyyat() {
		return mogheiyyat;
	}

	public void setMogheiyyat(MogheiyyatEntity mogheiyyat) {
		this.mogheiyyat = mogheiyyat;
	}

	public Boolean getSynced() {
		return synced;
	}

	public void setSynced(Boolean synced) {
		this.synced = synced;
	}

	public Boolean getHavadar() {
		return havadar;
	}

	public void setHavadar(Boolean havadar) {
		this.havadar = havadar;
	}

}
