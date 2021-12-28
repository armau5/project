package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "rahgiri")
public class RahgiriEntity extends BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeRahgiri")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv")
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoeAmaliat")
	private KodEntity noeAmaliat;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal")
	private PortalEntity portal;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKarbarAnjamDahandeh")
	private OzvEntity karbarAnjamDahandeh;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeTarafghararDad")
	private TarafeGharardadEntity tarafGharardad;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeBelit")
	private BelitEntity belit;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeService")
	private ServiceBakhshEntity service;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasat")
	private SiasatEntity siasat;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeDarkhasteKart")
	private DarkhasteKartEntity darkhasteKart;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSharjh")
	private SharjEntity sharj;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeBasteForosh")
	private BasteForooshEntity basteForosh;
	
	@Column(name = "TarikhZaman")
	private Date tarikhZaman;
	
	@Column(name = "SharheAmaliat", length = 1000)
	private String sharheAmaliat;
	
	@Column(name = "DideShode")
	private Boolean dideShode;

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

	public KodEntity getNoeAmaliat() {
		return noeAmaliat;
	}

	public void setNoeAmaliat(KodEntity noeAmaliat) {
		this.noeAmaliat = noeAmaliat;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public OzvEntity getKarbarAnjamDahandeh() {
		return karbarAnjamDahandeh;
	}

	public void setKarbarAnjamDahandeh(OzvEntity karbarAnjamDahandeh) {
		this.karbarAnjamDahandeh = karbarAnjamDahandeh;
	}

	public TarafeGharardadEntity getTarafGharardad() {
		return tarafGharardad;
	}

	public void setTarafGharardad(TarafeGharardadEntity tarafGharardad) {
		this.tarafGharardad = tarafGharardad;
	}

	public BelitEntity getBelit() {
		return belit;
	}

	public void setBelit(BelitEntity belit) {
		this.belit = belit;
	}

	public ServiceBakhshEntity getService() {
		return service;
	}

	public void setService(ServiceBakhshEntity service) {
		this.service = service;
	}

	public SiasatEntity getSiasat() {
		return siasat;
	}

	public void setSiasat(SiasatEntity siasat) {
		this.siasat = siasat;
	}

	public DarkhasteKartEntity getDarkhasteKart() {
		return darkhasteKart;
	}

	public void setDarkhasteKart(DarkhasteKartEntity darkhasteKart) {
		this.darkhasteKart = darkhasteKart;
	}

	public SharjEntity getSharj() {
		return sharj;
	}

	public void setSharj(SharjEntity sharj) {
		this.sharj = sharj;
	}

	public Date getTarikhZaman() {
		return tarikhZaman;
	}

	public void setTarikhZaman(Date tarikhZaman) {
		this.tarikhZaman = tarikhZaman;
	}

	public Boolean getDideShode() {
		return dideShode;
	}

	public void setDideShode(Boolean dideShode) {
		this.dideShode = dideShode;
	}

	public String getSharheAmaliat() {
		return sharheAmaliat;
	}

	public void setSharheAmaliat(String sharheAmaliat) {
		this.sharheAmaliat = sharheAmaliat;
	}

	public BasteForooshEntity getBasteForosh() {
		return basteForosh;
	}

	public void setBasteForosh(BasteForooshEntity basteForosh) {
		this.basteForosh = basteForosh;
	}
}
