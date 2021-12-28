package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "link")
public class LinkEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeLink")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "PortalID", nullable = false)
	private PortalEntity portal;
	
	@Column(name = "OnvaneLink", nullable = false, length = 50)
	private String onvanLink;
	
	@Column(name = "AdresLink", nullable = false, length = 1000)
	private String adresLink;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public String getOnvanLink() {
		return onvanLink;
	}

	public void setOnvanLink(String onvanLink) {
		this.onvanLink = onvanLink;
	}

	public String getAdresLink() {
		return adresLink;
	}

	public void setAdres(String adresLink) {
		this.adresLink = adresLink;
	}
}
