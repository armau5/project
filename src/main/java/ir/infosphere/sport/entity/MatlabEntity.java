package ir.infosphere.sport.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity(name = "matlab")
public class MatlabEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeMatlab")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal")
	private PortalEntity portal;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeNoeMatlab")
	private NoeMatlabEntity noeMatlab;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeOstan")
	private NahiyeEntity ostan ;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeMadrese")
	private MadreseEntity madrese ;
	
	@ManyToOne
	@JoinColumn(name = "ShenaseyeKodeMatlab")
	private KodEntity kodeMatlab;
	
	@OneToMany(mappedBy = "matlab")
	private List<MatlabAksEntity> matlabAksList;
	
	@OneToMany(mappedBy = "matlab")
	private List<MatlabFileEntity> matlabFileList;

	@Column(name = "Onvan", length=300)
	private String onvan;
	
	@Column(name = "GheyreFaal", nullable = false)
	private Boolean gheyreFaal = false;
	
	@Column(name = "OlaviatNamayesh", nullable = false)
	private Integer olaviateNamayesh = 0;
	
	@Column(name = "Kholase", length=300)
	private String Kholase;

	public MadreseEntity getMadrese() {
		return madrese;
	}

	public void setMadrese(MadreseEntity madrese) {
		this.madrese = madrese;
	}
	
	public String getKholase() {
		return Kholase;
	}

	public void setKholase(String kholase) {
		Kholase = kholase;
	}

	@Column(name = "Matn", length = 10000)
	private String matn;
	
	@Column(name = "TarikhVaZamaneErsal")
	private Date tarikhVaZamaneErsal;

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
	
	public NoeMatlabEntity getNoeMatlab() {
		return noeMatlab;
	}

	public void setNoeMatlab(NoeMatlabEntity noeMatlab) {
		this.noeMatlab = noeMatlab;
	}

	public KodEntity getKodeMatlab() {
		return kodeMatlab;
	}

	public void setKodeMatlab(KodEntity kodeMatlab) {
		this.kodeMatlab = kodeMatlab;
	}

	public String getOnvan() {
		return onvan;
	}

	public void setOnvan(String onvan) {
		this.onvan = onvan;
	}

	public String getMatn() {
		return matn;
	}

	public void setMatn(String matn) {
		this.matn = matn;
	}

	public Date getTarikhVaZamaneErsal() {
		return tarikhVaZamaneErsal;
	}

	public void setTarikhVaZamaneErsal(Date tarikhVaZamaneErsal) {
		this.tarikhVaZamaneErsal = tarikhVaZamaneErsal;
	}

	public List<MatlabAksEntity> getMatlabAksList() {
		return matlabAksList;
	}

	public void setMatlabAksList(List<MatlabAksEntity> matlabAksList) {
		this.matlabAksList = matlabAksList;
	}

	public List<MatlabFileEntity> getMatlabFileList() {
		return matlabFileList;
	}

	public void setMatlabFileList(List<MatlabFileEntity> matlabFileList) {
		this.matlabFileList = matlabFileList;
	}

	public Boolean getGheyreFaal() {
		return gheyreFaal;
	}

	public void setGheyreFaal(Boolean gheyreFaal) {
		this.gheyreFaal = gheyreFaal;
	}

	public Integer getOlaviateNamayesh() {
		return olaviateNamayesh;
	}

	public void setOlaviateNamayesh(Integer olaviateNamayesh) {
		this.olaviateNamayesh = olaviateNamayesh;
	}
}
