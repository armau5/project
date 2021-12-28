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

@Entity(name = "darkhastebelitesazmani")
public class DarkhasteBeliteSazmaniEntity extends BaseEntity<Integer> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ShenaseyeDarkhasteBeliteSazmani")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeOzv", nullable = false)
	private OzvEntity ozveDarkhastDahandeh;

	@Column(name = "Tozih", length = 200)
	private String tozih;

	@Column(name = "TarikheDarkhast", nullable = false)
	private Date tarikheDarkhast;

	@ManyToOne
	@JoinColumn(name = "VaziateDarkhast", nullable = false)
	private KodEntity vaziateDarkhast;

	@OneToMany(mappedBy = "darkhasteBeliteSazmani")
	private List<VaziateSandaliEntity> vaziateSandaliList;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBazi", nullable = false)
	private BaziEntity bazi;

	@ManyToOne
	@JoinColumn(name = "ShenaseyeBolook", nullable = false)
	private BolookEntity bolook;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public OzvEntity getOzveDarkhastDahandeh() {
		return ozveDarkhastDahandeh;
	}

	public void setOzveDarkhastDahandeh(OzvEntity ozveDarkhastDahandeh) {
		this.ozveDarkhastDahandeh = ozveDarkhastDahandeh;
	}

	public String getTozih() {
		return tozih;
	}

	public void setTozih(String tozih) {
		this.tozih = tozih;
	}

	public Date getTarikheDarkhast() {
		return tarikheDarkhast;
	}

	public void setTarikheDarkhast(Date tarikheDarkhast) {
		this.tarikheDarkhast = tarikheDarkhast;
	}

	public KodEntity getVaziateDarkhast() {
		return vaziateDarkhast;
	}

	public void setVaziateDarkhast(KodEntity vaziateDarkhast) {
		this.vaziateDarkhast = vaziateDarkhast;
	}

	public List<VaziateSandaliEntity> getVaziateSandaliList() {
		return vaziateSandaliList;
	}

	public void setVaziateSandaliList(
			List<VaziateSandaliEntity> vaziateSandaliList) {
		this.vaziateSandaliList = vaziateSandaliList;
	}

	public BaziEntity getBazi() {
		return bazi;
	}

	public void setBazi(BaziEntity bazi) {
		this.bazi = bazi;
	}

	public BolookEntity getBolook() {
		return bolook;
	}

	public void setBolook(BolookEntity bolook) {
		this.bolook = bolook;
	}

}
