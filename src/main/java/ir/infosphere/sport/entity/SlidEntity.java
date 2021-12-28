package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity(name = "slid")
public class SlidEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeSlid")
	private Integer id;

	@Column(name = "OnvaneSlid", nullable = false, length = 50)
	private String onvanSlid;

	@OneToOne
	@JoinColumn(name = "AksID", nullable = false)
	private AksEntity aks;

	@Column(name = "TozihSlid", nullable = false)
	private String tozihSlid;

	@Column(name = "OlaviatSlid", nullable = false)
	private Integer olaviatSlid;

	@ManyToOne
	@JoinColumn(name = "PortalID", nullable = false)
	private PortalEntity portal;

	@Column(name = "LinkAddress", nullable = true)
	private String linkAddress;

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public Integer getOlaviatSlid() {
		return olaviatSlid;
	}

	public void setOlaviatSlid(Integer olaviatSlid) {
		this.olaviatSlid = olaviatSlid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOnvanSlid() {
		return onvanSlid;
	}

	public void setOnvanSlid(String onvanSlid) {
		this.onvanSlid = onvanSlid;
	}

	public String getTozihSlid() {
		return tozihSlid;
	}

	public void setTozihSlid(String tozihSlid) {
		this.tozihSlid = tozihSlid;
	}

	public AksEntity getAks() {
		return aks;
	}

	public void setAks(AksEntity aks) {
		this.aks = aks;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

}
