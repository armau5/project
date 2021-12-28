package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity(name = "sharj")
public class SharjEntity extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeSharj")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = false)
	private PortalEntity portal;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;

	@Column(name = "Mablagh", nullable = false)
	private Integer mablagh;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}

	public Integer getMablagh() {
		return mablagh;
	}

	public void setMablagh(Integer mablagh) {
		this.mablagh = mablagh;
	}
}
