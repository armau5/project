package ir.infosphere.sport.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity(name = "vaziyyatemalekebelit")
public class VaziyyateMalekeBelitEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeVaziyyateMalekeBelit")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBelit", nullable = false)
	private BelitEntity belit;
	
	@Column(name = "ZamaneSabteVaziyyat", nullable = false)
	private Date zamaneSabteVaziyyat;

	@ManyToOne
	@JoinColumn(name = "Vaziyyat")
	private KodEntity vaziyyat;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BelitEntity getBelit() {
		return belit;
	}

	public void setBelit(BelitEntity belit) {
		this.belit = belit;
	}

	public Date getZamaneSabteVaziyyat() {
		return zamaneSabteVaziyyat;
	}

	public void setZamaneSabteVaziyyat(Date zamaneSabteVaziyyat) {
		this.zamaneSabteVaziyyat = zamaneSabteVaziyyat;
	}

	public KodEntity getVaziyyat() {
		return vaziyyat;
	}

	public void setVaziyyat(KodEntity vaziyyat) {
		this.vaziyyat = vaziyyat;
	}

	
}
