package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "goroohhayekarbar")
public class GoroohhayeKarbarEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Shenase")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "Gorooh", nullable = false)
	private GorooheKarbariEntity gorooh;
	
	@ManyToOne
	@JoinColumn(name = "Karbar", nullable = false)
	private OzvEntity ozv;
	
	@Column(name = "ShorooeTakhsis", nullable = false)
	private Date shorooeTakhsis;
	
	@Column(name = "PayaneTakhsis", nullable = true)
	private Date payaneTakhsis;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GorooheKarbariEntity getGorooh() {
		return gorooh;
	}

	public void setGorooh(GorooheKarbariEntity gorooh) {
		this.gorooh = gorooh;
	}

	public OzvEntity getKarbar() {
		return ozv;
	}

	public void setKarbar(OzvEntity ozv) {
		this.ozv = ozv;
	}
	
	public Date getShorooeTakhsis() {
		return shorooeTakhsis;
	}

	public void setShorooeTakhsis(Date shorooeTakhsis) {
		this.shorooeTakhsis = shorooeTakhsis;
	}

	public Date getPayaneTakhsis() {
		return payaneTakhsis;
	}

	public void setPayaneTakhsis(Date payaneTakhsis) {
		this.payaneTakhsis = payaneTakhsis;
	}

	
}

