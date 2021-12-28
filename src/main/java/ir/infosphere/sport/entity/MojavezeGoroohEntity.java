package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "mojavezegorooh")
public class MojavezeGoroohEntity extends BaseEntity<Short> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Shenase")
	private Short id;

	@ManyToOne
	@JoinColumn(name = "Gorooh", nullable = false)
	private GorooheKarbariEntity gorooh;
	
	@ManyToOne
	@JoinColumn(name = "Mojavez", nullable = false)
	private MojavezEntity mojavez;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public GorooheKarbariEntity getGorooh() {
		return gorooh;
	}

	public void setGorooh(GorooheKarbariEntity gorooh) {
		this.gorooh = gorooh;
	}

	public MojavezEntity getMojavez() {
		return mojavez;
	}

	public void setMojavez(MojavezEntity mojavez) {
		this.mojavez = mojavez;
	}
	
}

