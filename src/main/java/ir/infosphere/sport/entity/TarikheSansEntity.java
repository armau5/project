package ir.infosphere.sport.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "tarikhesans")
public class TarikheSansEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeTarikheSans")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeSans", nullable = false)
	private SansEntity shenaseyeSans;

	@Column(name = "Tarikh", nullable = false)
	private Date tarikh;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SansEntity getShenaseyeSans() {
		return shenaseyeSans;
	}

	public void setShenaseyeSans(SansEntity shenaseyeSans) {
		this.shenaseyeSans = shenaseyeSans;
	}
	
	public Date getTarikh() {
		return tarikh;
	}
	
	public void setTarikh(Date tarikh) {
		this.tarikh = tarikh;
	}

}
