package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ozvetim")
public class OzveTimEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeOzveTim")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTim", nullable = false)
	private TimEntity tim;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;
	
	@ManyToOne
	@JoinColumn(name = "SemateOzv", nullable = false)
	private KodEntity semat;

	@Column(name = "GheyreFaal", nullable = true)
	private Boolean gheyreFaal = false;
	
	@Column(name = "ShorooeOzviat", nullable = true)
	private Date shorooeOzviat;
	
	@Column(name = "PayaneOzviat", nullable = true)
	private Date payaneOzviat;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TimEntity getTim() {
		return tim;
	}

	public void setTim(TimEntity tim) {
		this.tim = tim;
	}

	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}

	public KodEntity getSemat() {
		return semat;
	}

	public void setSemat(KodEntity semat) {
		this.semat = semat;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public Date getShorooeOzviat() {
		return shorooeOzviat;
	}

	public void setShorooeOzviat(Date shorooeOzviat) {
		this.shorooeOzviat = shorooeOzviat;
	}

	public Date getPayaneOzviat() {
		return payaneOzviat;
	}

	public void setPayaneOzviat(Date payaneOzviat) {
		this.payaneOzviat = payaneOzviat;
	}



	// ////////////////////////


}
