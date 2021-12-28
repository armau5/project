package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "noematlab")
public class NoeMatlabEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeNoeMatlab")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeKodeMatlab")
	private KodEntity kodeMatlab;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeTozih")
	private TozihEntity tozih;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = false)
	private PortalEntity portal;
	
	@Column(name = "Nam")
	private String nam;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KodEntity getKodeMatlab() {
		return kodeMatlab;
	}

	public void setKodeMatlab(KodEntity kodeMatlab) {
		this.kodeMatlab = kodeMatlab;
	}

	public TozihEntity getTozih() {
		return tozih;
	}

	public void setTozih(TozihEntity tozih) {
		this.tozih = tozih;
	}
	
	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public String getNam() {
		return nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

}
