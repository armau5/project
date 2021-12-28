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
import javax.persistence.OneToOne;

@Entity(name = "kart")
public class KartEntity extends BaseEntity<Long> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeKart")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = true)
	private OzvEntity ozvEntity;

	@Column(name = "Serial", length = 15, nullable = true)
	private String serial;

	@Column(name = "Mojoodi", nullable = false)
	private Integer mojoodi = 0;

	@Column(name = "ShomareyeKart", length = 16, nullable = true)
	private String shomareyeKart;

	@Column(name = "TarikheSodoor", nullable = true)
	private Date tarikheSodoor;

	@Column(name = "TarikheEngheza", nullable = true)
	private Date tarikheEngheza;

	@Column(name = "Version", nullable = true)
	private Integer version;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeKarteGhabli", nullable = true)
	private KartEntity karteGhabli;

	@Column(name = "Faal", nullable = false)
	private Boolean faal = true;

	@Column(name = "Masdood", nullable = false)
	private Boolean masdood = false;
	
	@Column(name = "MasdoodShodeDarKart", nullable = true)
	private Boolean masdoodShodeDarKart = false;

	@ManyToOne
	@JoinColumn(name = "VaziyyateKart")
	private KodEntity vaziyyateKart;

	@ManyToOne
	@JoinColumn(name = "portalID", columnDefinition = "INT(11) UNSIGNED")
	private PortalEntity portalID;

	@OneToMany(mappedBy = "kart")
	private List<TageKartEntity> tageKart;

	@OneToOne(mappedBy = "kart")
	private DarkhasteKartEntity darkhasteKart;

	// ////////////////////////////////////

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public OzvEntity getOzvEntity() {
		return ozvEntity;
	}

	public void setOzvEntity(OzvEntity ozvEntity) {
		this.ozvEntity = ozvEntity;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Integer getMojoodi() {
		return mojoodi;
	}

	public void setMojoodi(Integer mojoodi) {
		this.mojoodi = mojoodi;
	}

	public String getShomareyeKart() {
		return shomareyeKart;
	}

	public void setShomareyeKart(String shomareyeKart) {
		this.shomareyeKart = shomareyeKart;
	}

	public Date getTarikheSodoor() {
		return tarikheSodoor;
	}

	public void setTarikheSodoor(Date tarikheSodoor) {
		this.tarikheSodoor = tarikheSodoor;
	}

	public Date getTarikheEngheza() {
		return tarikheEngheza;
	}

	public void setTarikheEngheza(Date tarikheEngheza) {
		this.tarikheEngheza = tarikheEngheza;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public KartEntity getKarteGhabli() {
		return karteGhabli;
	}

	public void setKarteGhabli(KartEntity karteGhabli) {
		this.karteGhabli = karteGhabli;
	}

	public Boolean getFaal() {
		return faal;
	}

	public void setFaal(Boolean faal) {
		this.faal = faal;
	}

	public Boolean getMasdood() {
		return masdood;
	}

	public void setMasdood(Boolean masdood) {
		this.masdood = masdood;
	}

	public KodEntity getVaziyyateKart() {
		return vaziyyateKart;
	}

	public void setVaziyyateKart(KodEntity vaziyyateKart) {
		this.vaziyyateKart = vaziyyateKart;
	}

	public PortalEntity getPortalID() {
		return portalID;
	}

	public void setPortalID(PortalEntity portalID) {
		this.portalID = portalID;
	}

	public List<TageKartEntity> getTageKart() {
		return tageKart;
	}

	public void setTageKart(List<TageKartEntity> tageKart) {
		this.tageKart = tageKart;
	}

	public DarkhasteKartEntity getDarkhasteKart() {
		return darkhasteKart;
	}

	public void setDarkhasteKart(DarkhasteKartEntity darkhasteKart) {
		this.darkhasteKart = darkhasteKart;
	}

	public Boolean getMasdoodShodeDarKart() {
		return masdoodShodeDarKart;
	}

	public void setMasdoodShodeDarKart(Boolean masdoodShodeDarKart) {
		this.masdoodShodeDarKart = masdoodShodeDarKart;
	}
}
