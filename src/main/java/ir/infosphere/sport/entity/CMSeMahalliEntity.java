package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "cmsemahalli")
public class CMSeMahalliEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeCMSeMahalli")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeVarzeshgah", nullable = false)
	private VarzeshgahEntity shenaseyeVarzeshgah;

	@Column(name = "IP", nullable = false, length = 15)
	private String ip;

	// ///////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VarzeshgahEntity getShenaseyeVarzeshgah() {
		return shenaseyeVarzeshgah;
	}

	public void setShenaseyeVarzeshgah(VarzeshgahEntity shenaseyeVarzeshgah) {
		this.shenaseyeVarzeshgah = shenaseyeVarzeshgah;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
