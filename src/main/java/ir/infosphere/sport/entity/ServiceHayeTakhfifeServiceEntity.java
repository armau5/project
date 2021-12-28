package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "servicehayetakhfifeservice")
public class ServiceHayeTakhfifeServiceEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeServiceHayeTakhfifeService")
	private Integer id;
	
	@ManyToOne 
	@JoinColumn(name = "ShenaseyeService")
	private ServiceBakhshEntity service;
	
	@ManyToOne 
	@JoinColumn(name = "ShenaseyeTakhfifeServiceService")
	private TakhfifeServiceEntity takhfifeService;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ServiceBakhshEntity getService() {
		return service;
	}

	public void setService(ServiceBakhshEntity service) {
		this.service = service;
	}

	public TakhfifeServiceEntity getTakhfifeService() {
		return takhfifeService;
	}

	public void setTakhfifeService(TakhfifeServiceEntity takhfifeService) {
		this.takhfifeService = takhfifeService;
	}
}
