package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ozviyyat")
public class OzviyyatEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeOziyyat")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePardakht", nullable = true)
	private PardakhtEntity pardakht;

	@ManyToOne
	@JoinColumn(name = "ShenaseService", nullable = false)
	private ServiceBakhshEntity service;

	@Column(name = "TarikhShoro")
	private Date tarikhShoro;

	@Column(name = "TarikhLaghveOzviat")
	private Date tarikhLaghveOzviat;

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

	public PardakhtEntity getPardakht() {
		return pardakht;
	}

	public void setPardakht(PardakhtEntity pardakht) {
		this.pardakht = pardakht;
	}

	public ServiceBakhshEntity getService() {
		return service;
	}

	public void setService(ServiceBakhshEntity service) {
		this.service = service;
	}

	public Date getTarikhShoro() {
		return tarikhShoro;
	}

	public void setTarikhShoro(Date tarikhShoro) {
		this.tarikhShoro = tarikhShoro;
	}

	public Date getTarikhLaghveOzviat() {
		return tarikhLaghveOzviat;
	}

	public void setTarikhLaghveOzviat(Date tarikhLaghveOzviat) {
		this.tarikhLaghveOzviat = tarikhLaghveOzviat;
	}
}
