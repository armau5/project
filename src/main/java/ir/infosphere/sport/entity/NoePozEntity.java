package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "noepoz")
public class NoePozEntity extends BaseEntity<Short> {
	@Id
	@Column(name = "ShenaseyeNoePoz")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Short id;

	@Column(name = "Model")
	private String model;
	
	@Column(name = "SherkateSazandeh")
	private String sherkateSazandeh;

	@Column(name = "Version")
	private Integer version;

	@Column(name = "MasireFileUpdate", length = 255)
	private String masireFileUpdate;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}
	

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getMasireFileUpdate() {
		return masireFileUpdate;
	}

	public void setMasireFileUpdate(String masireFileUpdate) {
		this.masireFileUpdate = masireFileUpdate;
	}

	public String getSherkateSazandeh() {
		return sherkateSazandeh;
	}

	public void setSherkateSazandeh(String sherkateSazandeh) {
		this.sherkateSazandeh = sherkateSazandeh;
	}
	
}
