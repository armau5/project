package ir.infosphere.sport.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="sanseservice")
public class SanseServiceEntity extends BaseEntity<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeSanseService")
	private Integer id;
	

	@Column(name = "GheyreFaal")
	private Boolean GheyreFaal;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSans")
	private SansEntity shenaseyeSans;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeServiceBakhsh", nullable = false)
	private ServiceBakhshEntity shenaseyeServiceBakhsh;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getGheyreFaal() {
		return GheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		GheyreFaal = gheyreFaal;
	}

	public SansEntity getShenaseyeSans() {
		return shenaseyeSans;
	}

	public void setShenaseyeSans(SansEntity shenaseyeSans) {
		this.shenaseyeSans = shenaseyeSans;
	}

	public ServiceBakhshEntity getShenaseyeServiceBakhsh() {
		return shenaseyeServiceBakhsh;
	}

	public void setShenaseyeServiceBakhsh(ServiceBakhshEntity shenaseyeServiceBakhsh) {
		this.shenaseyeServiceBakhsh = shenaseyeServiceBakhsh;
	}
	
	

}
