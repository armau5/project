package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "vaziaterakhtkanmadrese")
public class VaziateRakhtkanMadreseEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeVaziateRakhtkan")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTedadeRakhtkan")
	private KodMeghdarEntity tedadRakhtkan;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeSandaliYaNimkat")
	private KodMeghdarEntity sandaliNimkat;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeKomod")
	private KodMeghdarEntity komod;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTakhteWhiteBoard")
	private KodMeghdarEntity takhteWhiteBoard;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTahvieMonaseb")
	private KodMeghdarEntity tahvieMonaseb;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTakhtePezeshki")
	private KodMeghdarEntity takhtePezeshki;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeBehdashteOmomi")
	private KodMeghdarEntity behdashtOmomi;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeDosh")
	private KodMeghdarEntity dosh;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeServiceBehdashti")
	private KodMeghdarEntity serviceBehdashti;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KodMeghdarEntity getTedadRakhtkan() {
		return tedadRakhtkan;
	}

	public void setTedadRakhtkan(KodMeghdarEntity tedadRakhtkan) {
		this.tedadRakhtkan = tedadRakhtkan;
	}

	public KodMeghdarEntity getSandaliNimkat() {
		return sandaliNimkat;
	}

	public void setSandaliNimkat(KodMeghdarEntity sandaliNimkat) {
		this.sandaliNimkat = sandaliNimkat;
	}

	public KodMeghdarEntity getKomod() {
		return komod;
	}

	public void setKomod(KodMeghdarEntity komod) {
		this.komod = komod;
	}

	public KodMeghdarEntity getTakhteWhiteBoard() {
		return takhteWhiteBoard;
	}

	public void setTakhteWhiteBoard(KodMeghdarEntity takhteWhiteBoard) {
		this.takhteWhiteBoard = takhteWhiteBoard;
	}

	public KodMeghdarEntity getTahvieMonaseb() {
		return tahvieMonaseb;
	}

	public void setTahvieMonaseb(KodMeghdarEntity tahvieMonaseb) {
		this.tahvieMonaseb = tahvieMonaseb;
	}

	public KodMeghdarEntity getTakhtePezeshki() {
		return takhtePezeshki;
	}

	public void setTakhtePezeshki(KodMeghdarEntity takhtePezeshki) {
		this.takhtePezeshki = takhtePezeshki;
	}

	public KodMeghdarEntity getBehdashtOmomi() {
		return behdashtOmomi;
	}

	public void setBehdashtOmomi(KodMeghdarEntity behdashtOmomi) {
		this.behdashtOmomi = behdashtOmomi;
	}

	public KodMeghdarEntity getDosh() {
		return dosh;
	}

	public void setDosh(KodMeghdarEntity dosh) {
		this.dosh = dosh;
	}

	public KodMeghdarEntity getServiceBehdashti() {
		return serviceBehdashti;
	}

	public void setServiceBehdashti(KodMeghdarEntity serviceBehdashti) {
		this.serviceBehdashti = serviceBehdashti;
	}
}
