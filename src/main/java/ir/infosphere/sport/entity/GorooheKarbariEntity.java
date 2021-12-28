package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "goroohekarbari")
public class GorooheKarbariEntity extends BaseEntity<Short> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeGorooh")
	private Short id;

	@Column(name = "NameGorooh", length = 45, nullable = false)
	private String nam;
	
	@ManyToOne
	@JoinColumn(name = "NoeGorooh", nullable = false)
	private KodEntity noeGorooh;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = true, columnDefinition = "INT(11) UNSIGNED")
	private PortalEntity portal;

	@Column(name = "TasvireKart", nullable = true)
	private String tasvireKart;
	
	@Column(name = "GheyreFaal", nullable = true)
	private Boolean gheyreFaal = false;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNam() {
		return nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

	public KodEntity getNoeGorooh() {
		return noeGorooh;
	}

	public void setNoeGorooh(KodEntity noeGorooh) {
		this.noeGorooh = noeGorooh;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public String getTasvireKart() {
		return tasvireKart;
	}

	public void setTasvireKart(String tasvireKart) {
		this.tasvireKart = tasvireKart;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}	
}

