package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity(name = "siasat")
public class SiasatEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Shenaseyesiasat")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeReshteVarzeshi")
	private ReshteyeVarzeshiEntity reshteVarzeshi;
	
	@ManyToOne 
	@JoinColumn(name = "ShenaseyeGorooheReshteyeVarzeshi")
	private GorooheReshteyeVarzeshiEntity gorooheReshteVarzeshi;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoePardakht")
	private NoePardakhtEntity noepardakht;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoeSiasat", nullable = false)
	private KodEntity noesisat;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal")
	private PortalEntity portal;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeJensiat")
	private KodEntity jensiat;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeGorooheKarbari")
	private GorooheKarbariEntity gorooheKarbari;
	
	@ManyToOne
	@JoinColumn(name = "SemateOzv")
	private KodEntity semateOzv;
	
	@ManyToOne
	@JoinColumn(name = "SatheTim")
	private KodEntity satheTim;
	
	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal;
	
	@Column(name = "AzTarikh", nullable = false, length = 50)
	private Date azTarikh;
	
	@Column(name = "TaTarikh", nullable = false, length = 50)
	private Date taTarikh;

	@Column(name = "Mizan", length = 1000)
	private Integer mizan;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReshteyeVarzeshiEntity getReshteVarzeshi() {
		return reshteVarzeshi;
	}

	public void setReshteVarzeshi(ReshteyeVarzeshiEntity reshteVarzeshi) {
		this.reshteVarzeshi = reshteVarzeshi;
	}

	public GorooheReshteyeVarzeshiEntity getGorooheReshteVarzeshi() {
		return gorooheReshteVarzeshi;
	}

	public void setGorooheReshteVarzeshi(
			GorooheReshteyeVarzeshiEntity gorooheReshteVarzeshi) {
		this.gorooheReshteVarzeshi = gorooheReshteVarzeshi;
	}

	public NoePardakhtEntity getNoepardakht() {
		return noepardakht;
	}

	public void setNoepardakht(NoePardakhtEntity noepardakht) {
		this.noepardakht = noepardakht;
	}

	public KodEntity getNoesisat() {
		return noesisat;
	}

	public void setNoesisat(KodEntity noesisat) {
		this.noesisat = noesisat;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public KodEntity getSatheTim() {
		return satheTim;
	}

	public void setSatheTim(KodEntity satheTim) {
		this.satheTim = satheTim;
	}

	public KodEntity getJensiat() {
		return jensiat;
	}

	public void setJensiat(KodEntity jensiat) {
		this.jensiat = jensiat;
	}

	public GorooheKarbariEntity getGorooheKarbari() {
		return gorooheKarbari;
	}

	public void setGorooheKarbari(GorooheKarbariEntity gorooheKarbari) {
		this.gorooheKarbari = gorooheKarbari;
	}

	public Date getAzTarikh() {
		return azTarikh;
	}

	public void setAzTarikh(Date azTarikh) {
		this.azTarikh = azTarikh;
	}

	public Date getTaTarikh() {
		return taTarikh;
	}

	public void setTaTarikh(Date taTarikh) {
		this.taTarikh = taTarikh;
	}

	public Integer getMizan() {
		return mizan;
	}

	public void setMizan(Integer mizan) {
		this.mizan = mizan;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public KodEntity getSemateOzv() {
		return semateOzv;
	}

	public void setSemateOzv(KodEntity semateOzv) {
		this.semateOzv = semateOzv;
	}
	
	

}
