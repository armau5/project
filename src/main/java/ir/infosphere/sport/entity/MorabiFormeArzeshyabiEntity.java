package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "morabianformearzeshyabi")
public class MorabiFormeArzeshyabiEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeMorabiFormeArzeshyabi")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMorabi")
	private OzveMadreseEntity morabi;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeFormeArzeshyabi")
	private FormeArzeshyabiMadaresEntity formeArzeshyabi;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OzveMadreseEntity getMorabi() {
		return morabi;
	}

	public void setMorabi(OzveMadreseEntity morabi) {
		this.morabi = morabi;
	}

	public FormeArzeshyabiMadaresEntity getFormeArzeshyabi() {
		return formeArzeshyabi;
	}

	public void setFormeArzeshyabi(FormeArzeshyabiMadaresEntity formeArzeshyabi) {
		this.formeArzeshyabi = formeArzeshyabi;
	}
}
