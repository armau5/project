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

@Entity(name = "varzeshgah")
public class VarzeshgahEntity extends BaseEntity<Short> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeVarzeshgah")
	private Short id;

	@Column(name = "NameVarzeshgah", nullable = false, columnDefinition = "VARCHAR(50)")
	private String name;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzveMasool", nullable = false)
	private OzvEntity ozveMasool;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeNahiye", nullable = false)
	private NahiyeEntity nahiye;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeTozih")
	private TozihEntity tozih;

	@ManyToOne
	@JoinColumn(name = "NoeMalekiat")
	private KodEntity noeMalekiat;

	@Column(name = "GheyreFaal")
	private Boolean gheyreFaal = false;

	@Column(name = "Adres", columnDefinition = "VARCHAR(45)")
	private String adres;

	@OneToMany(mappedBy = "varzeshgah")
	private List<BakhshEntity> bakhshList;

//	@OneToMany(mappedBy = "shenaseyeVarzeshgah")
//	private List<CMSeMahalliEntity> cmseMahalliList;

	// ////////////////////////

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public OzvEntity getOzveMasool() {
		return ozveMasool;
	}

	public void setOzveMasool(OzvEntity ozveMasool) {
		this.ozveMasool = ozveMasool;
	}

	public NahiyeEntity getNahiye() {
		return nahiye;
	}

	public void setNahiye(NahiyeEntity nahiye) {
		this.nahiye = nahiye;
	}

	public TozihEntity getTozih() {
		return tozih;
	}

	public void setTozih(TozihEntity tozih) {
		this.tozih = tozih;
	}

	public KodEntity getNoeMalekiat() {
		return noeMalekiat;
	}

	public void setNoeMalekiat(KodEntity noeMalekiat) {
		this.noeMalekiat = noeMalekiat;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public String getAdres() {
		return adres;
	}

	public void setAdres(String adres) {
		this.adres = adres;
	}

	public List<BakhshEntity> getBakhshList() {
		return bakhshList;
	}

	public void setBakhshList(List<BakhshEntity> bakhshList) {
		this.bakhshList = bakhshList;
	}

//	public List<CMSeMahalliEntity> getCmseMahalliList() {
//		return cmseMahalliList;
//	}
//
//	public void setCmseMahalliList(List<CMSeMahalliEntity> cmseMahalliList) {
//		this.cmseMahalliList = cmseMahalliList;
//	}


}
