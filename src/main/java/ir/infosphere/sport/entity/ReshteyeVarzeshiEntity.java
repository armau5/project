package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "reshteyevarzeshi")
public class ReshteyeVarzeshiEntity extends BaseEntity<Short> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeReshteyeVarzeshi")
	private Short id;

	@Column(name = "NameReshteyeVarzeshi", nullable = false, length = 50)
	private String nameReshteyeVarzeshi;

	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal = false;

	
	// ////////////////////////

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNameReshteyeVarzeshi() {
		return nameReshteyeVarzeshi;
	}

	public void setNameReshteyeVarzeshi(String nameReshteyeVarzeshi) {
		this.nameReshteyeVarzeshi = nameReshteyeVarzeshi;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}



}
