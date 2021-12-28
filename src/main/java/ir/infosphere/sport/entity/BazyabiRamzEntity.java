package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "bazyabiramz")
public class BazyabiRamzEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeBazyabi")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = false)
	private PortalEntity portal;
	
	@Column(name = "GheyreFaal")
	private boolean gheyreFaal;

	@Column(name = "KodeErsali")
	private String kodeErsali;
	
	@Column(name = "TarikhZaman")
	private Date tarikhZaman;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public boolean isGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public String getKodeErsali() {
		return kodeErsali;
	}

	public void setKodeErsali(String kodeErsali) {
		this.kodeErsali = kodeErsali;
	}

	public Date getTarikhZaman() {
		return tarikhZaman;
	}

	public void setTarikhZaman(Date tarikhZaman) {
		this.tarikhZaman = tarikhZaman;
	}
}
