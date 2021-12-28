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

@Entity(name = "bakhsh")
public class BakhshEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeBakhsh")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeVarzeshgah", nullable = false)
	private VarzeshgahEntity varzeshgah;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeReshteyeVarzeshi", nullable = false)
	private ReshteyeVarzeshiEntity reshteyeVarzeshi;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeAks")
	private AksEntity aks;

	@Column(name = "NameBakhsh", nullable = false, length = 100)
	private String nameBakhsh;

	@Column(name = "ZarfiateTamashagar", nullable = false)
	private Integer zarfiateTamashagar;

	@Column(name = "ZarfiateOzviyyat", nullable = false)
	private Integer zarfiateOzviyyat;

	@Column(name = "GheyreFaal", nullable = false)
	private Boolean gheyreFaal = false;

	@OneToMany(mappedBy = "bakhsh")
	private List<BolookEntity> bolookList;

	@OneToMany(mappedBy = "bakhsh")
	private List<MogheiyyatEntity> mogheiyyatList;

	@OneToMany(mappedBy = "bakhsh")
	private List<BaziEntity> baziList;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public VarzeshgahEntity getVarzeshgah() {
		return varzeshgah;
	}

	public void setVarzeshgah(VarzeshgahEntity varzeshgah) {
		this.varzeshgah = varzeshgah;
	}

	public ReshteyeVarzeshiEntity getShenaseyeReshteyeVarzeshi() {
		return reshteyeVarzeshi;
	}

	public void setShenaseyeReshteyeVarzeshi(ReshteyeVarzeshiEntity shenaseyeReshteyeVarzeshi) {
		this.reshteyeVarzeshi = shenaseyeReshteyeVarzeshi;
	}

	public AksEntity getAks() {
		return aks;
	}

	public void setAks(AksEntity aks) {
		this.aks = aks;
	}

	public String getNameBakhsh() {
		return nameBakhsh;
	}

	public void setNameBakhsh(String nameBakhsh) {
		this.nameBakhsh = nameBakhsh;
	}

	public Integer getZarfiateTamashagar() {
		return zarfiateTamashagar;
	}

	public void setZarfiateTamashagar(Integer zarfiateTamashagar) {
		this.zarfiateTamashagar = zarfiateTamashagar;
	}

	public Integer getZarfiateOzviyyat() {
		return zarfiateOzviyyat;
	}

	public void setZarfiateOzviyyat(Integer zarfiateOzviyyat) {
		this.zarfiateOzviyyat = zarfiateOzviyyat;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public List<BolookEntity> getBolookList() {
		return bolookList;
	}

	public void setBolookList(List<BolookEntity> bolookList) {
		this.bolookList = bolookList;
	}

	public List<MogheiyyatEntity> getMogheiyyatList() {
		return mogheiyyatList;
	}

	public void setMogheiyyatList(List<MogheiyyatEntity> mogheiyyatList) {
		this.mogheiyyatList = mogheiyyatList;
	}

	public List<BaziEntity> getBaziList() {
		return baziList;
	}

	public void setBaziList(List<BaziEntity> baziList) {
		this.baziList = baziList;
	}


}
