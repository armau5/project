package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "goroohekod")
public class GorooheKodEntity extends BaseEntity<Short> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeGorooheKod")
	private Short id;

	@Column(name = "Nam", length = 100)
	private String name;

	@Column(name = "GhabeliatAfzoodanMeghdarJadid")
	private Boolean meghdarJadid;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getMeghdarJadid() {
		return meghdarJadid;
	}

	public void setMeghdarJadid(Boolean meghdarJadid) {
		this.meghdarJadid = meghdarJadid;
	}
}
