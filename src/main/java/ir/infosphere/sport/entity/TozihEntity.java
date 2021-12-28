package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tozih")
public class TozihEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTozih")
	private Integer id;

	@Column(name = "Tozih", length = 10240)
	private String tozih;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTozih() {
		return tozih;
	}

	public void setTozih(String tozih) {
		this.tozih = tozih;
	}

}
