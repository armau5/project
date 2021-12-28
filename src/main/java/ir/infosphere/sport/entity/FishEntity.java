package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "fish")
public class FishEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeFish")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeTim", nullable = false)
	private TimEntity tim;

	@Column(name = "TarikheFish", nullable = false)
	private Date tarikheFish;
	
	@Column(name = "ShomareyeFish", nullable = false, length = 45)
	private String shomareyeFish;

	@Column(name = "MablagheFish", nullable = false)
	private Integer mablagheFish;
	
	@ManyToOne
	@JoinColumn(name = "NoeFish", nullable = false)
	private KodEntity noeFish;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTasvireFish", nullable = true)
	private AksEntity tasvireFish;


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

	public Date getTarikheFish() {
		return tarikheFish;
	}

	public void setTarikheFish(Date tarikheFish) {
		this.tarikheFish = tarikheFish;
	}

	public String getShomareyeFish() {
		return shomareyeFish;
	}

	public void setShomareyeFish(String shomareyeFish) {
		this.shomareyeFish = shomareyeFish;
	}

	public Integer getMablagheFish() {
		return mablagheFish;
	}

	public void setMablagheFish(Integer mablagheFish) {
		this.mablagheFish = mablagheFish;
	}

	public KodEntity getNoeFish() {
		return noeFish;
	}

	public void setNoeFish(KodEntity noeFish) {
		this.noeFish = noeFish;
	}

	public AksEntity getTasvireFish() {
		return tasvireFish;
	}

	public void setTasvireFish(AksEntity tasvireFish) {
		this.tasvireFish = tasvireFish;
	}



}
