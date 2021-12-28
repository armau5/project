package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "galleryaks")
public class GalleryAksEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeGalleryAks")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeGallery")
	private GalleryEntity gallery;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeAks")
	private AksEntity Aks;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeTozih")
	private TozihEntity tozih;
	
	@Column(name = "NameAks")
	private String Nam;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GalleryEntity getGallery() {
		return gallery;
	}

	public void setGallery(GalleryEntity gallery) {
		this.gallery = gallery;
	}

	public AksEntity getAks() {
		return Aks;
	}

	public void setAks(AksEntity aks) {
		this.Aks = aks;
	}

	public TozihEntity getTozih() {
		return tozih;
	}

	public void setTozih(TozihEntity tozih) {
		this.tozih = tozih;
	}

	public String getNam() {
		return Nam;
	}

	public void setNam(String nam) {
		Nam = nam;
	}

	
}
