package ir.infosphere.sport.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "pardakhtedarkhastekart")
public class PardakhteDarkhasteKartEntity extends BaseEntity<Integer> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyePardakhteDarkhasteKart")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePishkhan", nullable = true)
	private PishkhanEntity pishkhan;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozv;

	@ManyToOne
	@JoinColumn(name = "NahveyeTahvil", nullable = false)
	private KodEntity nahveyeTahvil;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePardakht", nullable = false)
	private PardakhtEntity pardakht;

	@ManyToOne
	@JoinColumn(name = "ShenaseyePortal", nullable = false)
	private PortalEntity portal;

	@Column(name = "DadeyeEzafi", length = 200, nullable = true)
	private String dadeyeEzafi;
	
	// default constructor
	public PardakhteDarkhasteKartEntity() {
	}

	public PardakhteDarkhasteKartEntity(KodEntity nahveyeTahvil,
			PardakhtEntity pardakhtEntity, OzvEntity ozv,
			PishkhanEntity pishkhan, PortalEntity portal,
			MadreseEntity madrese, TimEntity tim) {
		this.setNahveyeTahvil(nahveyeTahvil);
		this.setOzv(ozv);
		this.setPardakht(pardakhtEntity);
		this.setPishkhan(pishkhan);
		this.setPortal(portal);
		if (madrese != null)
			this.setDadeyeEzafi("مدرسه " + madrese.getNameMadrese());
		if (tim != null)
			this.setDadeyeEzafi("تیم " + tim.getNameTim());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public PishkhanEntity getPishkhan() {
		return pishkhan;
	}

	public void setPishkhan(PishkhanEntity pishkhan) {
		this.pishkhan = pishkhan;
	}

	public KodEntity getNahveyeTahvil() {
		return nahveyeTahvil;
	}

	public void setNahveyeTahvil(KodEntity nahveyeTahvil) {
		this.nahveyeTahvil = nahveyeTahvil;
	}

	public PardakhtEntity getPardakht() {
		return pardakht;
	}

	public void setPardakht(PardakhtEntity pardakht) {
		this.pardakht = pardakht;
	}

	public OzvEntity getOzv() {
		return ozv;
	}

	public void setOzv(OzvEntity ozv) {
		this.ozv = ozv;
	}

	public PortalEntity getPortal() {
		return portal;
	}

	public void setPortal(PortalEntity portal) {
		this.portal = portal;
	}

	public String getDadeyeEzafi() {
		return dadeyeEzafi;
	}

	public void setDadeyeEzafi(String dadeyeEzafi) {
		this.dadeyeEzafi = dadeyeEzafi;
	}
}
