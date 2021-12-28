package ir.infosphere.sport.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "ozvemadrese")
public class OzveMadreseEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeOzveMadrese")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozveMadrese;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeMadrese", nullable = false)
	private MadreseEntity madrese;
	
	@ManyToOne
	@JoinColumn(name = "SemateOzv", nullable = false)
	private KodEntity semat;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeDarajeMorabiGari")
	private KodMeghdarEntity darajeMorabiGari;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeMadrakeTahsili")
	private KodMeghdarEntity madrakeTahsili;

	@Column(name = "TarikheSabteNam", nullable = false)
	private Date tarikheSabteNam;
	
	@Column(name = "TarbiatBadani")
	private Boolean tarbiatBadani;

	@Column(name = "TarikhePayaneEtebar")
	private Date tarikhePayaneEtebar;

	// ////////////////////////

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OzvEntity getOzveMadrese() {
		return ozveMadrese;
	}

	public void setOzveMadrese(OzvEntity ozveMadrese ) {
		this.ozveMadrese = ozveMadrese;
	}

	public MadreseEntity getMadrese() {
		return madrese;
	}

	public void setMadrese(MadreseEntity madrese) {
		this.madrese = madrese;
	}

	public KodEntity getSemat() {
		return semat;
	}

	public void setSemat(KodEntity semat) {
		this.semat = semat;
	}

	public Date getTarikheSabteNam() {
		return tarikheSabteNam;
	}

	public void setTarikheSabteNam(Date TarikheSabteNam) {
		this.tarikheSabteNam = TarikheSabteNam;
	}
	
	public Date getTarikhePayaneEtebar() {
		return tarikhePayaneEtebar;
	}

	public void setTarikhePayaneEtebar(Date TarikhePayaneEtebar) {
		this.tarikhePayaneEtebar = TarikhePayaneEtebar;
	}

	public KodMeghdarEntity getDarajeMorabiGari() {
		return darajeMorabiGari;
	}

	public void setDarajeMorabiGari(KodMeghdarEntity darajeMorabiGari) {
		this.darajeMorabiGari = darajeMorabiGari;
	}

	public KodMeghdarEntity getMadrakeTahsili() {
		return madrakeTahsili;
	}

	public void setMadrakeTahsili(KodMeghdarEntity madrakeTahsili) {
		this.madrakeTahsili = madrakeTahsili;
	}

	public Boolean getTarbiatBadani() {
		return tarbiatBadani;
	}

	public void setTarbiatBadani(Boolean tarbiatBadani) {
		this.tarbiatBadani = tarbiatBadani;
	}
}
