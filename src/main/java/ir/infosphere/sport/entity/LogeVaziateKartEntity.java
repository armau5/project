package ir.infosphere.sport.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "logevaziatekart")
public class LogeVaziateKartEntity extends BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeLogeVaziateKart")
	private Long id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeDarkhasteKart")
	private DarkhasteKartEntity darkhasteKart;

	@ManyToOne
	@JoinColumn(name = "MarhaleyeSodoor", nullable = false)
	private KodEntity marhaleyeSodoor;

	@ManyToOne
	@JoinColumn(name = "VaziateMarhale", nullable = false)
	private KodEntity vaziateMarhale;

	@ManyToOne
	@JoinColumn(name = "OnvaneKhata")
	private KodEntity onvaneKhata;

	@Column(name = "SharheKhata", length = 64)
	private String sharheKhata;

	@Column(name = "BayganiShode", nullable = false)
	private Boolean bayganiShode;

	@Column(name = "Faal", nullable = true)
	private Boolean faal;

	@Column(name = "Masdood", nullable = true)
	private Boolean masdood;

	// /////////////////////////////////////////////////////

	public LogeVaziateKartEntity() {
	}

	public LogeVaziateKartEntity(DarkhasteKartEntity darkhasteKart) {
		this.darkhasteKart = darkhasteKart;
		this.marhaleyeSodoor = darkhasteKart.getMarhaleyeSodoor();
		this.vaziateMarhale = darkhasteKart.getVaziateMarhale();
		this.onvaneKhata = darkhasteKart.getOnvaneKhata();
		this.bayganiShode = darkhasteKart.getBayganiShode();
		this.sharheKhata = darkhasteKart.getSharheKhata();
		this.faal = darkhasteKart.getKart().getFaal();
		this.masdood = darkhasteKart.getKart().getMasdood();
	}

	// /////////////////////////////////////////////////////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DarkhasteKartEntity getDarkhasteKart() {
		return darkhasteKart;
	}

	public void setDarkhasteKart(DarkhasteKartEntity darkhasteKart) {
		this.darkhasteKart = darkhasteKart;
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

	public Boolean getFaal() {
		return faal;
	}

	public void setFaal(Boolean faal) {
		this.faal = faal;
	}

	public Boolean getMasdood() {
		return masdood;
	}

	public void setMasdood(Boolean masdood) {
		this.masdood = masdood;
	}

}
