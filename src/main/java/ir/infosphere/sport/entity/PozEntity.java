package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "poz")
public class PozEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyePoz")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoePoz")
	private NoePozEntity noePoz;

	@Column(name = "TarikheKharid")
	private Date tarikheKharid;

	@Column(name = "Serial")
	private Integer serial;

	@Column(name = "Version")
	private Integer version;

	@ManyToOne
	@JoinColumn(name = "Vaziat")
	private KodEntity vaziat = null;

	@Column(name = "TarikheEsghat")
	private Date tarikheEsghat;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeVoroodi", nullable = true)
	private VoroodiEntity voroodi;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMogheiyyat", nullable = true)
	private MogheiyyatEntity mogheiyyat;

	// ///////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSerial() {
		return serial;
	}

	public void setSerial(Integer Serial) {
		this.serial = Serial;
	}

	public Date getTarikheKharid() {
		return tarikheKharid;
	}

	public void setTarikheKharid(Date tarikheKharid) {
		this.tarikheKharid = tarikheKharid;
	}

	public KodEntity getVaziat() {
		return vaziat;
	}

	public void setVaziat(KodEntity vaziat) {
		this.vaziat = vaziat;
	}

	public Date getTarikheEsghat() {
		return tarikheEsghat;
	}

	public void setTarikheEsghat(Date tarikheEsghat) {
		this.tarikheEsghat = tarikheEsghat;
	}

	public NoePozEntity getNoePoz() {
		return noePoz;
	}

	public void setNoePoz(NoePozEntity noePoz) {
		this.noePoz = noePoz;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public VoroodiEntity getVoroodi() {
		return voroodi;
	}

	public void setVoroodi(VoroodiEntity voroodi) {
		this.voroodi = voroodi;
	}

	public MogheiyyatEntity getMogheiyyat() {
		return mogheiyyat;
	}

	public void setMogheiyyat(MogheiyyatEntity mogheiyyat) {
		this.mogheiyyat = mogheiyyat;
	}

}
