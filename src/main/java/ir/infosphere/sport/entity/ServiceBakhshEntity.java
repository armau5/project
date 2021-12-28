package ir.infosphere.sport.entity;

import ir.infosphere.sport.util.FormatUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "servicebakhsh")
public class ServiceBakhshEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeServiceBakhsh")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBakhsh", nullable = false)
	private BakhshEntity bakhsh;

	@ManyToOne
	@JoinColumn(name = "NoeService", nullable = false)
	private KodEntity noeService;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTozih")
	private TozihEntity shenaseyeTozih;
	
	@ManyToOne
	@JoinColumn(name = "Jensiat")
	private KodEntity jensiat;
	
	@ManyToOne
	@JoinColumn(name = "Aks")
	private AksEntity aks;

	@Column(name = "EstefadehDarRooz", nullable = false)
	private Byte estefadehDarRooz;

	@Column(name = "EstefadehDarEtebar")
	private Short estefadehDarEtebar;

	@Column(name = "Gheymat", nullable = false)
	private Integer gheymat;

	@Column(name = "ModateEtebar")
	private Short modateEtebar;

	@Column(name = "NameService")
	private String nameService;

	@Column(name = "GheyreFaal", nullable = false)
	private Boolean gheyreFaal;

	// ///////////////////////////////

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public BakhshEntity getBakhsh() {
		return bakhsh;
	}

	public void setBakhsh(BakhshEntity bakhsh) {
		this.bakhsh = bakhsh;
	}

	public Short getEstefadehDarEtebar() {
		return estefadehDarEtebar;
	}

	public void setEstefadehDarEtebar(Short estefadehDarEtebar) {
		this.estefadehDarEtebar = estefadehDarEtebar;
	}

	public Integer getGheymat() {
		return gheymat;
	}

	public void setGheymat(Integer gheymat) {
		this.gheymat = gheymat;
	}

	public Short getModateEtebar() {
		return modateEtebar;
	}

	public void setModateEtebar(Short modateEtebar) {
		this.modateEtebar = modateEtebar;
	}

	public String getNameService() {
		return nameService;
	}

	public void setNameService(String nameService) {
		this.nameService = nameService;
	}

	public KodEntity getJensiat() {
		return jensiat;
	}

	public void setJensiat(KodEntity jensiat) {
		this.jensiat = jensiat;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public KodEntity getNoeService() {
		return noeService;
	}

	public void setNoeService(KodEntity noeService) {
		this.noeService = noeService;
	}

	public Byte getEstefadehDarRooz() {
		return estefadehDarRooz;
	}

	public void setEstefadehDarRooz(Byte estefadehDarRooz) {
		this.estefadehDarRooz = estefadehDarRooz;
	}

	public TozihEntity getShenaseyeTozih() {
		return shenaseyeTozih;
	}

	public void setShenaseyeTozih(TozihEntity shenaseyeTozih) {
		this.shenaseyeTozih = shenaseyeTozih;
	}

	public AksEntity getAks() {
		return aks;
	}

	public void setAks(AksEntity aks) {
		this.aks = aks;
	}
	
	public String getPersianGheymat() {
		return FormatUtil.getPersianCommaSeparatedInteger(gheymat);
	}
}
