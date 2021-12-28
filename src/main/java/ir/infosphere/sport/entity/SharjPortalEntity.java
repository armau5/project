package ir.infosphere.sport.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "sharjportal")
public class SharjPortalEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeSharj")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = false)
	private PortalEntity portal;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoePardakht", nullable = false)
	private KodEntity dasteBandiPardakht;

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

	public KodEntity getDasteBandiPardakht() {
		return dasteBandiPardakht;
	}

	public void setDasteBandiPardakht(KodEntity dasteBandiPardakht) {
		this.dasteBandiPardakht = dasteBandiPardakht;
	}
}

