package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pardakht")
public class PardakhtEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyePardakht")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv")
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeDargah")
	private DargahEntity dargah;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeDasteBandi")
	private KodEntity dastebandi;
	
	@Column(name = "HalatePardakht")
	private Short halatePardakht;
	
	@Column(name = "Mablagh")
	private Integer mablagh;
	
	@Column(name = "Takhfif")
	private Integer takhfif;
	
	@Column(name = "MablaghErsaliBeBank")
	private Integer mablaghErsaliBeBank;
	
	@Column(name = "KodeRahgiri", nullable = true, length = 100)
	private String kodeRahgiri;

	@Column(name = "TarikhZaman")
	private Date tarikhZaman;

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

	public DargahEntity getDargah() {
		return dargah;
	}

	public void setDargah(DargahEntity dargah) {
		this.dargah = dargah;
	}

	public KodEntity getDastebandi() {
		return dastebandi;
	}

	public void setDastebandi(KodEntity dastebandi) {
		this.dastebandi = dastebandi;
	}

	public Short getHalatePardakht() {
		return halatePardakht;
	}

	public void setHalatePardakht(Short halatePardakht) {
		this.halatePardakht = halatePardakht;
	}

	public Integer getMablagh() {
		return mablagh;
	}
	
	public void setMablagh(Integer mablagh) {
		this.mablagh = mablagh;
	}

	public Integer getTakhfif() {
		return takhfif;
	}

	public void setTakhfif(Integer takhfif) {
		this.takhfif = takhfif;
	}

	public Integer getMablaghErsaliBeBank() {
		return mablaghErsaliBeBank;
	}

	public void setMablaghErsaliBeBank(Integer mablaghErsaliBeBank) {
		this.mablaghErsaliBeBank = mablaghErsaliBeBank;
	}

	public String getKodeRahgiri() {
		return kodeRahgiri;
	}

	public void setKodeRahgiri(String kodeRahgiri) {
		this.kodeRahgiri = kodeRahgiri;
	}

	public Date getTarikhZaman() {
		return tarikhZaman;
	}

	public void setTarikhZaman(Date tarikhZaman) {
		this.tarikhZaman = tarikhZaman;
	}
}
