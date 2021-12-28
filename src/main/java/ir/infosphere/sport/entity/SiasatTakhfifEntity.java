package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "siasattakhfif")
public class SiasatTakhfifEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeSiasatTakhfif")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeGoroheKarbari", nullable = true)
	private GorooheKarbariEntity gorooheKarbari;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeBasteForoosh", nullable = true)
	private BasteForooshEntity basteForoosh;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoeSiasatTakhfif", nullable = false)
	private KodEntity noeBasteTakhfif;
	
	@Column(name = "Name", nullable = false)
	private String name;
	
	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GorooheKarbariEntity getGorooheKarbari() {
		return gorooheKarbari;
	}

	public void setGorooheKarbari(GorooheKarbariEntity gorooheKarbari) {
		this.gorooheKarbari = gorooheKarbari;
	}

	public BasteForooshEntity getBasteForoosh() {
		return basteForoosh;
	}

	public void setBasteForoosh(BasteForooshEntity basteForoosh) {
		this.basteForoosh = basteForoosh;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public KodEntity getNoeBasteTakhfif() {
		return noeBasteTakhfif;
	}

	public void setNoeBasteTakhfif(KodEntity noeBasteTakhfif) {
		this.noeBasteTakhfif = noeBasteTakhfif;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}
	
	public String getStandardName() {
		String result = "";
		if (getNoeBasteTakhfif().getMeghdar().equals(Constants.SiasatTakhfif_BasteForsoh)) {
			result += getBasteForoosh().getNameBaste();
		} else if (getNoeBasteTakhfif().getMeghdar().equals(Constants.SiasatTakhfif_GoroheKarbari)) {
			result += getGorooheKarbari().getNam();
		} else if (getNoeBasteTakhfif().getMeghdar().equals(Constants.SiasatTakhfif_Hamegani)) {
			result += getNoeBasteTakhfif().getMeghdar();
		}
		return result;
	}
}
