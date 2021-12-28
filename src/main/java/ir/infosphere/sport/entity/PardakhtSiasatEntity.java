package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pardakhtsiasat")
public class PardakhtSiasatEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyePardakhtSiasat")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasat", nullable = false)
	private SiasatEntity siasat;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePardakht", nullable = false)
	private PardakhtEntity pardakht;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoePardakht", nullable = false)
	private NoePardakhtEntity noepardakht;
	
	@Column(name = "ShenaseyeMadreseYaTim")
	private Integer madreseTim;
	
	@Column(name = "Mablagh", nullable = false, length = 11)
	private Integer mablagh;

	@Column(name = "Tarikh", nullable = false)
	private Date tarikh;

///////////////////////////

	
	public Integer getId() {
		return id;
	}

	public Integer getMadreseTim() {
		return madreseTim;
	}

	public void setMadreseTim(Integer madreseTim) {
		this.madreseTim = madreseTim;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}

	public SiasatEntity getSiasat() {
		return siasat;
	}

	public void setSiasat(SiasatEntity siasat) {
		this.siasat = siasat;
	}
	

	public PardakhtEntity getPardakht() {
		return pardakht;
	}

	public void setPardakht(PardakhtEntity pardakht) {
		this.pardakht = pardakht;
	}

	public NoePardakhtEntity getNoepardakht() {
		return noepardakht;
	}

	public void setNoepardakht(NoePardakhtEntity noepardakht) {
		this.noepardakht = noepardakht;
	}

	public Integer getMablagh() {
		return mablagh;
	}

	public void setMablagh(Integer mablagh) {
		this.mablagh = mablagh;
	}

	public Date getTarikh() {
		return tarikh;
	}

	public void setTarikh(Date tarikh) {
		this.tarikh = tarikh;
	}
	


	
}
