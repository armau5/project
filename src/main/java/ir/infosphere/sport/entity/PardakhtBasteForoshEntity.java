package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pardakhtbasteforosh")
public class PardakhtBasteForoshEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyePardakhtBasteForosh")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeSiasat", nullable = false)
	private BasteForooshEntity basteForosh;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyePardakht", nullable = false)
	private PardakhtEntity pardakht;
	
	@Column(name = "Mablagh", nullable = false, length = 11)
	private Integer mablagh;

	public Integer getId() {
		return id;
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

	public BasteForooshEntity getBasteForosh() {
		return basteForosh;
	}

	public void setBasteForosh(BasteForooshEntity basteForosh) {
		this.basteForosh = basteForosh;
	}

	public Integer getMablagh() {
		return mablagh;
	}

	public void setMablagh(Integer mablagh) {
		this.mablagh = mablagh;
	}

	public PardakhtEntity getPardakht() {
		return pardakht;
	}

	public void setPardakht(PardakhtEntity pardakht) {
		this.pardakht = pardakht;
	}
}
