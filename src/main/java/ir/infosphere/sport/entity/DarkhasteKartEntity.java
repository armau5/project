package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "darkhastekart")
public class DarkhasteKartEntity extends BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeDarkhasteKart")
	private Long id;

	@ManyToOne(optional = true)
	@JoinColumn(name = "ShenaseyeOzv")
	private OzvEntity ozv;

	@ManyToOne
	@JoinColumn(name = "NahveyeTahvil")
	private KodEntity nahveyeTahvil;

	@Column(name = "PUK", length = 8)
	private String puk;

	@Column(name = "PIN", length = 4)
	private String pin;

	@Column(name = "ZamaneDarkhast")
	private Date zamaneDarkhast;
	
	@OneToOne
	@JoinColumn(name = "ShenaseyeKart")
	private KartEntity kart;

	@ManyToOne
	@JoinColumn(name = "MarhaleyeSodoor")
	private KodEntity marhaleyeSodoor;

	@ManyToOne
	@JoinColumn(name = "VaziateMarhale")
	private KodEntity vaziateMarhale;

	@ManyToOne
	@JoinColumn(name = "OnvaneKhata")
	private KodEntity onvaneKhata;

	@ManyToOne
	@JoinColumn(name = "portalID", columnDefinition = "INT(11) UNSIGNED")
	private PortalEntity sazman;

	@Column(name = "SharheKhata", length = 64)
	private String sharheKhata;

	@Column(name = "BayganiShode")
	private Boolean bayganiShode;
	
	@Column(name = "GhoflePrint")
	private Boolean ghoflePrint;

	@Column(name = "ShomareyeMarsooleh", length = 20, nullable = true)
	private String shomareyeMarsooleh;

	@Column(name = "DadeyeEzafi", length = 200, nullable = true)
	private String dadeyeEzafi;
	
	@Column(name = "KarbareSodoor", nullable = true)
	private Integer karbareSodoor;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePishkhan")
	private PishkhanEntity pishkhan;

	// ///////////////////////////////////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}

	public KodEntity getNahveyeTahvil() {
		return nahveyeTahvil;
	}

	public void setNahveyeTahvil(KodEntity nahveyeTahvil) {
		this.nahveyeTahvil = nahveyeTahvil;
	}

	public Date getZamaneDarkhast() {
		return zamaneDarkhast;
	}

	public void setZamaneDarkhast(Date zamaneDarkhast) {
		this.zamaneDarkhast = zamaneDarkhast;
	}

	public KartEntity getKart() {
		return kart;
	}

	public void setKart(KartEntity kart) {
		this.kart = kart;
	}

	public KodEntity getOnvaneKhata() {
		return onvaneKhata;
	}

	public void setOnvaneKhata(KodEntity onvaneKhata) {
		this.onvaneKhata = onvaneKhata;
	}

	public String getSharheKhata() {
		return sharheKhata;
	}

	public void setSharheKhata(String sharheKhata) {
		this.sharheKhata = sharheKhata;
	}

	public Boolean getBayganiShode() {
		return bayganiShode;
	}

	public void setBayganiShode(Boolean bayganiShode) {
		this.bayganiShode = bayganiShode;
	}

	public KodEntity getMarhaleyeSodoor() {
		return marhaleyeSodoor;
	}

	public void setMarhaleyeSodoor(KodEntity marhaleyeSodoor) {
		this.marhaleyeSodoor = marhaleyeSodoor;
	}

	public KodEntity getVaziateMarhale() {
		return vaziateMarhale;
	}

	public void setVaziateMarhale(KodEntity vaziateMarhale) {
		this.vaziateMarhale = vaziateMarhale;
	}

	public String getPuk() {
		return puk;
	}

	public void setPuk(String puk) {
		this.puk = puk;
	}

	public PortalEntity getSazman() {
		return sazman;
	}

	public void setSazman(PortalEntity sazman) {
		this.sazman = sazman;
	}

	public Boolean getGhoflePrint() {
		return ghoflePrint;
	}

	public void setGhoflePrint(Boolean ghoflePrint) {
		this.ghoflePrint = ghoflePrint;
	}

	public String getShomareyeMarsooleh() {
		return shomareyeMarsooleh;
	}

	public void setShomareyeMarsooleh(String shomareyeMarsooleh) {
		this.shomareyeMarsooleh = shomareyeMarsooleh;
	}
	
	public Integer getKarbareSodoor() {
		return karbareSodoor;
	}
	
	public void setKarbareSodoor(Integer karbareSodoor) {
		this.karbareSodoor = karbareSodoor;
	}

	public PishkhanEntity getPishkhan() {
		return pishkhan;
	}

	public void setPishkhan(PishkhanEntity pishkhan) {
		this.pishkhan = pishkhan;
	}

	public String getDadeyeEzafi() {
		return dadeyeEzafi;
	}

	public void setDadeyeEzafi(String dadeyeEzafi) {
		this.dadeyeEzafi = dadeyeEzafi;
	}

}
