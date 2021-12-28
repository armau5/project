package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tag")
public class TagEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTag")
	private Integer id;

	@Column(name = "Nam", length = 32, nullable = false)
	private String nam;

	@Column(name = "Tozih", length = 64)
	private String tozih;

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

	public String getTozih() {
		return tozih;
	}

	public void setTozih(String tozih) {
		this.tozih = tozih;
	}
}
