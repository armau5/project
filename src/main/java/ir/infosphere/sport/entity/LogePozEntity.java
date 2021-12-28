package ir.infosphere.sport.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "logepoz")
public class LogePozEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeLogePoz")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePoz", nullable = false)
	private PozEntity poz;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKart", nullable = true)
	private KartEntity kart;

	@Column(name = "ShomareyeMelli", nullable = true)
	private Long shomareyeMelli;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBazi")
	private BaziEntity bazi;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzviyyat")
	private OzviyyatEntity ozviyyat;

	@Column(name = "Zaman", nullable = false)
	private Date zaman;

	@ManyToOne
	@JoinColumn(name = "NoeTarakonesh")
	private KodEntity noeTarakonesh;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PozEntity getPoz() {
		return poz;
	}

	public void setPoz(PozEntity poz) {
		this.poz = poz;
	}

	public KartEntity getKart() {
		return kart;
	}

	public void setKart(KartEntity kart) {
		this.kart = kart;
	}

	public BaziEntity getBazi() {
		return bazi;
	}

	public void setBazi(BaziEntity bazi) {
		this.bazi = bazi;
	}

	public OzviyyatEntity getOzviyyat() {
		return ozviyyat;
	}

	public void setOzviyyat(OzviyyatEntity ozviyyat) {
		this.ozviyyat = ozviyyat;
	}

	public Date getZaman() {
		return zaman;
	}

	public void setZaman(Date zaman) {
		this.zaman = zaman;
	}

	public KodEntity getNoeTarakonesh() {
		return noeTarakonesh;
	}

	public void setNoeTarakonesh(KodEntity noeTarakonesh) {
		this.noeTarakonesh = noeTarakonesh;
	}

	public Long getShomareyeMelli() {
		return shomareyeMelli;
	}

	public void setShomareyeMelli(Long shomareyeMelli) {
		this.shomareyeMelli = shomareyeMelli;
	}


	
}
