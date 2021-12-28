package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "belit")
public class BelitEntity extends BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeBelit")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeBazi", nullable = false)
	private BaziEntity bazi;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeRadifeBolook", nullable = false)
	private RadifeBolookEntity radifeBolook;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTimeTarafdar", nullable = false)
	private TimEntity timeTarafdar;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKart", nullable = true)
	private KartEntity kart;

	@Column(name = "ShomareyeSandali", nullable = false)
	private Short shomareyeSandali;

	@Column(name = "TarikheLaghveBelit")
	private Date tarikheLaghveBelit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public TimEntity getTimeTarafdar() {
		return timeTarafdar;
	}

	public void setTimeTarafdar(TimEntity timeTarafdar) {
		this.timeTarafdar = timeTarafdar;
	}

	public KartEntity getKart() {
		return kart;
	}

	public void setKart(KartEntity kart) {
		this.kart = kart;
	}

	public Short getShomareyeSandali() {
		return shomareyeSandali;
	}

	public void setShomareyeSandali(Short shomareyeSandali) {
		this.shomareyeSandali = shomareyeSandali;
	}

	public Date getTarikheLaghveBelit() {
		return tarikheLaghveBelit;
	}

	public void setTarikheLaghveBelit(Date tarikheLaghveBelit) {
		this.tarikheLaghveBelit = tarikheLaghveBelit;
	}

}
