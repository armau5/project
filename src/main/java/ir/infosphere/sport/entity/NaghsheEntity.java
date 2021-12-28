package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "naghshe")
public class NaghsheEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeNaghshe")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeNahiye")
	private NahiyeEntity nahiye;

	@Column(name = "Naghshe", length = 10000)
	private String naghshe;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NahiyeEntity getNahiye() {
		return nahiye;
	}

	public void setNahiye(NahiyeEntity nahiye) {
		this.nahiye = nahiye;
	}

	public String getNaghshe() {
		return naghshe;
	}

	public void setNaghshe(String naghshe) {
		this.naghshe = naghshe;
	}
}
