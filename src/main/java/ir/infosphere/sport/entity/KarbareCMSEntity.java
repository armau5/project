package ir.infosphere.sport.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "karbarecms")
public class KarbareCMSEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeKarbareCMS")
	private Integer id;

	@Column(name = "NameKarbari", length = 32, nullable = false)
	private String nameKarbari;

	@Column(name = "HasheRamzeOboor", length = 40, nullable = false)
	private String hasheRamzeOboor;

	@OneToMany(mappedBy = "karbareCMS")
	private List<RoleKarbareCMSEntity> roleKarbareCMSList;

	@ManyToOne
	@JoinColumn(name = "PortalID",columnDefinition = "INT(11) UNSIGNED", nullable = true)
	private PortalEntity shenaseyeSazman;

	// /////////////////////////////

	public Integer getId() {
		return id;
	}

	public PortalEntity getShenaseyeSazman() {
		return shenaseyeSazman;
	}

	public void setShenaseyeSazman(PortalEntity shenaseyeSazman) {
		this.shenaseyeSazman = shenaseyeSazman;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameKarbari() {
		return nameKarbari;
	}

	public void setNameKarbari(String nameKarbari) {
		this.nameKarbari = nameKarbari;
	}

	public String getHasheRamzeOboor() {
		return hasheRamzeOboor;
	}

	public void setHasheRamzeOboor(String hasheRamzeOboor) {
		this.hasheRamzeOboor = hasheRamzeOboor;
	}

	public List<RoleKarbareCMSEntity> getRoleKarbareCMSList() {
		return roleKarbareCMSList;
	}

	public void setRoleKarbareCMSList(List<RoleKarbareCMSEntity> roleKarbareCMSList) {
		this.roleKarbareCMSList = roleKarbareCMSList;
	}

}
