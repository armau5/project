package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "nahiyeh")
public class NahiyeEntity extends BaseEntity<Integer> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeNahiye")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeValed")
	private NahiyeEntity valed;

	@Column(name = "NameNahiye", length = 100, nullable = false)
	private String nameNahiye;

	@Column(name = "GheyreFaal", nullable = false)
	private Boolean gheyreFaal = false;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public NahiyeEntity getValed() {
		return valed;
	}

	public void setValed(NahiyeEntity valed) {
		this.valed = valed;
	}

	public String getNameNahiye() {
		return nameNahiye;
	}

	public void setNameNahiye(String nameNahiye) {
		this.nameNahiye = nameNahiye;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}
}
