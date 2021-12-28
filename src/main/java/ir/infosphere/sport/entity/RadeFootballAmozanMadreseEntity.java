package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "radefootballamozanmadrese")
public class RadeFootballAmozanMadreseEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeRadeFootballAmozan")
	private Integer id;

	@Column(name = "Shaneseye8Sal")
	private Integer zire_8;
	
	@Column(name = "Shenaseye8_9Sal")
	private Integer beyne8_9;
	
	@Column(name = "Shenaseye10_11Sal")
	private Integer beyne10_11;
	
	@Column(name = "Shenaseye12_13Sal")
	private Integer beyne12_13;
	
	@Column(name = "Shenaseye14_16Sal")
	private Integer beyne14_16;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getZire_8() {
		return zire_8;
	}

	public void setZire_8(Integer zire_8) {
		this.zire_8 = zire_8;
	}

	public Integer getBeyne8_9() {
		return beyne8_9;
	}

	public void setBeyne8_9(Integer beyne8_9) {
		this.beyne8_9 = beyne8_9;
	}

	public Integer getBeyne10_11() {
		return beyne10_11;
	}

	public void setBeyne10_11(Integer beyne10_11) {
		this.beyne10_11 = beyne10_11;
	}

	public Integer getBeyne12_13() {
		return beyne12_13;
	}

	public void setBeyne12_13(Integer beyne12_13) {
		this.beyne12_13 = beyne12_13;
	}

	public Integer getBeyne14_16() {
		return beyne14_16;
	}

	public void setBeyne14_16(Integer beyne14_16) {
		this.beyne14_16 = beyne14_16;
	}
}
