package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tashilatmadrese")
public class TashilatMadreseEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTashilatMadrese")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeEtayePayanDore")
	private KodMeghdarEntity etayePayanDore;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTashkilParvandeh")
	private KodMeghdarEntity tashkilParvandeh;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTahieJozve")
	private KodMeghdarEntity tahieJozve;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTahieFilm")
	private KodMeghdarEntity tahieFilm;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeNamayeshFilm")
	private KodMeghdarEntity namayeshFilm;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeVasayelSamiee")
	private KodMeghdarEntity vasayeleSamiee;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeMoshaverin")
	private KodMeghdarEntity moshaverin;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeDidarAzMosabeghat")
	private KodMeghdarEntity didarAzMosabeghat;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyePardakhteBeMoghe")
	private KodMeghdarEntity pardakhteBeMoghe;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTashkileAnjoman")
	private KodMeghdarEntity tashkileAnjoman;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KodMeghdarEntity getEtayePayanDore() {
		return etayePayanDore;
	}

	public void setEtayePayanDore(KodMeghdarEntity etayePayanDore) {
		this.etayePayanDore = etayePayanDore;
	}

	public KodMeghdarEntity getTashkilParvandeh() {
		return tashkilParvandeh;
	}

	public void setTashkilParvandeh(KodMeghdarEntity tashkilParvandeh) {
		this.tashkilParvandeh = tashkilParvandeh;
	}

	public KodMeghdarEntity getTahieJozve() {
		return tahieJozve;
	}

	public void setTahieJozve(KodMeghdarEntity tahieJozve) {
		this.tahieJozve = tahieJozve;
	}

	public KodMeghdarEntity getTahieFilm() {
		return tahieFilm;
	}

	public void setTahieFilm(KodMeghdarEntity tahieFilm) {
		this.tahieFilm = tahieFilm;
	}

	public KodMeghdarEntity getNamayeshFilm() {
		return namayeshFilm;
	}

	public void setNamayeshFilm(KodMeghdarEntity namayeshFilm) {
		this.namayeshFilm = namayeshFilm;
	}

	public KodMeghdarEntity getVasayeleSamiee() {
		return vasayeleSamiee;
	}

	public void setVasayeleSamiee(KodMeghdarEntity vasayeleSamiee) {
		this.vasayeleSamiee = vasayeleSamiee;
	}

	public KodMeghdarEntity getMoshaverin() {
		return moshaverin;
	}

	public void setMoshaverin(KodMeghdarEntity moshaverin) {
		this.moshaverin = moshaverin;
	}

	public KodMeghdarEntity getDidarAzMosabeghat() {
		return didarAzMosabeghat;
	}

	public void setDidarAzMosabeghat(KodMeghdarEntity didarAzMosabeghat) {
		this.didarAzMosabeghat = didarAzMosabeghat;
	}

	public KodMeghdarEntity getPardakhteBeMoghe() {
		return pardakhteBeMoghe;
	}

	public void setPardakhteBeMoghe(KodMeghdarEntity pardakhteBeMoghe) {
		this.pardakhteBeMoghe = pardakhteBeMoghe;
	}

	public KodMeghdarEntity getTashkileAnjoman() {
		return tashkileAnjoman;
	}

	public void setTashkileAnjoman(KodMeghdarEntity tashkileAnjoman) {
		this.tashkileAnjoman = tashkileAnjoman;
	}
}
