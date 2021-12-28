package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "kod")
public class KodEntity extends BaseEntity<Short> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeKod")
	private Short id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "ShenaseyeGorooheKod")
	private GorooheKodEntity gorooheKod;

	@Column(name = "Meghdar", nullable = false, length = 200)
	private String meghdar;

	@Column(name = "GhabeliyatHazf")
	private Boolean ghabeliyatHazf = true;
	
	@Column(name = "GhabeliyatEslah")
	private Boolean ghabeliyatEslah = true;

	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal = false;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public GorooheKodEntity getGorooheKod() {
		return gorooheKod;
	}

	public void setGorooheKod(GorooheKodEntity gorooheKod) {
		this.gorooheKod = gorooheKod;
	}

	public String getMeghdar() {
		return meghdar;
	}

	public void setMeghdar(String meghdar) {
		this.meghdar = meghdar;
	}

	public Boolean getGhabeliyatHazf() {
		return ghabeliyatHazf;
	}

	public void setGhabeliyatHazf(Boolean ghabeliyatHazf) {
		this.ghabeliyatHazf = ghabeliyatHazf;
	}

	public Boolean getGhabeliyatEslah() {
		return ghabeliyatEslah;
	}

	public void setGhabeliyatEslah(Boolean ghabeliyatEslah) {
		this.ghabeliyatEslah = ghabeliyatEslah;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	@Override
	public boolean equals(Object obj) {
		KodEntity objKod = (KodEntity) obj;
		if (obj == null)
			return false;
		if (objKod.getMeghdar().equals(meghdar) && objKod.getGorooheKod().equals(gorooheKod)) {
			return true;
		} else {
			return false;
		}
	}
}
