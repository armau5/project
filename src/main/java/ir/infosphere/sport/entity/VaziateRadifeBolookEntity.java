package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "vaziateradifebolook")
public class VaziateRadifeBolookEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeVaziateRadifeBolook")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeRadifeBolook", nullable = false)
	private RadifeBolookEntity radifeBolook;
	
	@ManyToOne
	@JoinColumn(name = "shenaseyebazi", nullable = false)
	private BaziEntity bazi;
	
	@ManyToOne
	@JoinColumn(name = "Vaziat", nullable = false)
	private KodEntity vaziat;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RadifeBolookEntity getRadifeBolook() {
		return radifeBolook;
	}

	public void setRadifeBolook(RadifeBolookEntity radifeBolook) {
		this.radifeBolook = radifeBolook;
	}

	public BaziEntity getBazi() {
		return bazi;
	}

	public void setBazi(BaziEntity bazi) {
		this.bazi = bazi;
	}

	public KodEntity getVaziat() {
		return vaziat;
	}

	public void setVaziat(KodEntity vaziat) {
		this.vaziat = vaziat;
	}


}
