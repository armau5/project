package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "goroohereshteyevarzeshi")
public class GorooheReshteyeVarzeshiEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeGorooheReshteyeVarzeshi")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeReshteyeVarzeshi", nullable = false)
	private ReshteyeVarzeshiEntity reshteyeVarzeshi;
	
	@Column(name = "NameGorooheReshteyeVarzeshi", nullable = false, length = 100)
	private String nameGorooheReshteyeVarzeshi;

	@Column(name = "GheyreFaal", nullable = true)
	private Boolean gheyreFaal = false;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ReshteyeVarzeshiEntity getReshteyeVarzeshi() {
		return reshteyeVarzeshi;
	}

	public void setReshteyeVarzeshi(ReshteyeVarzeshiEntity reshteyeVarzeshi) {
		this.reshteyeVarzeshi = reshteyeVarzeshi;
	}

	public String getNameGorooheReshteyeVarzeshi() {
		return nameGorooheReshteyeVarzeshi;
	}

	public void setNameGorooheReshteyeVarzeshi(String nameGorooheReshteyeVarzeshi) {
		this.nameGorooheReshteyeVarzeshi = nameGorooheReshteyeVarzeshi;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}
	
}
