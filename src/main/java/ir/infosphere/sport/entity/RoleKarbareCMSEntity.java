package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "rolekarbarecms")
public class RoleKarbareCMSEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeRoleKarbareCMS")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeKarbareCMS")
	private KarbareCMSEntity karbareCMS;

	@ManyToOne()
	@JoinColumn(name = "ShenaseyeRoleCMS", nullable = false)
	private KodEntity roleCMS;

	// /////////////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KarbareCMSEntity getKarbareCMS() {
		return karbareCMS;
	}

	public void setKarbareCMS(KarbareCMSEntity karbareCMS) {
		this.karbareCMS = karbareCMS;
	}

	public KodEntity getRoleCMS() {
		return roleCMS;
	}

	public void setRoleCMS(KodEntity roleCMS) {
		this.roleCMS = roleCMS;
	}

}
