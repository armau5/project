package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "kodmeghdar")
public class KodMeghdarEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeKodMeghdar")
	private Integer id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeGorooheKod")
	private GorooheKodEntity gorooheKod;

	@Column(name = "Nam")
	private String nam;

	@Column(name = "Meghdar")
	private Integer meghdar;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GorooheKodEntity getGorooheKod() {
		return gorooheKod;
	}
	
	public void setGorooheKod(GorooheKodEntity gorooheKod) {
		this.gorooheKod = gorooheKod;
	}

	public String getNam() {
		return nam;
	}

	public void setNam(String nam) {
		this.nam = nam;
	}

	public Integer getMeghdar() {
		return meghdar;
	}

	public void setMeghdar(Integer meghdar) {
		this.meghdar = meghdar;
	}

	@Override
	public boolean equals(Object obj) {
		KodMeghdarEntity objKodMeghdar = (KodMeghdarEntity) obj;
		if (objKodMeghdar.getNam().equals(nam) && objKodMeghdar.getMeghdar().equals(meghdar) && objKodMeghdar.getGorooheKod().equals(gorooheKod)) {
			return true;
		} else {
			return false;
		}
	}
}
