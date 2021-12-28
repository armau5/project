package ir.infosphere.sport.entity;

import java.util.Date;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "taghvimetatilatebakhsh")
public class TaghvimeTatilateBakhshEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTaghvimeBakhsh")
	
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBakhsh", nullable = false)
	private BakhshEntity shenaseyeBakhsh;

	@Column(name = "Tarikh", nullable = false)
	private Date tarikh;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BakhshEntity getShenaseyeBakhsh() {
		return shenaseyeBakhsh;
	}

	public void setShenaseyeBakhsh(BakhshEntity shenaseyeBakhsh) {
		this.shenaseyeBakhsh = shenaseyeBakhsh;
	}

	public Date getTarikh() {
		return tarikh;
	}

	public void setTarikh(Date tarikh) {
		this.tarikh = tarikh;
	}
}
