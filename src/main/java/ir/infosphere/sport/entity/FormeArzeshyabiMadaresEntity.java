package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "formearzeshyabimadares")
public class FormeArzeshyabiMadaresEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeFormeArzeshyabiMadrese")
	private Integer id;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeOzvArzyab")
	private OzvEntity ozv;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeMadrese")
	private MadreseEntity madrese;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeHamkariMoases")
	private KodMeghdarEntity hamkariMoases;
	
	@Column(name = "ShenaseyeZamineTamrin")
	private Integer zaminTamrinMadrese;
	
	@Column(name = "ShenaseyeEmkanat")
	private Integer emkanatMadrese;
	
	@Column(name = "ShenaseyeRakhtkan")
	private Integer rakhtkanMadrese;
	
	@Column(name = "ShenaseyeEstedadYabi")
	private Integer estedadMadrese;
	
	@Column(name = "ShenaseyeTashilat")
	private Integer tashilatMadrese;
	
	@Column(name = "ShenaseyeVaziateHoghoghi")
	private Integer vaziateHoghoghiMadrese;
	
	@Column(name = "ShenaseyeZaherMorabi")
	private Integer zaherMorabiMadrese;
	
	@Column(name = "ShenaseyeMalzomatEdari")
	private Integer malzomatEdariMadrese;
	
	@Column(name = "ShenaseyeRadeFootballAmozan")
	private Integer radeFootballAmozan;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeNoeZaminBazi")
	private  KodMeghdarEntity noeZamin;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeMaleekiat")
	private  KodMeghdarEntity malekiat;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeKarmandEdari")
	private KodMeghdarEntity karmandEdari;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeLogo")
	private KodMeghdarEntity logo;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeSabegheMadrese")
	private KodMeghdarEntity sabegheMadrese;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeSarparast")
	private KodMeghdarEntity sarparast;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyePezeshk")
	private KodMeghdarEntity pezeshk;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeTadarokat")
	private KodMeghdarEntity tadarokat;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeVaziateMadrese")
	private KodMeghdarEntity vaziateMadreseDarSaleGozashte;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeSherkatMosabeghat")
	private KodMeghdarEntity sherkatMosabeghat;
	
	@Column(name = "Tarikh")
	private Date tarikh;
	
	@Column(name = "HamiMali")
	private Boolean hamiMali;
	
	@Column(name = "ReshteDigar")
	private Boolean reshteDigar;
	
	@Column(name = "Banovan")
	private Boolean banovan;
	
	@Column(name = "Shahrie")
	private Integer shahrie;
	
	@Column(name = "Molahezat")
	private String molahezat;
	
	@Column(name = "ReshteHayeDigar")
	private String reshteHayeDigar;
	
	@Column(name = "Emtiaz")
	private Integer emtiaz;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}

	public MadreseEntity getMadrese() {
		return madrese;
	}

	public void setMadrese(MadreseEntity madrese) {
		this.madrese = madrese;
	}

	public KodMeghdarEntity getHamkariMoases() {
		return hamkariMoases;
	}

	public void setHamkariMoases(KodMeghdarEntity hamkariMoases) {
		this.hamkariMoases = hamkariMoases;
	}

	public Integer getZaminTamrinMadrese() {
		return zaminTamrinMadrese;
	}

	public void setZaminTamrinMadrese(Integer zaminTamrinMadrese) {
		this.zaminTamrinMadrese = zaminTamrinMadrese;
	}

	public Integer getEmkanatMadrese() {
		return emkanatMadrese;
	}

	public void setEmkanatMadrese(Integer emkanatMadrese) {
		this.emkanatMadrese = emkanatMadrese;
	}

	public Integer getRakhtkanMadrese() {
		return rakhtkanMadrese;
	}

	public void setRakhtkanMadrese(Integer rakhtkanMadrese) {
		this.rakhtkanMadrese = rakhtkanMadrese;
	}

	public Integer getEstedadMadrese() {
		return estedadMadrese;
	}

	public void setEstedadMadrese(Integer estedadMadrese) {
		this.estedadMadrese = estedadMadrese;
	}

	public Integer getTashilatMadrese() {
		return tashilatMadrese;
	}

	public void setTashilatMadrese(Integer tashilatMadrese) {
		this.tashilatMadrese = tashilatMadrese;
	}

	public Integer getVaziateHoghoghiMadrese() {
		return vaziateHoghoghiMadrese;
	}

	public void setVaziateHoghoghiMadrese(Integer vaziateHoghoghiMadrese) {
		this.vaziateHoghoghiMadrese = vaziateHoghoghiMadrese;
	}

	public Integer getZaherMorabiMadrese() {
		return zaherMorabiMadrese;
	}

	public void setZaherMorabiMadrese(Integer zaherMorabiMadrese) {
		this.zaherMorabiMadrese = zaherMorabiMadrese;
	}

	public Integer getMalzomatEdariMadrese() {
		return malzomatEdariMadrese;
	}

	public void setMalzomatEdariMadrese(Integer malzomatEdariMadrese) {
		this.malzomatEdariMadrese = malzomatEdariMadrese;
	}

	public Integer getRadeFootballAmozan() {
		return radeFootballAmozan;
	}

	public void setRadeFootballAmozan(Integer radeFootballAmozan) {
		this.radeFootballAmozan = radeFootballAmozan;
	}

	public KodMeghdarEntity getNoeZamin() {
		return noeZamin;
	}

	public void setNoeZamin(KodMeghdarEntity noeZamin) {
		this.noeZamin = noeZamin;
	}

	public KodMeghdarEntity getMalekiat() {
		return malekiat;
	}

	public void setMalekiat(KodMeghdarEntity malekiat) {
		this.malekiat = malekiat;
	}

	public KodMeghdarEntity getKarmandEdari() {
		return karmandEdari;
	}

	public void setKarmandEdari(KodMeghdarEntity karmandEdari) {
		this.karmandEdari = karmandEdari;
	}

	public KodMeghdarEntity getLogo() {
		return logo;
	}

	public void setLogo(KodMeghdarEntity logo) {
		this.logo = logo;
	}

	public KodMeghdarEntity getSabegheMadrese() {
		return sabegheMadrese;
	}

	public void setSabegheMadrese(KodMeghdarEntity sabegheMadrese) {
		this.sabegheMadrese = sabegheMadrese;
	}

	public KodMeghdarEntity getSarparast() {
		return sarparast;
	}

	public void setSarparast(KodMeghdarEntity sarparast) {
		this.sarparast = sarparast;
	}

	public KodMeghdarEntity getPezeshk() {
		return pezeshk;
	}

	public void setPezeshk(KodMeghdarEntity pezeshk) {
		this.pezeshk = pezeshk;
	}

	public KodMeghdarEntity getTadarokat() {
		return tadarokat;
	}

	public void setTadarokat(KodMeghdarEntity tadarokat) {
		this.tadarokat = tadarokat;
	}

	public KodMeghdarEntity getVaziateMadreseDarSaleGozashte() {
		return vaziateMadreseDarSaleGozashte;
	}

	public void setVaziateMadreseDarSaleGozashte(
			KodMeghdarEntity vaziateMadreseDarSaleGozashte) {
		this.vaziateMadreseDarSaleGozashte = vaziateMadreseDarSaleGozashte;
	}

	public KodMeghdarEntity getSherkatMosabeghat() {
		return sherkatMosabeghat;
	}

	public void setSherkatMosabeghat(KodMeghdarEntity sherkatMosabeghat) {
		this.sherkatMosabeghat = sherkatMosabeghat;
	}

	public Date getTarikh() {
		return tarikh;
	}

	public void setTarikh(Date tarikh) {
		this.tarikh = tarikh;
	}

	public Boolean getHamiMali() {
		return hamiMali;
	}

	public void setHamiMali(Boolean hamiMali) {
		this.hamiMali = hamiMali;
	}

	public Boolean getReshteDigar() {
		return reshteDigar;
	}

	public void setReshteDigar(Boolean reshteDigar) {
		this.reshteDigar = reshteDigar;
	}

	public Boolean getBanovan() {
		return banovan;
	}

	public void setBanovan(Boolean banovan) {
		this.banovan = banovan;
	}

	public Integer getShahrie() {
		return shahrie;
	}

	public void setShahrie(Integer shahrie) {
		this.shahrie = shahrie;
	}

	public String getMolahezat() {
		return molahezat;
	}

	public void setMolahezat(String molahezat) {
		this.molahezat = molahezat;
	}

	public String getReshteHayeDigar() {
		return reshteHayeDigar;
	}

	public void setReshteHayeDigar(String reshteHayeDigar) {
		this.reshteHayeDigar = reshteHayeDigar;
	}

	public Integer getEmtiaz() {
		return emtiaz;
	}

	public void setEmtiaz(Integer emtiaz) {
		this.emtiaz = emtiaz;
	}
}
