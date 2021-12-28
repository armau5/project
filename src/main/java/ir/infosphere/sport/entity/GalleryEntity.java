package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "gallery")
public class GalleryEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeGallery")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = false)
	private PortalEntity portal;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOstan")
	private NahiyeEntity ostan ;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeMadrese")
	private MadreseEntity madrese ;
	
	public MadreseEntity getMadrese() {
		return madrese;
	}

	public void setMadrese(MadreseEntity madrese) {
		this.madrese = madrese;
	}

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTozih")
	private TozihEntity tozih;

	@Column(name = "NameGallery", nullable = false, length = 50)
	private String nameGallery;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public NahiyeEntity getOstan() {
		return ostan;
	}

	public void setOstan(NahiyeEntity ostan) {
		this.ostan = ostan;
	}

	public TozihEntity getTozih() {
		return tozih;
	}

	public void setTozih(TozihEntity tozih) {
		this.tozih = tozih;
	}

	public String getNameGallery() {
		return nameGallery;
	}

	public void setNameGallery(String nameGallery) {
		this.nameGallery = nameGallery;
	}

}
