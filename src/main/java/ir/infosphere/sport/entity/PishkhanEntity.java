package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pishkhan")
public class PishkhanEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyePishkhan")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeNahiye")
	private NahiyeEntity nahiye;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal")
	private PortalEntity portal;

	@Column(name = "Adres", length = 255)
	private String adres;
	
	@Column(name = "Name")
	private String name;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	public NahiyeEntity getNahiye() {
		return nahiye;
	}

	public void setNahiye(NahiyeEntity nahiye) {
		this.nahiye = nahiye;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}
	
}
