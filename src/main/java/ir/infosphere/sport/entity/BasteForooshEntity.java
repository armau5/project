package ir.infosphere.sport.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "basteforosh")
public class BasteForooshEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeBasteForosh")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal")
	private PortalEntity portal;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeAks")
	private AksEntity aks;
	
	@Column(name = "NameBaste", nullable = false)
	private String nameBaste;
	
	@Column(name = "Mablagh")
	private Integer mablagh;
	
	@Column(name = "TarikhShoro", nullable = false)
	private Date tarikhShoro;
	
	@Column(name = "TarikhPayan", nullable = false)
	private Date tarikhPayan;
	
	@Column(name = "HadeAksareAfrad")
	private Integer hadeAksareAfrad;
	
	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameBaste() {
		return nameBaste;
	}

	public void setNameBaste(String nameBaste) {
		this.nameBaste = nameBaste;
	}

	public Integer getMablagh() {
		return mablagh;
	}

	public void setMablagh(Integer mablagh) {
		this.mablagh = mablagh;
	}

	public Date getTarikhShoro() {
		return tarikhShoro;
	}

	public void setTarikhShoro(Date tarikhShoro) {
		this.tarikhShoro = tarikhShoro;
	}

	public Date getTarikhPayan() {
		return tarikhPayan;
	}

	public void setTarikhPayan(Date tarikhPayan) {
		this.tarikhPayan = tarikhPayan;
	}

	public Integer getHadeAksareAfrad() {
		return hadeAksareAfrad;
	}

	public void setHadeAksareAfrad(Integer hadeAksareAfrad) {
		this.hadeAksareAfrad = hadeAksareAfrad;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public AksEntity getAks() {
		return aks;
	}

	public void setAks(AksEntity aks) {
		this.aks = aks;
	}
}
