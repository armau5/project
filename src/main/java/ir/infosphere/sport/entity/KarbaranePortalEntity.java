package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "karbaraneportal")
public class KarbaranePortalEntity extends BaseEntity<Short> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Shenase")
	private Short id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = false)
	private PortalEntity portal;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortaleKarbaran", nullable = false)
	private PortalEntity portaleKarbaran;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public PortalEntity getPortaleKarbaran() {
		return portaleKarbaran;
	}

	public void setPortaleKarbaran(PortalEntity portaleKarbaran) {
		this.portaleKarbaran = portaleKarbaran;
	}

}

