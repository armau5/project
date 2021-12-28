package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "logekartebaje")
public class LogeKarteBajeEntity extends BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeLogeKarteBaje")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "NoeTarakonesh", nullable = false)
	private KodEntity noeTarakonesh;

	@Column(name = "Gheymat", nullable = false)
	private Long gheymat;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePoz", nullable = true)
	private PozEntity shenaseyePoz;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBazi", nullable = false)
	private BaziEntity bazi;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKart", nullable = true)
	private KartEntity kart;

	@Column(name = "Zaman", nullable = false)
	private Date zaman;

	public KodEntity getNoeTarakonesh() {
		return noeTarakonesh;
	}

	public void setNoeTarakonesh(KodEntity noeTarakonesh) {
		this.noeTarakonesh = noeTarakonesh;
	}

	public Long getGheymat() {
		return gheymat;
	}

	public void setGheymat(Long gheymat) {
		this.gheymat = gheymat;
	}

	public PozEntity getShenaseyePoz() {
		return shenaseyePoz;
	}

	public void setShenaseyePoz(PozEntity shenaseyePoz) {
		this.shenaseyePoz = shenaseyePoz;
	}

	public BaziEntity getBazi() {
		return bazi;
	}

	public void setBazi(BaziEntity bazi) {
		this.bazi = bazi;
	}

	public KartEntity getKart() {
		return kart;
	}

	public void setKart(KartEntity kart) {
		this.kart = kart;
	}

	public Date getZaman() {
		return zaman;
	}

	public void setZaman(Date zaman) {
		this.zaman = zaman;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
}
