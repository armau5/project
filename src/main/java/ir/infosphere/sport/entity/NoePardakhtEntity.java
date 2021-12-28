package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "noepardakht")
public class NoePardakhtEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeNoePardakht")
	private Integer id;
	
	@Column(name = "Onvan", nullable = false, length = 50)
	private String onvan;
	
	@Column(name = "MablaghDarad", nullable = false, length = 50)
	private Boolean mablaghDarad;
	
	@Column(name = "GheyreFaal", nullable = false)
	private Boolean gheyreFaal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOnvan() {
		return onvan;
	}

	public void setOnvan(String onvan) {
		this.onvan = onvan;
	}
	
	public Boolean getMablaghDarad() {
		return mablaghDarad;
	}

	public void setMablaghDarad(Boolean mablaghDarad) {
		this.mablaghDarad = mablaghDarad;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	// ////////////////////////


}
