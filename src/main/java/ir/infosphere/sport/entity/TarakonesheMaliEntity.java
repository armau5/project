package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "TarakonesheMali")
public class TarakonesheMaliEntity extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTarakonesheMali")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv")
	private OzvEntity ozv;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBelit")
	private BelitEntity belit;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKart")
	private KartEntity kart;

	@Column(name = "ShenaseyeOzviyateService")
	private Integer shenaseyeOzviyat;

	// code by Hesabgar
	// 19 khordad 1392

	@Column(name = "ShenaseyePoz", nullable = true)
	private Integer serialePoz;

	// @ManyToOne
	// @JoinColumn(name = "ShenaseyeTarafeGharardad", nullable = false)
	// private TarafeGharardadEntity shenaseyeTarafeGharardad;

	@Column(name = "Zaman", nullable = false)
	private Date zaman;

	@ManyToOne
	@JoinColumn(name = "NahveyeKharid", nullable = false)
	private KodEntity nahveyeKharid;

	@Column(name = "Mablagh", nullable = false)
	private Long mablagh;

	@ManyToOne
	@JoinColumn(name = "NoeTarakonesh", nullable = false)
	private KodEntity noeTarakonesh;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeLogeKarteBaje", nullable = true)
	private LogeKarteBajeEntity shenaseyeLogeKarteBaje;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeHesab", nullable = true)
	private HesabEntity hesab;

	// @ManyToOne
	// @JoinColumn(name = "ShenaseyeTarafeGharardad", nullable = false)
	// private TarafeGharardadEntity tarafeGharardad;

	// ////////////////////////

	public HesabEntity getHesab() {
		return hesab;
	}

	public void setHesab(HesabEntity hesab) {
		this.hesab = hesab;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}

	public BelitEntity getBelit() {
		return belit;
	}

	public void setBelit(BelitEntity belit) {
		this.belit = belit;
	}

	public KartEntity getKart() {
		return kart;
	}

	public void setKart(KartEntity kart) {
		this.kart = kart;
	}

	public Integer getShenaseyeOzviyat() {
		return shenaseyeOzviyat;
	}

	public void setShenaseyeOzviyat(Integer shenaseyeOzviyat) {
		this.shenaseyeOzviyat = shenaseyeOzviyat;
	}

	// public Short getShenaseyeTarafeGharardad() {
	// return tarafeGharardad;
	// }
	//
	// public void setShenaseyeTarafeGharardad(Short tarafeGharardad) {
	// this.tarafeGharardad = tarafeGharardad;
	// }

	public Date getZaman() {
		return zaman;
	}

	public void setZaman(Date zaman) {
		this.zaman = zaman;
	}

	public KodEntity getNahveyeKharid() {
		return nahveyeKharid;
	}

	public void setNahveyeKharid(KodEntity nahveyeKharid) {
		this.nahveyeKharid = nahveyeKharid;
	}

	public Long getMablagh() {
		return mablagh;
	}

	public void setMablagh(Long mablagh) {
		this.mablagh = mablagh;
	}

	public KodEntity getNoeTarakonesh() {
		return noeTarakonesh;
	}

	public void setNoeTarakonesh(KodEntity noeTarakonesh) {
		this.noeTarakonesh = noeTarakonesh;
	}

	// public TarafeGharardadEntity getTarafeGharardad() {
	// return tarafeGharardad;
	// }
	//
	// public void setTarafeGharardad(TarafeGharardadEntity tarafeGharardad) {
	// this.tarafeGharardad = tarafeGharardad;
	// }

	// code by Hesabgar
	// 19 khordad 1392
	public Integer getPoz() {
		return serialePoz;
	}

	public void setPoz(Integer serialePoz) {
		this.serialePoz = serialePoz;
	}

	public LogeKarteBajeEntity getShenaseyeLogeKarteBaje() {
		return shenaseyeLogeKarteBaje;
	}

	public void setShenaseyeLogeKarteBaje(
			LogeKarteBajeEntity shenaseyeLogeKarteBaje) {
		this.shenaseyeLogeKarteBaje = shenaseyeLogeKarteBaje;
	}

}
