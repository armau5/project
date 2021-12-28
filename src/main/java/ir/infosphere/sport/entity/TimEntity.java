package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tim")
public class TimEntity extends BaseEntity<Short> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTim")
	private Short id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzveMasool", nullable = true)
	private OzvEntity ozveMasool;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeNahiye", nullable = true)
	private NahiyeEntity nahiye;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeTozih", nullable = true)
	private TozihEntity tozih;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeGorooheReshteyeVarzeshi", nullable = false)
	private GorooheReshteyeVarzeshiEntity gorooheReshteyeVarzeshi;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeJensiat", nullable = true)
	private KodEntity jensiat;
	
	@Column(name = "NameTim", nullable = false, length = 100)
	private String nameTim;
	
	@ManyToOne
	@JoinColumn(name = "satheTim", nullable = false)
	private KodEntity satheTim;

	@Column(name = "TimeKhareji", nullable = false)
	private Boolean timeDakheli;

	@ManyToOne
	@JoinColumn(name = "Keshvar", nullable = true)
	private KodEntity keshvar;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeLogo", nullable = true)
	private AksEntity logo;
	
	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal = false;
	
	@Column(name = "TarikheSabtTim")
	private Date tarikheSabtTim;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public OzvEntity getOzveMasool() {
		return ozveMasool;
	}

	public void setOzveMasool(OzvEntity ozveMasool) {
		this.ozveMasool = ozveMasool;
	}

	public NahiyeEntity getNahiye() {
		return nahiye;
	}

	public void setNahiye(NahiyeEntity nahiye) {
		this.nahiye = nahiye;
	}

	public TozihEntity getTozih() {
		return tozih;
	}

	public void setTozih(TozihEntity tozih) {
		this.tozih = tozih;
	}
	
	public GorooheReshteyeVarzeshiEntity getGorooheReshteyeVarzeshi() {
		return gorooheReshteyeVarzeshi;
	}

	public void setGorooheReshteyeVarzeshi(
			GorooheReshteyeVarzeshiEntity gorooheReshteyeVarzeshi) {
		this.gorooheReshteyeVarzeshi = gorooheReshteyeVarzeshi;
	}

	public String getNameTim() {
		return nameTim;
	}

	public void setNameTim(String nameTim) {
		this.nameTim = nameTim;
	}

	public KodEntity getSatheTim() {
		return satheTim;
	}

	public void setSatheTim(KodEntity satheTim) {
		this.satheTim = satheTim;
	}

	public Boolean getTimeDakheli() {
		return timeDakheli;
	}

	public void setTimeDakheli(Boolean timeDakheli) {
		this.timeDakheli = timeDakheli;
	}

	public KodEntity getKeshvar() {
		return keshvar;
	}

	public void setKeshvar(KodEntity keshvar) {
		this.keshvar = keshvar;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public KodEntity getJensiat() {
		return jensiat;
	}

	public void setJensiat(KodEntity jensiat) {
		this.jensiat = jensiat;
	}

	public Date getTarikheSabtTim() {
		return tarikheSabtTim;
	}

	public void setTarikheSabtTim(Date tarikheSabtTim) {
		this.tarikheSabtTim = tarikheSabtTim;
	}

	public AksEntity getLogo() {
		return logo;
	}

	public void setLogo(AksEntity logo) {
		this.logo = logo;
	}
}
