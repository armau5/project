package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "mojavez")
public class MojavezEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeMojavez")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeValed")
	private MojavezEntity valed;

	@Column(name = "NameMojavez", nullable = false, length = 500)
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MojavezEntity getValed() {
		return valed;
	}

	public void setValed(MojavezEntity valed) {
		this.valed = valed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
