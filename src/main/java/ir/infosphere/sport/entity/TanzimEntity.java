package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tanzim")
public class TanzimEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTanzim")
	private Integer id;

	@Column(name = "Nam")
	private String nam;

	@Column(name = "Meghdar")
	private String meghdar;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNam() {
		return nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

	public String getMeghdar() {
		return meghdar;
	}

	public void setMeghdar(String meghdar) {
		this.meghdar = meghdar;
	}

}
