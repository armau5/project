package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pardakhtsharj")
public class PardakhtSharjEntity extends BaseEntity<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyePardakhtSharj")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSharj")
	private SharjEntity sharj;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePardakht", nullable = false)
	private PardakhtEntity pardakht;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = false)
	private PortalEntity portal;
	
	@Column(name = "Mablagh", nullable = false)
	private Integer mablagh;

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

	public SharjEntity getSharj() {
		return sharj;
	}

	public void setSharj(SharjEntity sharj) {
		this.sharj = sharj;
	}

	public PardakhtEntity getPardakht() {
		return pardakht;
	}

	public void setPardakht(PardakhtEntity pardakht) {
		this.pardakht = pardakht;
	}

	public Integer getMablagh() {
		return mablagh;
	}

	public void setMablagh(Integer mablagh) {
		this.mablagh = mablagh;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}
}
